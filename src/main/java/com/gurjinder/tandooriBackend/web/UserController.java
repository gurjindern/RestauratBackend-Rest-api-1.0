package com.gurjinder.tandooriBackend.web;

import com.gurjinder.tandooriBackend.model.Customer;
import com.gurjinder.tandooriBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(produces =MediaType.APPLICATION_JSON_VALUE,path="users")
public class UserController {
    @Autowired
    private UserService service;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,path="registerCustomer")
    public ResponseEntity<Response> registerCustomer(@RequestBody Customer customer){
        service.registerCustomer(customer);
        return new ResponseEntity<>(new Response(){
            private String status="user created";

            public String getStatus() {
                return status;
            }
        },HttpStatus.CREATED);
    }

}
