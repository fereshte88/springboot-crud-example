package com.springBoot.crud.example.service.Impl;

import com.springBoot.crud.example.model.Customer;
import com.springBoot.crud.example.repositories.CustomerRepository;
import com.springBoot.crud.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer add(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        Optional<Customer> optCustomer = this.customerRepository.findById(customer.getId());
        if (optCustomer.isEmpty())
            return null;
        Customer repCustomer = optCustomer.get();
        repCustomer.setFirstName(customer.getFirstName());
        repCustomer.setLastName(customer.getLastName());
        return customerRepository.save(repCustomer);
    }

    @Override
    public void delete(long id) {
            customerRepository.deleteById(id);
    }

    @Override
    public Customer getCustomerById(long id) {
        return customerRepository.findById(id).orElse(null);
    }
}
