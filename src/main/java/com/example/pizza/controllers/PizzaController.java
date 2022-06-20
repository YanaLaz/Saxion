package com.example.pizza.controllers;

import com.example.pizza.entities.Ingredient;
import com.example.pizza.entities.Pizza;
import com.example.pizza.services.IngredientService;
import com.example.pizza.services.PizzaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {

    private static PizzaService pizzaService;
    public final IngredientService ingredientService;

    public PizzaController(PizzaService pizzaService, IngredientService ingredientService) {
        this.pizzaService = pizzaService;
        this.ingredientService = ingredientService;
    }

    @GetMapping
    @CrossOrigin(origins = {"*"})
    public List<Pizza> getAllPizzas() {
        return this.pizzaService.getAllPizzas();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = {"*"})
    public static Pizza findOnePizza(@PathVariable Long id) {
        Optional<Pizza> pizza = pizzaService.findOnePizza(id);
        return pizza.orElse(null);
    }

   @GetMapping("{id}/ingredients")
   @CrossOrigin(origins = {"*"})
    public List<Ingredient> getIngredientsByPizza(@PathVariable Long id){
//        Optional<Pizza> foundPizza = findOnePizza(id);

       return findOnePizza(id).getListOfIngredients();
//        return foundPizza.get().getListOfIngredients();
    }

    @PostMapping(consumes = {"Application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin(origins = {"*"})
    public Pizza addNewPizza(@RequestBody Pizza pizza) {
        if (pizza.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Do not provide (new) id!");
        }

        return this.pizzaService.savePizza(pizza);
    }

    @PostMapping("{id}/ingredients")
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin(origins = {"*"})
    public Ingredient addNewIngredientToPizza(@PathVariable Long id, @RequestBody Ingredient ingredient){
        Pizza pizza = findOnePizza(id);

        if (pizza == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + id + " not found!");
        }

        return ingredientService.addIngredientToPizza(pizza, ingredient);
    }


    @PutMapping("/{id}")
    @CrossOrigin(origins = {"*"})
    public Pizza updatePizza(@PathVariable Long id, @RequestBody Pizza pizza) {
        if (!id.equals(pizza.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot change id!");
        }

        return this.pizzaService.savePizza(pizza);
    }

//    @PutMapping("/{id}/ingredients/{id}")
//    @CrossOrigin(origins = {"*"})
//    public Pizza updateIngredientByPizza(@PathVariable Long pizzaId, @RequestBody Ingredient ingredient, @PathVariable Long ingredientId) {
//
//
//        return this.pizzaService.savePizza(pizza);
//    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CrossOrigin(origins = {"*"})
    public void deletePizza(@PathVariable Long id) {
        if (!this.pizzaService.pizzaExists(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + id + " not found!");
        }

        this.pizzaService.deletePizza(id);
    }

//    @DeleteMapping("/{id}/ingredients/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @CrossOrigin(origins = {"*"})
//    public void deleteIngredient(@PathVariable Long pizzaId, @PathVariable Long ingrId) {
//        Pizza pizza = findOnePizza(pizzaId);
//
//        if (pizza == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + pizzaId + " not found!");
//        }
//
//        this.ingredientService.deleteIngredient(pizza, ingrId);
//    }
}
