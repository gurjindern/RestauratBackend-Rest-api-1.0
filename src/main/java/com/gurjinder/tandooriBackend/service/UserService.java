package com.gurjinder.tandooriBackend.service;

import com.gurjinder.tandooriBackend.DAOs.UserDao;
import com.gurjinder.tandooriBackend.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void registerCustomer(Customer customer){
        userDao.insertCustomer(customer);

    }


}
