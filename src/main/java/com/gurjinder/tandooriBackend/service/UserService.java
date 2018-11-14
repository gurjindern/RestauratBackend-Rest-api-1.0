package com.gurjinder.tandooriBackend.service;

import com.gurjinder.tandooriBackend.DAOs.UserDao;
import com.gurjinder.tandooriBackend.model.Address;
import com.gurjinder.tandooriBackend.model.Admin;
import com.gurjinder.tandooriBackend.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    public ResultResponse<Customer> getCustomerByEmailId(String emailId){
        Customer customer=userDao.getCustomerProfileByEmailId(emailId);
        customer.setPassword(null);
        return new ResultResponse<>("success", new Date(),customer);

    }

    public ResultResponse<Customer> registerCustomer(Customer customer) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        customer.setPassword(encoder.encode(customer.getPassword()));
        return new ResultResponse<>("created",new Date(),userDao.insertCustomer(customer));

    }

    public ResultResponse  addAddress(int customerId, Address address) {
        userDao.insertAddress(customerId, address);
        return new ResultResponse("created",new Date());
    }



    // **************************************Admin Specific*****************************************//

    public ResultResponse<Admin> registerAdmin(Admin admin) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        admin.setPassword(encoder.encode(admin.getPassword()));
        return new ResultResponse<>("created",new Date(),userDao.insertAdmin(admin));
    }

    public ResultResponse<Admin> getAdminByEmailId(String emailId){
        Admin admin=userDao.getAdminProfileByEmailId(emailId);
        admin.setPassword(null);
        return new ResultResponse<>("success", new Date(),admin);

    }


}
