package com.springBoot.crud.example.service;

import com.springBoot.crud.example.model.Customer;
import com.springBoot.crud.example.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    public List<Customer> getAll();

    public Customer add(Customer customer);

    public Customer update(Customer customer);

    public void delete(long id);

    public Customer getCustomerById(long id);
}
