package com.springBoot.crud.example.service.Impl;

import com.springBoot.crud.example.model.Customer;
import com.springBoot.crud.example.repositories.CustomerRepository;
import com.springBoot.crud.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "customerCache")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Cacheable(cacheNames = "customers")
    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @CacheEvict(cacheNames = "customers", allEntries = true)
    @Override
    public Customer add(Customer customer) {
        return customerRepository.save(customer);
    }

    @CacheEvict(cacheNames = "customers", allEntries = true)
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

    @Caching(evict = { @CacheEvict(cacheNames = "customer", key = "#id"),
            @CacheEvict(cacheNames = "customers", allEntries = true) })
    @Override
    public void delete(long id) {
            customerRepository.deleteById(id);
    }

    @Cacheable(cacheNames = "customer", key = "#id", unless = "#result == null")
    @Override
    public Customer getCustomerById(long id) {
        return customerRepository.findById(id).orElse(null);
    }
}
