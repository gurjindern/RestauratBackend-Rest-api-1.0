package com.gurjinder.tandooriBackend.myConfigurations;

import com.gurjinder.tandooriBackend.DAOs.UserDao;
import com.gurjinder.tandooriBackend.model.RegisteredUser;
import com.gurjinder.tandooriBackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class MyUserDetail implements UserDetailsService {


    private UserDao userDao;

    public MyUserDetail(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {

        RegisteredUser user = userDao.findUser(emailId.toUpperCase());
        System.err.println("i am running");
        if (user != null) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole().toUpperCase()));

            return new org.springframework.security.core.userdetails.User(user.getEmailId(),
                    user.getPassword(), authorities);
        }
        throw new UsernameNotFoundException("user with email_id " + emailId + " not found");
    }
}
