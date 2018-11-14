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

    @GetMapping(path="customers/profile" ,params = {"emailId"})
    public ResponseEntity<ResultResponse<Customer>> getCustomerProfile(String emailId){
        return new ResponseEntity<>(service.getCustomerByEmailId(emailId),HttpStatus.OK);
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
    @GetMapping(path="admin/profile" ,params = {"emailId"})
    public ResponseEntity<ResultResponse<Admin>> getAdminProfile(String emailId){
        return new ResponseEntity<>(service.getAdminByEmailId(emailId),HttpStatus.OK);
    }


/*
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "demo")
    public ResponseEntity<?> demo() {

        System.err.println("i am here");
        return new ResponseEntity<>(ser.findUser("wolverine"), HttpStatus.OK);
        //  return new ResponseEntity<>(new MyUserDetail(ser).loadUserByUsername("wolverine"),HttpStatus.OK);
        // return new ResponseEntity<>(new MyUserDetail().loadUserByUsername(""),HttpStatus.OK);
    }

*/
}
