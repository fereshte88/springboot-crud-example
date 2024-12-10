package com.springBoot.crud.example.service;

import com.springBoot.crud.example.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
