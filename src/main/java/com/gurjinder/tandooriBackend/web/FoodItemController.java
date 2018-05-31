package com.gurjinder.tandooriBackend.web;

import com.gurjinder.tandooriBackend.model.FoodItem;
import com.gurjinder.tandooriBackend.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE,path = "fooditems")
public class FoodItemController {
    @Autowired
    private FoodItemService service;
    @GetMapping
    public ResponseEntity<?> getFoodItems(){

        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        return new ResponseEntity<List<FoodItem>>(service.getMessages(),headers,HttpStatus.OK);
    }
}
