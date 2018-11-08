package com.gurjinder.tandooriBackend.web;

import com.gurjinder.tandooriBackend.DAOs.UserDao;
import com.gurjinder.tandooriBackend.model.Address;
import com.gurjinder.tandooriBackend.model.Admin;
import com.gurjinder.tandooriBackend.model.Customer;
import com.gurjinder.tandooriBackend.myConfigurations.MyUserDetail;
import com.gurjinder.tandooriBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(produces =MediaType.APPLICATION_JSON_VALUE,path="users")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private UserDao ser;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,path="registerCustomer")
    public ResponseEntity<Response> registerCustomer(
            @RequestBody Customer customer){

        return new ResponseEntity<>(new Response(){
            private String status="user created";
            private Customer createdCustomer=service.registerCustomer(customer);
            public String getStatus() {
                return status;
            }

            public Customer getCreatedCustomer() {
                return createdCustomer;
            }
        },HttpStatus.CREATED);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,path="{customerId}/addAddress")
    public ResponseEntity<Response> addAddress(@PathVariable int customerId, @RequestBody Address address){

        service.addAddress(customerId, address);

        return new ResponseEntity<>(new Response() {
            private String status="Address Added";

            public String getStatus() {
                return status;
            }
        },HttpStatus.CREATED);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,path="admin/register")
    public ResponseEntity<Response> resgisterAdmin(@RequestBody Admin admin){

        return new ResponseEntity<>(new Response(){
            private String status="user created";
            private Admin createdAdmin=service.registerAdmin(admin);
            public String getStatus() {
                return status;
            }

            public Admin getCreatedAdmin() {
                return createdAdmin;
            }
        },HttpStatus.CREATED);

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,path="demo")
    public ResponseEntity<?> demo(){

        System.err.println("i am here");
        return new ResponseEntity<>(ser.findUser("wolverine"),HttpStatus.OK);
     //  return new ResponseEntity<>(new MyUserDetail(ser).loadUserByUsername("wolverine"),HttpStatus.OK);
       // return new ResponseEntity<>(new MyUserDetail().loadUserByUsername(""),HttpStatus.OK);
    }



}
