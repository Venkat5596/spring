package com.example.database_jpa.service;

import com.example.database_jpa.entities.Login;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    Login saveLogin(Login login);

    String verify(Login login);
}
