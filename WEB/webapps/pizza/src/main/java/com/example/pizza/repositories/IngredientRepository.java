package com.example.pizza.repositories;

import com.example.pizza.entities.Customer;
import com.example.pizza.entities.Ingredient;
import com.example.pizza.entities.Pizza;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
    List<Ingredient> findAll();

}
