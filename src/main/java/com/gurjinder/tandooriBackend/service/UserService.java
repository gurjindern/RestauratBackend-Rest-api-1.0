package com.gurjinder.tandooriBackend.service;

import com.gurjinder.tandooriBackend.DAOs.UserDao;
import com.gurjinder.tandooriBackend.model.Address;
import com.gurjinder.tandooriBackend.model.Admin;
import com.gurjinder.tandooriBackend.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    public ResultResponse<Customer> getCustomerProfile(String authToken){

        String username=MyUtilities.getUsersnameFormAuthToken(authToken);


        Customer customer=userDao.getCustomerProfile(username);
        customer.setPassword(null);
        return new ResultResponse<>("success", new Date(),customer);

    }

    public ResultResponse<Customer> registerCustomer(Customer customer) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        customer.setPassword(encoder.encode(customer.getPassword()));
        UUID uuid = UUID.randomUUID();
        customer.setId(uuid.toString());
        return new ResultResponse<>("created",new Date(),userDao.insertCustomer(customer));

    }

    public ResultResponse  addAddress(String customerId, Address address) {

        userDao.insertAddress(customerId, address);
        return new ResultResponse("created",new Date());
    }

    public ResultResponse<List<Address>> getAddresses(String cutomerId){

        return new ResultResponse<>("success",new Date(),userDao.getAddresses(cutomerId));
    }



    // **************************************Admin Specific*****************************************//

    public ResultResponse<Admin> registerAdmin(Admin admin) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        admin.setPassword(encoder.encode(admin.getPassword()));
        UUID uuid = UUID.randomUUID();
        admin.setId(uuid.toString());

        return new ResultResponse<>("created",new Date(),userDao.insertAdmin(admin));
    }

    public ResultResponse<Admin> getAdminProfile(String authToken){
        String username=MyUtilities.getUsersnameFormAuthToken(authToken);
        Admin admin=userDao.getAdminProfile(username);
        admin.setPassword(null);
        return new ResultResponse<>("success", new Date(),admin);


    }


}
