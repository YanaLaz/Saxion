package com.example.pizza.entities;

import com.example.pizza.entities.views.Views;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.AllViews.class)
    private Long id;
    @JsonView(Views.AllViews.class)
    private int price;
    @JsonView(Views.AllViews.class)
    private String name;

    @OneToMany(mappedBy = "pizza", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @JsonView(Views.PizzasView.class)
    private List<Ingredient> listOfIngredients;


    @ManyToMany
    @JoinTable(
        name = "pizza_customer",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    @JsonView(Views.PizzasView.class)
    private Set<Customer> customers;

    public String getName() {
        return name;
    }
//
//    public void addIngredient(Ingredient ingredient){
//        listOfIngredients.add(ingredient);
//    }

    public Long getId() {
        return id;
    }

    public List<Ingredient> getListOfIngredients() {
        return listOfIngredients;
    }

    public int getPrice() {
        return price;
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Set<Customer> getCustomers() {
        return customers;
    }
}
