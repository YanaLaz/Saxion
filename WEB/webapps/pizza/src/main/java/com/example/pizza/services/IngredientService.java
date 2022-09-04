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

    /**
     * checks that ingredient exists by id
     * @param id
     * @return
     */
    public boolean ingredientExists(Long id) {
        return ingredientRepository.existsById(id);
    }

    /**
     * returns list of ingredients
     * @return
     */
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    /**
     * returns ingredient by id
     * @param id
     * @return
     */
    public Optional<Ingredient> findOneIngredient(Long id) {
        return ingredientRepository.findById(id);
    }

    /**
     * saves ingredient
     * @param ingredient
     * @return
     */
    public Ingredient saveIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    /**
     * adds new ingredient to pizza
     * @param pizza
     * @param ingredient
     * @return
     */
    public Ingredient addIngredientToPizza(Pizza pizza, Ingredient ingredient) {
        ingredient.setPizza(pizza);
        return ingredientRepository.save(ingredient);
    }

    /**
     * deletes ingredient by id
     * @param IngredientId
     */
    public void deleteIngredient(Long IngredientId) {
        ingredientRepository.deleteById(IngredientId);
    }

    /*public Ingredient addNewIngredientToPizza(Optional<Pizza> pizza, Ingredient ingredient) {
        return ingredientRepository.addNewIngredientToPizza(pizza, ingredient);
    }*/
}
