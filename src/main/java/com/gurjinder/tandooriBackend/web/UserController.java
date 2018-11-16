package com.gurjinder.tandooriBackend.web;

import com.gurjinder.tandooriBackend.DAOs.UserDao;
import com.gurjinder.tandooriBackend.model.Address;
import com.gurjinder.tandooriBackend.model.Admin;
import com.gurjinder.tandooriBackend.model.Customer;
import com.gurjinder.tandooriBackend.service.ResultResponse;
import com.gurjinder.tandooriBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "users")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private UserDao ser;

    @GetMapping(path="customers/profile")
    public ResponseEntity<ResultResponse<Customer>> getCustomerProfile(@RequestHeader(name ="Authorization") String authToken){
        System.err.println(authToken);
        ResultResponse<Customer> response=service.getCustomerProfile(authToken);
    //    System.err.println(response.getResult().toString());
        System.err.println("sgjvifjwe");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "customers")
    public ResponseEntity<ResultResponse<Customer>> registerCustomer(
            @RequestBody Customer customer) {

        return new ResponseEntity<>(service.registerCustomer(customer), HttpStatus.CREATED);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "customers/{customerId}/address")
    public ResponseEntity<ResultResponse> addAddress(@PathVariable int customerId, @RequestBody Address address) {

        service.addAddress(customerId, address);

        return new ResponseEntity<>(service.addAddress(customerId,address), HttpStatus.CREATED);
    }



    //**************************************admin specific*********************************//

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "admin")
    public ResponseEntity<ResultResponse<Admin>> resgisterAdmin(@RequestBody Admin admin) {

        return new ResponseEntity<>(service.registerAdmin(admin), HttpStatus.CREATED);

    }
    @GetMapping(path="admin/profile" )
    public ResponseEntity<ResultResponse<Admin>> getAdminProfile(@RequestHeader(name ="Authorization") String authToken){
        return new ResponseEntity<>(service.getAdminProfile(authToken),HttpStatus.OK);
    }



    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "demo")
    public ResponseEntity<?> demo() {

        System.err.println("i am here");
        return new ResponseEntity<>(ser.findUser("GURJINDERN74"), HttpStatus.OK);
        //  return new ResponseEntity<>(new MyUserDetail(ser).loadUserByUsername("wolverine"),HttpStatus.OK);
        // return new ResponseEntity<>(new MyUserDetail().loadUserByUsername(""),HttpStatus.OK);
    }


}
