package com.gurjinder.tandooriBackend.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;

public class FoodCategory {
    private int id;
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description ;

    public FoodCategory(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public FoodCategory() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




}
