package com.example.pizza.services;

import com.example.pizza.entities.Customer;
import com.example.pizza.entities.Pizza;
import com.example.pizza.repositories.PizzaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class JoinPizzaCustomerService {
    private PizzaService pizzaService;
    private CustomerService customerService;
    private PizzaRepository pizzaRepository;

    public JoinPizzaCustomerService(PizzaService pizzaService, CustomerService customerService, PizzaRepository pizzaRepository) {
        this.pizzaService = pizzaService;
        this.customerService = customerService;
        this.pizzaRepository = pizzaRepository;
    }

    /**
     * Method joins pizza and customer
     * @param pizzaId
     * @param customerId
     */
    public void joinPizzaAndCustomer(Long pizzaId, Long customerId) {
        Pizza pizza = pizzaService.findOnePizza(pizzaId).orElse(null);
        if (pizza==null){
            throw new EntityNotFoundException("Pizza with that id not found");
        }
        Customer customer = customerService.findOneCustomer(customerId).orElse(null);
        if (customer==null){
            throw new EntityNotFoundException("Customer with that id not found");
        }
        pizza.addCustomer(customer);
        this.pizzaRepository.save(pizza);
    }

    /**
     * method deletes pizza from customer
     * @param pizzaId
     * @param customerId
     */
    public void removePizzaFromCustomer(Long pizzaId, Long customerId){
        Pizza pizza = pizzaService.findOnePizza(pizzaId).orElse(null);
        if (pizza==null){
            throw new EntityNotFoundException("Pizza with that id not found");
        }
        Customer customer = customerService.findOneCustomer(customerId).orElse(null);
        if (customer==null){
            throw new EntityNotFoundException("Customer with that id not found");
        }
        pizza.removeCustomer(customer);
        this.pizzaRepository.save(pizza);
    }

}




