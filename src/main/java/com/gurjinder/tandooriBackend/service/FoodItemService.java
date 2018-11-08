package com.gurjinder.tandooriBackend.service;

import com.gurjinder.tandooriBackend.DAOs.FoodItemAndCategoryDao;
import com.gurjinder.tandooriBackend.model.FoodCategory;
import com.gurjinder.tandooriBackend.model.FoodItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class FoodItemService {



    @Autowired
    private FoodItemAndCategoryDao itemAndCategoryDao;




    public ResultResponse<List<FoodItem>> getFoodItems() {

        return new ResultResponse<>("sucess",new Date(),itemAndCategoryDao.getAllFoodIItems());
    }


    public ResultResponse<List<FoodCategory> > getFoodCategories(){

        return  new ResultResponse<>("success",new Date(),itemAndCategoryDao.getAllCategories());
    }



    // admin specific

    public ResultResponse<FoodCategory> addFoodCatgory(FoodCategory category){
        return new ResultResponse<>("created", new Date(),itemAndCategoryDao.addFoodCategory(category));
    }

    public ResultResponse<FoodItem> addFoodItem(FoodItem foodItem){
        return new ResultResponse<>("created", new Date(),itemAndCategoryDao.addFoodItem(foodItem));
    }
}





