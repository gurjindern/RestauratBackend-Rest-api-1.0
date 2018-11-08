package com.gurjinder.tandooriBackend.service;

import com.gurjinder.tandooriBackend.DAOs.UserDao;
import com.gurjinder.tandooriBackend.model.Address;
import com.gurjinder.tandooriBackend.model.Admin;
import com.gurjinder.tandooriBackend.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public Customer registerCustomer(Customer customer){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        customer.setPassword(encoder.encode(customer.getPassword()));
        return userDao.insertCustomer(customer);

    }

    public void addAddress(int customerId, Address address){

        userDao.insertAddress(customerId,address);
    }

    public Admin registerAdmin(Admin admin){
       BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
       admin.setPassword(encoder.encode(admin.getPassword()));
        return userDao.insertAdmin(admin);

    }




}
