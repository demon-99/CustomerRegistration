package com.example.User_Registration.service;

import com.example.User_Registration.dto.CustomerDTO;
import com.example.User_Registration.repository.CustomerRepository;
import com.example.User_Registration.util.IpAndCountryLookup;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.User_Registration.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private IpAndCountryLookup ipAndCountryLookup;
    public Customer registercustomer(Customer customer){
        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new RuntimeException("Customer already exists with this email");
        }
        if (customer.getPassword() == null || customer.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        String ipAddress = ipAndCountryLookup.getIpAddress();
        String country = ipAndCountryLookup.getCountry(ipAddress);
        customer.setIpAddress(ipAddress);
        customer.setCountry(country);
        return customerRepository.save(customer);
    }
    public boolean validateUser(String email,String password){
        Optional <Customer> user = customerRepository.findByEmail(email);
        return user.isPresent()&&user.get().getPassword().equals(password);
    }
    public List<CustomerDTO> getAllUsers() {
        return customerRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CustomerDTO convertToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setName(customer.getName());
        customerDTO.setGender(customer.getGender());
        customerDTO.setIpAddress(customer.getIpAddress());
        customerDTO.setCountry(customer.getCountry());
        return customerDTO;
    }
    @Transactional
    public boolean deleteUserByEmail(String email) {
        Optional<Customer> customer = customerRepository.findByEmail(email);
        if (customer.isPresent()) {
            customerRepository.deleteUserByEmail(email);
            return true;
        }
        return false; // If the user is not found
    }
}
