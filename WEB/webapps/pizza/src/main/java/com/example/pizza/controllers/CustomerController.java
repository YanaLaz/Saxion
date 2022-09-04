package com.example.pizza.controllers;

import com.example.pizza.entities.Customer;
import com.example.pizza.entities.Ingredient;
import com.example.pizza.entities.Pizza;
import com.example.pizza.entities.views.Views;
import com.example.pizza.services.CustomerService;
import com.example.pizza.services.JoinPizzaCustomerService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final JoinPizzaCustomerService joinPizzaCustomerService;

    /**
     * Customer controller creates
     * @param customerService
     * @param joinPizzaAndCustomer
     */
    public CustomerController(CustomerService customerService, JoinPizzaCustomerService joinPizzaAndCustomer) {
        this.customerService = customerService;
        this.joinPizzaCustomerService = joinPizzaAndCustomer;
    }

    /**
     * This method for returning all customers
     * @return
     */
    @GetMapping
    @JsonView(Views.CustomersView.class)
    @CrossOrigin(origins = {"*"})
    public List<Customer> getAllCustomers() {
        return this.customerService.getAllCustomers();
    }

    /**
     * This method for returning one customer
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @JsonView(Views.CustomersView.class)
    @CrossOrigin(origins = {"*"})
    public Optional<Customer> findOneCustomer(@PathVariable Long id) {
       Optional<Customer> customer = this.customerService.findOneCustomer(id);

        if (customer == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with id " + id + " not found!");
        }
        return customer;
    }

    /**
     * This method for returning all pizzas for customer
     * @param customerId
     * @return
     */
    @GetMapping("/{customerId}/pizzas")
    @CrossOrigin(origins = {"*"})
    @JsonView(Views.CustomersView.class)
    public Set<Pizza> getPizzasByCustomer(@PathVariable Long customerId){

        return findOneCustomer(customerId).get().getCustomersPizza();
    }

    /**
     * This method for adding a customer
     * @param customer
     * @return
     */
    @PostMapping(consumes = {"Application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin(origins = {"*"})
    public Customer addNewCustomerJson(@RequestBody Customer customer){
        return this.customerService.saveCustomer(customer);
    }

    /**
     * This method updates customer
     * @param id
     * @param customer
     * @return
     */
    @PutMapping("/{id}")
    @CrossOrigin(origins = {"*"})
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        if (!id.equals(customer.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot change id!");
        }
        return this.customerService.saveCustomer(customer);
    }

    /**
     * This is method add pizza to customer
     * @param customerId
     * @param pizzaId
     */
    @PutMapping("/{customerId}/pizzas/{pizzaId}")
    @CrossOrigin(origins = {"*"})
    public void addCustomerToPizza(@PathVariable Long customerId, @PathVariable Long pizzaId) {
        try {
            joinPizzaCustomerService.joinPizzaAndCustomer(pizzaId, customerId);
        } catch (EntityNotFoundException enfe){
            throw new ResponseStatusException(HttpStatus.MULTI_STATUS, enfe.getMessage());
        }
    }

    /**
     * This method deletes customer
     * @param id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CrossOrigin(origins = {"*"})
    public void deleteCustomer(@PathVariable Long id) {
        if (!this.customerService.customerExists(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with id " + id + " not found!");
        }

        this.customerService.deleteCustomer(id);
    }

    /**
     * This method deletes pizza from customer
     * @param customerId
     * @param pizzaId
     */
    @DeleteMapping("/{customerId}/pizzas/{pizzaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CrossOrigin(origins = {"*"})
    public void removeCustomerFromPizza(@PathVariable Long customerId, @PathVariable Long pizzaId) {
        try {
            joinPizzaCustomerService.removePizzaFromCustomer(pizzaId, customerId);
        } catch (EntityNotFoundException enfe){
            throw new ResponseStatusException(HttpStatus.MULTI_STATUS, enfe.getMessage());
        }
    }

}
