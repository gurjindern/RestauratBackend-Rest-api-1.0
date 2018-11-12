package com.gurjinder.tandooriBackend.service;

import com.gurjinder.tandooriBackend.DAOs.FoodItemDao;
import com.gurjinder.tandooriBackend.model.FoodItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FoodItemService {


    @Autowired
    private FoodItemDao dao;


    public ResultResponse<List<FoodItem>> getFoodItems() {

        return new ResultResponse<>("sucess", new Date(), dao.getAllFoodIItems());
    }

    public ResultResponse<FoodItem> getFoodItemsById(int id) {

        return new ResultResponse<>("sucess", new Date(), dao.getFoodItemById(id));
    }









    // admin specific


    public ResultResponse<FoodItem> addFoodItem(FoodItem foodItem) {
        return new ResultResponse<>("created", new Date(), dao.addFoodItem(foodItem));
    }

    public ResultResponse deleteFoodItem(int itemId) {
        dao.deleteFoddItem(itemId);
        return new ResultResponse("deleted", new Date());
    }

    public ResultResponse toggleItemAvailability(int itemId,boolean availability) {
        int availabilityAsInt;
       if(availability)
           availabilityAsInt=1;
       else
           availabilityAsInt=0;

         dao.toggleAvailabilty(itemId,availabilityAsInt);
        return new ResultResponse("success", new Date());
    }

    public ResultResponse changePrice(int itemId,double price) {


        dao.changePrice(itemId,price);
        return new ResultResponse("success", new Date());
    }
    public ResultResponse changeItemName(int itemId,String name) {


        dao.changeName(itemId,name);
        return new ResultResponse("success", new Date());
    }
    public ResultResponse changeItemDescription(int itemId,String description) {


        dao.changeDescription(itemId,description);
        return new ResultResponse("success", new Date());
    }



}





