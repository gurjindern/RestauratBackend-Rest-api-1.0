package com.gurjinder.tandooriBackend.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE,path = "orders")
public class OrderController {
    @GetMapping
    public String getAllOrders(){
        return "{all orders will come from here}";
    }


}
