package com.example.database_jpa.jwt.login.service;

import com.example.database_jpa.entities.Login;
import com.example.database_jpa.jwt.login.LoginRequestDto;
import com.example.database_jpa.jwt.login.LoginResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    Login saveLogin(Login login);

    LoginResponseDto verify(LoginRequestDto login);
}
