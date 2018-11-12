package com.gurjinder.tandooriBackend.model;


import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class FoodItem implements Cloneable {

    private int id;
    private String name;
    private Double price;
    private String description;
    private List<Integer> categoryIds;
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private boolean availabily;


    public FoodItem() {
    }

    public FoodItem(int id, String name, double price, String description, boolean availabily) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.availabily = availabily;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public boolean isAvailabily() {
        return availabily;
    }

    public void setAvailabily(boolean availabily) {
        this.availabily = availabily;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
