package com.gurjinder.tandooriBackend.web;

import com.gurjinder.tandooriBackend.model.FoodCategory;
import com.gurjinder.tandooriBackend.model.FoodItem;
import com.gurjinder.tandooriBackend.service.FoodCategoryService;
import com.gurjinder.tandooriBackend.service.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE, path = "foodcategories")
public class FoodCategoryController {
    @Autowired
    FoodCategoryService service;

    @GetMapping
    public ResponseEntity<ResultResponse<List<FoodCategory>>> getcategories() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        return new ResponseEntity<>(service.getFoodCategories()
                , headers, HttpStatus.OK);
    }

    @GetMapping(path = "{id}/fooditems")
    public ResponseEntity<ResultResponse<List<FoodItem>>> getFoodItemsInCategory(@PathVariable int id) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        return new ResponseEntity<>(service.getItemsInCategory(id), headers, HttpStatus.OK);
    }

}
