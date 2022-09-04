package com.example.pizza.repositories;

import com.example.pizza.entities.Customer;
import com.example.pizza.entities.Pizza;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findAll();




}
