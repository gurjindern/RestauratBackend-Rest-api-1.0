package com.gurjinder.tandooriBackend.web;

import com.gurjinder.tandooriBackend.model.Order;
import com.gurjinder.tandooriBackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE,path = "orders")
public class OrderController {
    @Autowired
    OrderService service;

    @GetMapping
    public String getAllOrders(){
        return "{all orders will come from here}";
    }


    @PostMapping(consumes=APPLICATION_JSON_VALUE,path="{customerId}/submit")
    public ResponseEntity<Response>  submitOrder(@PathVariable int customerId,@RequestBody Order order){

        return  new ResponseEntity<>(new Response() {
            private String status="order accepted";

            private Order sumittedOrder=service.submitOrder(customerId, order);

            public String getStatus() {
                return status;
            }

            public Order getSumittedOrder() {
                return sumittedOrder;
            }
        },HttpStatus.ACCEPTED);
    };
}
