package com.example.User_Registration.service;

import com.example.User_Registration.entity.Customer;
import com.example.User_Registration.repository.CustomerRepository;
import com.example.User_Registration.service.CustomerService;
import com.example.User_Registration.util.IpAndCountryLookup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private IpAndCountryLookup ipAndCountryLookup;  // Mock IpAndCountryLookup

    private Customer customer1;

    @BeforeEach
    void setUp() {
        customer1 = new Customer();
        customer1.setId(1L);
        customer1.setEmail("rahul.sharma@example.in");
        customer1.setName("Rahul Sharma");
        customer1.setPassword("RahulPass123");
        customer1.setGender(com.example.User_Registration.enums.Gender.MALE);

        lenient().when(customerRepository.findByEmail(customer1.getEmail())).thenReturn(Optional.empty());
        lenient().when(ipAndCountryLookup.getIpAddress()).thenReturn("192.168.0.1");  // Mock IP address
    }

    @Test
    void testRegisterCustomer_Success() {
        when(customerRepository.findByEmail(customer1.getEmail())).thenReturn(Optional.empty());
        when(customerRepository.save(customer1)).thenReturn(customer1);

        Customer registeredCustomer = customerService.registercustomer(customer1);

        assertNotNull(registeredCustomer);
        assertEquals(customer1.getEmail(), registeredCustomer.getEmail());
        verify(customerRepository, times(1)).findByEmail(customer1.getEmail());
        verify(customerRepository, times(1)).save(customer1);
    }

    @Test
    void testRegisterCustomerWithExistingEmail() {
        when(customerRepository.findByEmail(customer1.getEmail())).thenReturn(Optional.of(customer1));

        Exception exception = assertThrows(RuntimeException.class, () -> customerService.registercustomer(customer1));
        assertEquals("Customer already exists with this email", exception.getMessage());  // Adjusted for case-sensitivity

        verify(customerRepository, times(1)).findByEmail(customer1.getEmail());
    }

    @Test
    void testRegisterCustomerWithEmptyPassword() {
        Customer customerWithEmptyPassword = new Customer();
        customerWithEmptyPassword.setEmail("new.user@example.in");
        customerWithEmptyPassword.setName("New User");
        customerWithEmptyPassword.setPassword("");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerService.registercustomer(customerWithEmptyPassword));
        assertEquals("Password cannot be empty", exception.getMessage());
    }

    @Test
    void testValidateUser_Success() {
        when(customerRepository.findByEmail(customer1.getEmail())).thenReturn(Optional.of(customer1));

        boolean isValid = customerService.validateUser(customer1.getEmail(), customer1.getPassword());

        assertTrue(isValid);
        verify(customerRepository, times(1)).findByEmail(customer1.getEmail());
    }

    @Test
    void testValidateUserWithInvalidCredentials() {
        when(customerRepository.findByEmail(customer1.getEmail())).thenReturn(Optional.of(customer1));

        boolean isValid = customerService.validateUser(customer1.getEmail(), "wrongPassword");

        assertFalse(isValid);
        verify(customerRepository, times(1)).findByEmail(customer1.getEmail());
    }

    @Test
    void testDeleteUserByEmail_UserExists() {
        when(customerRepository.findByEmail(customer1.getEmail())).thenReturn(Optional.of(customer1));
        doNothing().when(customerRepository).deleteUserByEmail(customer1.getEmail());

        boolean isDeleted = customerService.deleteUserByEmail(customer1.getEmail());

        assertTrue(isDeleted);
        verify(customerRepository, times(1)).findByEmail(customer1.getEmail());
        verify(customerRepository, times(1)).deleteUserByEmail(customer1.getEmail());
    }

    @Test
    void testDeleteUserByEmail_UserNotFound() {
        when(customerRepository.findByEmail("unknown.user@example.in")).thenReturn(Optional.empty());

        boolean isDeleted = customerService.deleteUserByEmail("unknown.user@example.in");

        assertFalse(isDeleted);
        verify(customerRepository, times(1)).findByEmail("unknown.user@example.in");
    }
}
