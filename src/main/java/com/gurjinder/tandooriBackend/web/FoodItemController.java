package com.gurjinder.tandooriBackend.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE,path = "fooditems")
public class FoodItemController {

    public ResponseEntity<?> getFoodItems(){

        return new ResponseEntity<String>("hahahahaahaha",HttpStatus.ACCEPTED);
    }
}
