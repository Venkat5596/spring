package com.example.database_jpa.jwt;

import com.example.database_jpa.entities.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
//import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
//import java.util.List;


public class LoginDetailsPrincipal implements UserDetails {


    private final Login login;

    public LoginDetailsPrincipal(Login login) {
        this.login = login;
    }





    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return login.getPassword();
    }

    @Override
    public String getUsername() {
        return login.getUsername();
    }
}
