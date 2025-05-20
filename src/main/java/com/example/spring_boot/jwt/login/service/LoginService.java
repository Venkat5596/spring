package com.example.spring_boot.jwt.login.service;

import com.example.spring_boot.entities.Login;
import com.example.spring_boot.jwt.login.LoginRequestDto;
import com.example.spring_boot.jwt.login.LoginResponseDto;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    Login saveLogin(Login login);

    LoginResponseDto verify(LoginRequestDto login);

    Login findByUsername(@NotBlank String username);
}
