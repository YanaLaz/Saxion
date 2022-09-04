package com.example.pizza.services;

import com.example.pizza.entities.Customer;
import com.example.pizza.entities.Pizza;
import com.example.pizza.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public boolean customerExists(Long id) {
        return customerRepository.existsById(id);
    }

    /**
     * method return list of all customers
     * @return
     */
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    /**
     * method return one customer by id
     * @param id
     * @return
     */
    public Optional<Customer> findOneCustomer(Long id) {
        return customerRepository.findById(id);
    }

    /**
     * saves customer
     * @param customer
     * @return
     */
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * delete customer by id
     * @param id
     */
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }




}
