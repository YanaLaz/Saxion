package com.example.pizza.services;

import com.example.pizza.entities.Ingredient;
import com.example.pizza.entities.Pizza;
import com.example.pizza.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {

    private IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public boolean ingredientExists(Long id) {
        return ingredientRepository.existsById(id);
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public Optional<Ingredient> findOneIngredient(Long id) {
        return ingredientRepository.findById(id);
    }

    public Ingredient saveIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public void deleteIngredient(Long id) {
        ingredientRepository.deleteById(id);
    }

    public Ingredient addIngredientToPizza(Pizza pizza, Ingredient ingredient) {
        ingredient.setPizza(pizza);
        return ingredientRepository.save(ingredient);
    }

    public void deleteIngredient(Pizza pizza, Long ingrId) {
        ingredientRepository.deleteById(ingrId);
    }

    /*public Ingredient addNewIngredientToPizza(Optional<Pizza> pizza, Ingredient ingredient) {
        return ingredientRepository.addNewIngredientToPizza(pizza, ingredient);
    }*/
}
