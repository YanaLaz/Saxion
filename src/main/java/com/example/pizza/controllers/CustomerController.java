package com.example.pizza.controllers;

import com.example.pizza.entities.Customer;
import com.example.pizza.entities.Pizza;
import com.example.pizza.services.CustomerService;
import com.example.pizza.services.PizzaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @CrossOrigin(origins = {"*"})
    public List<Customer> getAllCustomers() {
        return this.customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = {"*"})
    public Optional<Customer> findOneCustomer(@PathVariable Long id) {
       Optional<Customer> customer = this.customerService.findOneCustomer(id);

        if (customer == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with id " + id + " not found!");
        }
        return customer;
    }



    /*@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addNewCustomer(@RequestBody Customer customer) {
        if (customer.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Do not provide (new) id!");
        }

        return this.customerService.saveCustomer(customer);
    }*/

    @PostMapping(consumes = {"Application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin(origins = {"*"})
    public Customer addNewCustomerJson(@RequestBody Customer customer){
        return this.customerService.saveCustomer(customer);
    }

    @PutMapping("/{id}")
    @CrossOrigin(origins = {"*"})
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        if (!id.equals(customer.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot change id!");
        }
        return this.customerService.saveCustomer(customer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CrossOrigin(origins = {"*"})
    public void deleteCustomer(@PathVariable Long id) {
        if (!this.customerService.customerExists(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with id " + id + " not found!");
        }

        this.customerService.deleteCustomer(id);
    }
}
