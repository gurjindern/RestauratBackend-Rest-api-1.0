package com.gurjinder.tandooriBackend.model;

import java.util.HashMap;
import java.util.Map;

public class TempDb {

    public static Map<Integer,FoodItem> foodItemList=new HashMap<>();

    static{
        foodItemList.put(1,new FoodItem(1,"butter_chicken",17.00,"comes with Rice, Naan",true));
        foodItemList.put(2,new FoodItem(1,"agneu korma",20.00,"comes with Rice, ",true));
        foodItemList.put(3,new FoodItem(1,"butter_chicken combo",30.00,"comes with Rice, Naan, onion bhaaji,butter chicken",true));
    }
    public static Map<Integer, FoodItem> getFoodItemList() {
        return foodItemList;
    }

    public static void setFoodItemList(Map<Integer, FoodItem> foodItemList) {
        TempDb.foodItemList = foodItemList;
    }
}
