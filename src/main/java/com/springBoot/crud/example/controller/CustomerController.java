package com.springBoot.crud.example.controller;


import com.springBoot.crud.example.model.Customer;
import com.springBoot.crud.example.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/customers")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> getAllCustomers() {
        List<Customer> customers = this.customerService.getAll();
        logger.info("logger.info getAllCustomers called");
        logger.debug(" logger.debug getAllCustomers called");
        return ResponseEntity.ok(customers);
    }

    @GetMapping(value = "/customers/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> getCustomerById(@PathVariable("id") String id) {
        Long _id = Long.valueOf(id);
        Customer customer = this.customerService.getCustomerById(_id);
        if(customer == null) {
            logger.error("getCustomerById called with id {} not found", id);
        }
        return ResponseEntity.ok(customer);
    }

    @PostMapping(value = "/customers")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> addCustomer(@RequestBody Customer customer) {
        logger.info("addCustomer called");
        logger.debug("customer: {}", customer);
        Customer created = this.customerService.add(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping(value = "/customers")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> updateCustomer(@RequestBody Customer customer) {
        logger.info("updateCustomer called");
        Customer updated = this.customerService.update(customer);
        if(updated == null) {
            logger.error("updateCustomer called with id {} not found", customer.getId());
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping(value = "/customers/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteCustomerById(@PathVariable("id") String id) {
        Long _id = Long.valueOf(id);
        this.customerService.delete(_id);
        return ResponseEntity.ok().build();
    }
}
