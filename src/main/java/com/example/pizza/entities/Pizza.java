package com.example.pizza.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int price;
    private String name;

    @OneToMany(mappedBy = "pizza", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Ingredient> listOfIngredients;

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
}
