package com.api.alquilaya.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.alquilaya.models.Customer;
import com.api.alquilaya.repositories.CustomerRepository;

@Service
public class CustomerService extends BaseCrudService<Customer> {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        super(customerRepository, "El cliente");
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public Customer save(Customer customer) {
        customerRepository.findByEmailIgnoreCase(customer.getEmail())
                .filter(saved -> !saved.getId().equals(customer.getId()))
                .ifPresent(saved -> {
                    throw new IllegalArgumentException("Ya existe un cliente con ese correo");
                });
        customerRepository.findByDriversLicenseIgnoreCase(customer.getDriversLicense())
                .filter(saved -> !saved.getId().equals(customer.getId()))
                .ifPresent(saved -> {
                    throw new IllegalArgumentException("Ya existe un cliente con esa licencia");
                });
        return customerRepository.save(customer);
    }
}
