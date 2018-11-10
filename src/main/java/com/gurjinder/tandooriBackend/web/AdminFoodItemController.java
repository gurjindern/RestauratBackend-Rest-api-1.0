package com.gurjinder.tandooriBackend.web;

import com.gurjinder.tandooriBackend.model.FoodCategory;
import com.gurjinder.tandooriBackend.model.FoodItem;
import com.gurjinder.tandooriBackend.service.FoodItemService;
import com.gurjinder.tandooriBackend.service.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE, path = "admin/fooditems")
public class AdminFoodItemController {
    @Autowired
    private FoodItemService service;





    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultResponse<FoodItem>> addNewFoodItem(@RequestBody FoodItem foodItem) {

        return new ResponseEntity<>(service.addFoodItem(foodItem), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResultResponse> deleteFoodItem(@PathVariable int id){
        return new ResponseEntity<>(service.deleteFoodItem(id), HttpStatus.OK);
    }

    @PutMapping(path="{id}" ,params={"availability"} )
    public ResponseEntity<ResultResponse>  toggleAvailability(@PathVariable int id,boolean availability){
        System.err.println(availability);

        return new ResponseEntity<>(service.toggleItemAvailability(id,availability), HttpStatus.OK);

    }

    @PutMapping(path="{id}" ,params={"price"})
    public ResponseEntity<ResultResponse>  changePrice(@PathVariable int id,double price) {
        System.err.println(price);
        return new ResponseEntity<>(service.changePrice(id, price), HttpStatus.OK);

    }

    @PutMapping(path="{id}" ,params={"name"})
    public ResponseEntity<ResultResponse>  changeItemName (@PathVariable int id,String name) {
        System.err.println(name);
        return new ResponseEntity<>(service.changeItemName(id, name), HttpStatus.OK);

    }
    @PutMapping(path="{id}" ,params={"description"})
    public ResponseEntity<ResultResponse>  changeItemDescription (@PathVariable int id,String description) {
        System.err.println();
        return new ResponseEntity<>(service.changeItemDescription(id, description), HttpStatus.OK);

    }




}
