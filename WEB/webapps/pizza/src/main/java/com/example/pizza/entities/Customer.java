package com.example.pizza.entities;

import com.example.pizza.entities.views.Views;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.AllViews.class)
    private Long id;
    @JsonView(Views.AllViews.class)
    private String name;
    @JsonView(Views.AllViews.class)
    private String address;
    @JsonView(Views.AllViews.class)
    private String phoneNumber;

    @ManyToMany(mappedBy = "customers")
    @JsonView(Views.CustomersView.class)
    private Set<Pizza> customersPizza;

    public Set<Pizza> getCustomersPizza() {
        return customersPizza;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}
