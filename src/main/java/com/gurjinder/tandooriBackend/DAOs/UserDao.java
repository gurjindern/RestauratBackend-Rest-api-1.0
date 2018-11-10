package com.gurjinder.tandooriBackend.DAOs;


import com.gurjinder.tandooriBackend.exceptions.UserExistException;
import com.gurjinder.tandooriBackend.model.Address;
import com.gurjinder.tandooriBackend.model.Admin;
import com.gurjinder.tandooriBackend.model.Customer;

import com.gurjinder.tandooriBackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate template;
    private Object myLock;
    private Object myLock1;

    public UserDao() {
        myLock = new Object();
        myLock1 = new Object();
        ;

    }

    public Customer insertCustomer(Customer customer) {
        String insertCustomer = "insert into customers(id,First_name,Last_name,Phone_number,Email_id,Password) " +
                "values(customer_seq.nextVal,?,?,?,?,?)";
        synchronized (myLock) {
            String existingEmail = null;
            try {
                existingEmail = (String) template.queryForObject("select email_id from customers where email_id like ?",
                        new Object[]{customer.getEmailId()},
                        String.class);
            } catch (EmptyResultDataAccessException e) {
            }
            if (existingEmail == null) {
                template.update(insertCustomer, new Object[]{customer.getFirstName(),
                        customer.getLastName(), customer.getPhoneNumber(),
                        customer.getEmailId().toUpperCase(), customer.getPassword()});
                customer = template.queryForObject("select *  from customers where id=(select max(id) from customers)",
                        new BeanPropertyRowMapper<Customer>(Customer.class));

            } else {
                throw new UserExistException("email address already exist in records");

            }
        }
        return customer;
    }

    public void insertAddress(int customerId, Address address) {
        String insertAddress = "insert into customer_address(id,building_Number,Street_name,apt,postal_code,city,province,customer_id) " +
                "values(address_seq.nextVal,?,?,?,?,?,?,?)";

        template.update(insertAddress, new Object[]{address.getBuildingNumber(),
                address.getStreetName(), address.getApt(), address.getPostalCode(), address.getCity(),
                address.getProvince(), customerId});

    }

    public Admin insertAdmin(Admin admin) {

        String insertAdmin = "insert into admins(id,Email_id,First_name,Last_name,Phone_number,Password) " +
                "values(?,?,?,?,?,?)";
        synchronized (myLock1) {
            String existingUsername = null;
            try {
                existingUsername = (String) template.queryForObject("select email_id from admins where Email_id like ?",
                        new Object[]{admin.getEmailId()},
                        String.class);
            } catch (EmptyResultDataAccessException e) {
            }
            if (existingUsername == null) {

                int lastAdminId;
                try {
                    lastAdminId = (int) template.queryForObject("select max(id) from admins", Integer.class);
                } catch (EmptyResultDataAccessException e) {
                    lastAdminId = 0;
                } catch (NullPointerException e) {
                    lastAdminId = 0;
                }
                admin.setId(lastAdminId + 1);

                template.update(insertAdmin, new Object[]{admin.getId(),
                        admin.getEmailId().toUpperCase(), admin.getFirstName(),
                        admin.getLastName(), admin.getPhoneNumber(),
                        admin.getPassword()});


            } else {

                throw new UserExistException("username already exist in records");

            }
        }
        admin.setPassword(null);
        return admin;

    }

    // for login purpose
    public User findUser(String email_id) {
        String searchInAdmin = "select * from admins where email_id  like ?";
        String searchInCustomers = "select * from customers where email_id like ?";
        Admin admin = null;
        Customer customer = null;
        try {
            admin = (Admin) template.queryForObject(searchInAdmin, new Object[]{email_id}, new BeanPropertyRowMapper<Admin>(Admin.class));


        } catch (EmptyResultDataAccessException e) {

        }
        try {
            customer = (Customer) template.queryForObject(searchInCustomers, new Object[]{email_id}, new BeanPropertyRowMapper<>(Customer.class));
        } catch (EmptyResultDataAccessException e) {

        }
        if (admin != null) {
            admin.setRole("ROLE_ADMIN");
            return admin;
        } else if (customer != null) {
            customer.setRole("ROLE_CUSTOMER");
            return customer;
        } else
            return null;

    }


}
