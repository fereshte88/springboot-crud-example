package com.springBoot.crud.example.controller;


import com.springBoot.crud.example.model.Customer;
import com.springBoot.crud.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/customers")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> getAllCustomers() {
        List<Customer> customers = this.customerService.getAll();
        return ResponseEntity.ok(customers);
    }

    @GetMapping(value = "/customers/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> getCustomerById(@PathVariable("id") String id) {
        Long _id = Long.valueOf(id);
        Customer customer = this.customerService.getCustomerById(_id);
        return ResponseEntity.ok(customer);
    }

    @PostMapping(value = "/customers")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> addCustomer(@RequestBody Customer customer) {
        Customer created = this.customerService.add(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping(value = "/customers")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> updateCustomer(@RequestBody Customer customer) {
        Customer updated = this.customerService.update(customer);
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
