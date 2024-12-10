package com.springBoot.crud.example.repositories;

import com.springBoot.crud.example.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
