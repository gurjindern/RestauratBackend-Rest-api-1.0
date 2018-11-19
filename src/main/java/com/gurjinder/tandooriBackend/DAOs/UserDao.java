package com.gurjinder.tandooriBackend.DAOs;


import com.gurjinder.tandooriBackend.exceptions.UniqueIntegrityViolationException;
import com.gurjinder.tandooriBackend.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate template;
    private Object customerInsertionLock;
    private Object adminInsertionLock;

    public UserDao() {
        customerInsertionLock = new Object();
        adminInsertionLock = new Object();

    }

    public Customer getCustomerProfile(String username){
        Customer customer;

        try {
            customer = (Customer) template.queryForObject("select * from customers where email_id like ?",
                    new Object[]{username},
                    new BeanPropertyRowMapper<>(Customer.class));
        }
        catch(EmptyResultDataAccessException e){
            customer=null;
        }
        return  customer;

    }

    public Customer insertCustomer(Customer customer) {


        String insertCustomer = "insert into customers(id,unique_identifier,First_name,Last_name,Phone_number,Email_id,Password) " +
                "values(?,?,?,?,?,?,?)";
        synchronized (customerInsertionLock) {
            String existingEmail = null;
            try {
                existingEmail = (String) template.queryForObject("select email_id from customers where email_id like ?",
                        new Object[]{customer.getEmailId().toUpperCase()},
                        String.class);
            } catch (EmptyResultDataAccessException e) {
            }

            int newPrimaryId=template.queryForObject("select customer_seq.nextval from dual",Integer.class);

            if (existingEmail == null) {
                template.update(insertCustomer, new Object[]{newPrimaryId,customer.getId(),customer.getFirstName(),
                        customer.getLastName(), customer.getPhoneNumber(),
                        customer.getEmailId().toUpperCase(), customer.getPassword()});


            } else {
                throw new UniqueIntegrityViolationException("email address already exist in records");

            }
        }
        customer.setPassword(null);
        return customer;
    }

    public void insertAddress(String customerId, Address address) {
        String insertAddress = "insert into customer_address(id,building_Number,Street_name,apt,postal_code,city,province,customer_id) " +
                "values(address_seq.nextVal,?,?,?,?,?,?,(select id from customers where unique_identifier like ?))";

        template.update(insertAddress, new Object[]{address.getBuildingNumber(),
                address.getStreetName(), address.getApt(), address.getPostalCode(), address.getCity(),
                address.getProvince(), customerId});

    }
    public List<Address> getAddresses(String customerId){
        String stmt="select * from customer_address where customer_id=(select id from customers where unique_identifier like ?)";
        return template.query(stmt,new Object[]{customerId},new BeanPropertyRowMapper<>(Address.class));

    }

    /// admin specif

    public Admin insertAdmin(Admin admin) {

        String insertAdmin = "insert into admins(id,Email_id,First_name,Last_name,Phone_number,Password) " +
                "values(?,?,?,?,?,?,?)";



        synchronized (adminInsertionLock) {
            String existingUsername = null;
            try {
                existingUsername = (String) template.queryForObject("select email_id from admins where Email_id like ?",
                        new Object[]{admin.getEmailId().toUpperCase()},
                        String.class);
            } catch (EmptyResultDataAccessException e) {
            }
            if (existingUsername == null) {

               int newPrimaryId=template.queryForObject("select admin_seq.nextval from dual",Integer.class);


                template.update(insertAdmin, new Object[]{newPrimaryId,admin.getId(),
                        admin.getEmailId().toUpperCase(), admin.getFirstName(),
                        admin.getLastName(), admin.getPhoneNumber(),
                        admin.getPassword()});


            } else {

                throw new UniqueIntegrityViolationException("user with email id  already exists");

            }
        }
        admin.setPassword(null);
        return admin;

    }
    public Admin getAdminProfile(String username){
         Admin admin;
        try {
            admin = (Admin) template.queryForObject("select * from admin where email_id like ?", new Object[]{username.toUpperCase()},
                    new BeanPropertyRowMapper<>(Admin.class));
        }
        catch(EmptyResultDataAccessException e){

            admin=null;
        }
        return  admin;

    }
















    // for login purpose
    public RegisteredUser findUser(String email_id) {
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
