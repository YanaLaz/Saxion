package com.example.pizza.repositories;

import com.example.pizza.entities.Customer;
import com.example.pizza.entities.Pizza;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PizzaRepository extends CrudRepository<Pizza, Long> {
    List<Pizza> findAll();
    //List<Pizza> findAllByDelivered(boolean delivered);

    //Pizza addNewPizzaToCustomer(Optional<Customer> customer, Pizza pizza);
}
