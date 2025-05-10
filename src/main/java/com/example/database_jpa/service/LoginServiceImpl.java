package com.example.database_jpa.service;

import com.example.database_jpa.repo.LoginRepo;

public class LoginServiceImpl implements LoginService {
    private final LoginRepo loginRepo;
    public LoginServiceImpl(LoginRepo loginRepo) {
        this.loginRepo = loginRepo;
    }
}
