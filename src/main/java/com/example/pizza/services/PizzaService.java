package com.example.pizza.services;

import com.example.pizza.entities.Customer;
import com.example.pizza.entities.Pizza;
import com.example.pizza.repositories.PizzaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {
    private PizzaRepository pizzaRepository;

    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public boolean pizzaExists(Long id) {
        return pizzaRepository.existsById(id);
    }

    public List<Pizza> getAllPizzas() {

        return pizzaRepository.findAll();
    }

    public Optional<Pizza> findOnePizza(Long id) {
        return pizzaRepository.findById(id);
    }

    public Pizza savePizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public void deletePizza(Long id) {
        pizzaRepository.deleteById(id);
    }

    /*public Pizza addNewPizzaToCustomer(Optional<Customer> customer, Pizza pizza) {
        return pizzaRepository.addNewPizzaToCustomer(customer, pizza);
    }*/

}
