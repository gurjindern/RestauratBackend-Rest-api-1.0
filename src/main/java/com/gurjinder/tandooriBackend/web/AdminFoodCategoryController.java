package com.gurjinder.tandooriBackend.web;

import com.gurjinder.tandooriBackend.model.FoodCategory;
import com.gurjinder.tandooriBackend.service.FoodCategoryService;
import com.gurjinder.tandooriBackend.service.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE, path = "admin/foodcategories")
public class AdminFoodCategoryController {

    @Autowired
    FoodCategoryService service;


    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultResponse<FoodCategory>> addNewCategory(@RequestBody FoodCategory category) {

        return new ResponseEntity<>(service.addFoodCatgory(category), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResultResponse> deleteFoodCategory(@PathVariable int id){
        return new ResponseEntity<>(service.deleteFoodCategory(id), HttpStatus.OK);
    }
    @PutMapping(path="{id}", params={"name"})
    public  ResponseEntity<ResultResponse> changeName(@PathVariable  int id,String name){
        return  new ResponseEntity<>(service.changeItemName(id,name), HttpStatus.OK);
    }

    @PutMapping(path="{id}/fooditems/{itemId}")
    public ResponseEntity<ResultResponse>  addItemInCategory (@PathVariable int id,@PathVariable int itemId) {
        System.err.println();
        return new ResponseEntity<>(service.addItemInCategory(id, itemId), HttpStatus.OK);

    }

    @DeleteMapping (path="{id}/fooditems/{itemId}")
    public ResponseEntity<ResultResponse>  removeItemFormCategory (@PathVariable int id,@PathVariable int itemId) {
        System.err.println();
        return new ResponseEntity<>(service.removeItemFromCategory(id, itemId), HttpStatus.OK);

    }


}
