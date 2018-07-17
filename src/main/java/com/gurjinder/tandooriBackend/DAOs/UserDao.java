package com.gurjinder.tandooriBackend.DAOs;


import com.gurjinder.tandooriBackend.model.Customer;
import com.gurjinder.tandooriBackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
     private JdbcTemplate template;

    public void insertCustomer(Customer customer){
        String insertCustomer="insert into customers(id,First_name,Last_name,Phone_number,Email_id,Password) " +
                "values(customer_seq.nextVal,?,?,?,?,?)";
        template.update( insertCustomer,new Object[]{customer.getFirstName(),

        customer.getLastName(),customer.getPhoneNumber(),customer.getEmailId(),customer.getPassword()});
        }



}
