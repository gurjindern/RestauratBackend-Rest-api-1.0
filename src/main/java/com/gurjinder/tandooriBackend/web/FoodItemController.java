package com.gurjinder.tandooriBackend.web;

import com.gurjinder.tandooriBackend.model.FoodCategory;
import com.gurjinder.tandooriBackend.model.FoodItem;
import com.gurjinder.tandooriBackend.service.FoodItemService;
import com.gurjinder.tandooriBackend.service.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE,path = "fooditems")
public class FoodItemController {
    @Autowired
    private FoodItemService service;


    @GetMapping
    public ResponseEntity<ResultResponse<List<FoodItem>>> getFoodItems(){

        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        return new ResponseEntity<>(service.getFoodItems(),headers,HttpStatus.OK);
    }



    @GetMapping("categories")
    public ResponseEntity<ResultResponse<List<FoodCategory>>> getcategories(){

        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        return new ResponseEntity<>(service.getFoodCategories()
                ,headers,HttpStatus.OK);
    }


    // admin specific
    @PostMapping(consumes = APPLICATION_JSON_VALUE,path="admin/category")
    public ResponseEntity<ResultResponse<FoodCategory>> addNewCategory(@RequestBody FoodCategory category ){

        return new ResponseEntity<>(service.addFoodCatgory(category),HttpStatus.CREATED);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE,path="admin/fooditem")
    public ResponseEntity<ResultResponse<FoodItem>> addNewFoodItem(@RequestBody FoodItem foodItem ){
        List<Integer> i=foodItem.getCategoryIds();
        System.err.println(i);
        return new ResponseEntity<>(service.addFoodItem(foodItem),HttpStatus.CREATED);
    }
}
