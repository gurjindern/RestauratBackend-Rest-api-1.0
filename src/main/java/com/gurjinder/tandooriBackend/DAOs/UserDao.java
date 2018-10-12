package com.gurjinder.tandooriBackend.DAOs;


import com.gurjinder.tandooriBackend.exceptions.UserExistException;
import com.gurjinder.tandooriBackend.model.Address;
import com.gurjinder.tandooriBackend.model.Admin;
import com.gurjinder.tandooriBackend.model.Customer;

import com.gurjinder.tandooriBackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
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
        myLock=new Object();
        myLock1=new Object();;

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
            } catch (Exception e) {
            }
            if (existingEmail == null) {
                template.update(insertCustomer, new Object[]{customer.getFirstName(),
                        customer.getLastName(), customer.getPhoneNumber(),
                        customer.getEmailId(), customer.getPassword()});
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
                address.getStreetName(),address.getApt(), address.getPostalCode(), address.getCity(),
                address.getProvince(), customerId});

    }

    public Admin insertAdmin(Admin admin){

        String insertAdmin = "insert into admins(id,admin_Username,First_name,Last_name,Phone_number,Password) " +
                "values(?,?,?,?,?,?)";
        synchronized (myLock1) {
            String existingUsername = null;
            try {
                existingUsername = (String) template.queryForObject("select admin_Username from admins where admin_Username like ?",
                        new Object[]{admin.getAdminUsername()},
                        String.class);
            } catch (Exception e) {
            }
            if (existingUsername == null) {

                int lastAdminId=(int)template.queryForObject("select max(id) from admins",Integer.class);
                admin.setId(lastAdminId+1);
                template.update(insertAdmin, new Object[]{admin.getId(),
                        admin.getAdminUsername(),admin.getFirstName(),
                        admin.getLastName(), admin.getPhoneNumber(),
                        admin.getPassword()});


            } else {

                throw new UserExistException("username already exist in records");

            }
        }
        return admin;

    }
    //for customers email id is username
    public User  findUser(String userName){
        String searchInAdmin="select * from admins where admin_Username like ?";
        String searchInCustomers="select * from customers where email_id like ?";
        Admin admin=null;
        Customer customer=null;
        try{
            admin=(Admin)template.queryForObject(searchInAdmin,new Object[]{userName}, new BeanPropertyRowMapper<Admin>(Admin.class));
            customer=(Customer) template.queryForObject(searchInCustomers,new Object[]{userName}, new BeanPropertyRowMapper<>(Customer.class));

        }catch(Exception e){

        }
        if(admin!=null){
            admin.setRole("ADMIN");
            return admin;}
        else if(customer!=null) {
           return customer;
        }
        else
            return null;

    }


}
