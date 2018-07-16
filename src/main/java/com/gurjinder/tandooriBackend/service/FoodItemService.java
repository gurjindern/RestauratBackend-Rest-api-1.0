package com.gurjinder.tandooriBackend.service;

import com.gurjinder.tandooriBackend.DAOs.FoodItemAndCategoryDao;
import com.gurjinder.tandooriBackend.model.FoodCategory;
import com.gurjinder.tandooriBackend.model.FoodItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FoodItemService {



    @Autowired
    private FoodItemAndCategoryDao itemAndCategoryDao;
    public List<FoodItem> getFoodItems() {
        return itemAndCategoryDao.getAllFoodIItems();
    }


    public List<FoodCategory>  getFoodCategories(){

        return itemAndCategoryDao.getAllCategories();
    }


}





