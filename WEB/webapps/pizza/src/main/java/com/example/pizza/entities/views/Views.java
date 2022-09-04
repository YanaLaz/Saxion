package com.example.pizza.entities.views;

public class Views {
    /**
     * Used for things that needs to be included everywhere
      */
    public static class AllViews{}

    /**
     * Used for /Pizzas and related views
     */
    public static class PizzasView extends AllViews{}

    /**
     * Used for /Customers and related views
     */
    public static class CustomersView extends AllViews{}
}
