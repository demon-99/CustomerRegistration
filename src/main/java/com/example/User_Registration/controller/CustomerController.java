package com.example.User_Registration.controller;

import com.example.User_Registration.dto.CustomerDTO;
import com.example.User_Registration.entity.Customer;
import com.example.User_Registration.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    @Operation(summary = "Register a new customer", description = "Provide customer details to register")
    @ApiResponse(responseCode = "200", description = "Customer registered successfully returns customer in response")
    @ApiResponse(responseCode = "500", description = "Not NULL property missing in request, Email alreadY Exists")
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {
        Customer registeredCustomer = customerService.registercustomer(customer);
        return ResponseEntity.ok(registeredCustomer);
    }

    @PostMapping("/validate")
    @Operation(summary = "Validate customer", description = "Check for  login with email and password in db")
    @ApiResponse(responseCode = "200", description = "Customer logged in successfully")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @ResponseBody
    public ResponseEntity<String> validateUser(@RequestParam String email, @RequestParam String password) {

        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email and password must not be empty.");
        }
        boolean isValid = Boolean.TRUE.equals(customerService.validateUser(email, password));
        if (!isValid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Login successful");
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Get all users",
            description = "Fetches all customer details. Requires ROLE_ADMIN role."
             // Adjust as per your security setup
    )
    @ApiResponse(responseCode = "200", description = "All Customer Fetched")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @GetMapping("/all")
    public ResponseEntity<List<CustomerDTO>> getAllUsers() {
        List<CustomerDTO> customerDTOS = customerService.getAllUsers();
        return ResponseEntity.ok(customerDTOS);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Delete a customer", description = "Delete customer by email")
    @ApiResponse(responseCode = "200", description = "Customer deleted successfully")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        boolean deleted = customerService.deleteUserByEmail(email);
        if (deleted) {
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

}
