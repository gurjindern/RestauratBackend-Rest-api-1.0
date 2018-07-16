package com.gurjinder.tandooriBackend.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class Order {

    private int id;
    private long timeSubmitted;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private long timeAccepted;
    private boolean accepted;
    private Customer Customer;
    private List<FoodItem> foodItems;
    private boolean cancelled;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimeSubmitted() {
        return timeSubmitted;
    }

    public void setTimeSubmitted(long timeSubmitted) {
        this.timeSubmitted = timeSubmitted;
    }

    public long getTimeAccepted() {
        return timeAccepted;
    }

    public void setTimeAccepted(long timeAccepted) {
        this.timeAccepted = timeAccepted;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public Customer getCustomer() {
        return Customer;
    }

    public void setCustomer(Customer customer) {
        Customer = customer;
    }

    public List<FoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
