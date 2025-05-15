package com.example.database_jpa.service;

import com.example.database_jpa.entities.Login;
import com.example.database_jpa.jwt.JWTService;
import com.example.database_jpa.repo.LoginRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class LoginServiceImpl implements LoginService {
    private final LoginRepo loginRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authManager;
    private final JWTService jwtService;
    public LoginServiceImpl(LoginRepo loginRepo, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationManager authManager, JWTService jwtService) {
        this.loginRepo = loginRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    @Override
    public Login saveLogin(Login login) {
        login.setPassword(bCryptPasswordEncoder.encode(login.getPassword()));
        return loginRepo.save(login);
    }

    @Override
    public String verify(Login login) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(),login.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(login.getUsername());

        }
        else{
            return ("Invalid Credentials");
        }

    }
}
