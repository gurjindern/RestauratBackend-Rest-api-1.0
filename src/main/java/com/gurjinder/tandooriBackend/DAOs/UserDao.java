package com.gurjinder.tandooriBackend.DAOs;


import com.gurjinder.tandooriBackend.exceptions.NoContentException;
import com.gurjinder.tandooriBackend.exceptions.UniqueIntegrityViolationException;
import com.gurjinder.tandooriBackend.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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

    public Customer getCustomerProfileByEmailId(String email){
        Customer customer;

        try {
            customer = (Customer) template.queryForObject("select * from customers where email_id like ?", new Object[]{email.toUpperCase()},
                    new BeanPropertyRowMapper<>(Customer.class));
        }
        catch(EmptyResultDataAccessException e){
            throw new NoContentException("no user found related to this email id:: "+email);
        }
        return  customer;

    }

    public Customer insertCustomer(Customer customer) {


        String insertCustomer = "insert into customers(id,First_name,Last_name,Phone_number,Email_id,Password) " +
                "values(customer_seq.nextVal,?,?,?,?,?)";
        synchronized (customerInsertionLock) {
            String existingEmail = null;
            try {
                existingEmail = (String) template.queryForObject("select email_id from customers where email_id like ?",
                        new Object[]{customer.getEmailId().toUpperCase()},
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
                throw new UniqueIntegrityViolationException("email address already exist in records");

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

    /// admin specif

    public Admin insertAdmin(Admin admin) {

        String insertAdmin = "insert into admins(id,Email_id,First_name,Last_name,Phone_number,Password) " +
                "values(?,?,?,?,?,?)";



        synchronized (adminInsertionLock) {
            String existingUsername = null;
            try {
                existingUsername = (String) template.queryForObject("select email_id from admins where Email_id like ?",
                        new Object[]{admin.getEmailId().toUpperCase()},
                        String.class);
            } catch (EmptyResultDataAccessException e) {
            }
            if (existingUsername == null) {

               int newId=template.queryForObject("select categories_seq.nextval from dual",Integer.class);
                admin.setId(newId);

                template.update(insertAdmin, new Object[]{admin.getId(),
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
    public Admin getAdminProfileByEmailId(String email){
         Admin admin;
        try {
            admin = (Admin) template.queryForObject("select * from admin where email_id like ?", new Object[]{email.toUpperCase()},
                    new BeanPropertyRowMapper<>(Admin.class));
        }
        catch(EmptyResultDataAccessException e){
            throw new NoContentException("no user found related to this email id:: "+email);
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
