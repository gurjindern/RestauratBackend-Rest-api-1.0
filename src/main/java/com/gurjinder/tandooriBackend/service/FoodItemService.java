package com.gurjinder.tandooriBackend.service;

import com.gurjinder.tandooriBackend.model.FoodItem;
import com.gurjinder.tandooriBackend.model.TempDb;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FoodItemService {


    public Map<Integer, FoodItem> foodItems = TempDb.getFoodItemList();


    public List<FoodItem> getMessages() {
        return new ArrayList<FoodItem>(foodItems.values());
    }

    ;


}





