package com.example.pizza.controllers;

import com.example.pizza.entities.Customer;
import com.example.pizza.entities.Ingredient;
import com.example.pizza.entities.Pizza;
import com.example.pizza.entities.views.Views;
import com.example.pizza.services.IngredientService;
import com.example.pizza.services.JoinPizzaCustomerService;
import com.example.pizza.services.PizzaService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {

    private static PizzaService pizzaService;
    public final IngredientService ingredientService;
    private final JoinPizzaCustomerService joinPizzaCustomerService;

    /**
     * This method creates pizza controller
     * @param pizzaService
     * @param ingredientService
     * @param joinPizzaCustomerService
     */
    public PizzaController(PizzaService pizzaService, IngredientService ingredientService, JoinPizzaCustomerService joinPizzaCustomerService) {
        this.pizzaService = pizzaService;
        this.ingredientService = ingredientService;
        this.joinPizzaCustomerService = joinPizzaCustomerService;
    }

    /**
     * Returns list of all pizzas
     * @return
     */
    @GetMapping
    @JsonView(Views.PizzasView.class)
    @CrossOrigin(origins = {"*"})
    public List<Pizza> getAllPizzas() {
        return this.pizzaService.getAllPizzas();
    }

    /**
     * Returns one pizza
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @JsonView(Views.PizzasView.class)
    @CrossOrigin(origins = {"*"})
    public static Pizza findOnePizza(@PathVariable Long id) {
        Optional<Pizza> pizza = pizzaService.findOnePizza(id);
        return pizza.orElse(null);
    }

    /**
     * Returns list of all ingredients of pizza
     * @param id
     * @return
     */
   @GetMapping("/{id}/ingredients")
   @CrossOrigin(origins = {"*"})
    public List<Ingredient> getIngredientsByPizza(@PathVariable Long id){

       return findOnePizza(id).getListOfIngredients();

    }

    /**
     * Returns list of all customers for one pizza
     * @param pizzaId
     * @return
     */
    @GetMapping("/{pizzaId}/customers")
    @CrossOrigin(origins = {"*"})
    @JsonView(Views.CustomersView.class)
    public Set<Customer> getCustomersByPizza(@PathVariable Long pizzaId){

        return findOnePizza(pizzaId).getCustomers();
    }

    /**
     * Method adds new pizza
     * @param pizza
     * @return
     */
    @PostMapping(consumes = {"Application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin(origins = {"*"})
    public Pizza addNewPizza(@RequestBody Pizza pizza) {
        if (pizza.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Do not provide (new) id!");
        }

        return this.pizzaService.savePizza(pizza);
    }

    /**
     * Method adds new ingredient to pizza
     * @param id
     * @param ingredient
     * @return
     */
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

    /**
     * This method updates pizza details
     * @param id
     * @param pizza
     * @return
     */
    @PutMapping("/{id}")
    @CrossOrigin(origins = {"*"})
    public Pizza updatePizza(@PathVariable Long id, @RequestBody Pizza pizza) {
        if (!id.equals(pizza.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot change id!");
        }

        return this.pizzaService.savePizza(pizza);
    }


    /**
     * Method adds customer to pizza
     * @param pizzaId
     * @param customerId
     */
    @PutMapping("/{pizzaId}/customers/{customerId}")
    @CrossOrigin(origins = {"*"})
    public void addCustomerToPizza(@PathVariable Long pizzaId, @PathVariable Long customerId) {
        try {
            joinPizzaCustomerService.joinPizzaAndCustomer(pizzaId, customerId);
        } catch (EntityNotFoundException enfe){
            throw new ResponseStatusException(HttpStatus.MULTI_STATUS, enfe.getMessage());
        }
    }

    /**
     * Updates ingredient for pizza
     * @param pizzaId
     * @param ingredientId
     * @param ingredient
     * @return
     */
    @PutMapping("/{pizzaId}/ingredients/{ingredientId}")
    @CrossOrigin(origins = {"*"})
    public Ingredient updateIngredient(@PathVariable Long pizzaId, @PathVariable Long ingredientId,  @RequestBody Ingredient ingredient) {
        if (!pizzaId.equals(findOnePizza(pizzaId))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot change id!");
        }

        return this.ingredientService.saveIngredient(ingredient);
    }

    /**
     * Delete pizza from customer
     * @param pizzaId
     * @param customerId
     */
    @DeleteMapping("/{pizzaId}/customers/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CrossOrigin(origins = {"*"})
    public void removePizzaFromCustomer(@PathVariable Long pizzaId, @PathVariable Long customerId) {
        try {
            joinPizzaCustomerService.removePizzaFromCustomer(pizzaId, customerId);
        } catch (EntityNotFoundException enfe){
            throw new ResponseStatusException(HttpStatus.MULTI_STATUS, enfe.getMessage());
        }
    }

    /**
     * Delete pizza
     * @param id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CrossOrigin(origins = {"*"})
    public void deletePizza(@PathVariable Long id) {
        if (!this.pizzaService.pizzaExists(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + id + " not found!");
        }

        this.pizzaService.deletePizza(id);
    }

    /**
     * Delete ingredient for pizza
     * @param pizzaId
     * @param ingredientId
     */
    @DeleteMapping("/{pizzaId}/ingredients/{ingredientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CrossOrigin(origins = {"*"})
    public void deleteIngredient(@PathVariable Long pizzaId, @PathVariable Long ingredientId) {
        Pizza pizza = findOnePizza(pizzaId);

        if (pizza == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + pizzaId + " not found!");
        }

        this.ingredientService.deleteIngredient(ingredientId);
    }
}
