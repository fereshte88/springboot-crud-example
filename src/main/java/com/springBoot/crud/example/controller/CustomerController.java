package com.springBoot.crud.example.controller;


import com.springBoot.crud.example.model.Customer;
import com.springBoot.crud.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // create customer rest api
    @PostMapping("/customer")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }
}
