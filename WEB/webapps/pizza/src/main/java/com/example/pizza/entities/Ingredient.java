package com.example.pizza.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int kcal;
    private double price;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    @JsonBackReference
    private Pizza pizza;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public int getKcal() {
        return kcal;
    }
}
