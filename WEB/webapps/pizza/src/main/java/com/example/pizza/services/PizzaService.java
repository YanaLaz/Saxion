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

    /**
     * Method checks that pizza exists
     * @param id
     * @return
     */
    public boolean pizzaExists(Long id) {
        return pizzaRepository.existsById(id);
    }

    /**
     * returns all pizzas
     * @return
     */
    public List<Pizza> getAllPizzas() {

        return pizzaRepository.findAll();
    }

    /**
     * returns pizza by id
     * @param id
     * @return
     */
    public Optional<Pizza> findOnePizza(Long id) {
        return pizzaRepository.findById(id);
    }

    /**
     * saves pizza
     * @param pizza
     * @return
     */
    public Pizza savePizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    /**
     * deletes pizza by id
     * @param id
     */
    public void deletePizza(Long id) {
        pizzaRepository.deleteById(id);
    }
}
