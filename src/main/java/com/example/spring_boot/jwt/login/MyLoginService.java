package com.example.spring_boot.jwt.login;

import com.example.spring_boot.entities.Login;
import com.example.spring_boot.repo.LoginRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyLoginService implements UserDetailsService {

    private final LoginRepo loginRepo;

    public MyLoginService(LoginRepo loginRepo) {
        this.loginRepo = loginRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Login> login = loginRepo.findByUsername(username);
        return login.map(Login::new)
                .orElseThrow(() -> new UsernameNotFoundException("User with username '" + username + "' not found."));
    }
}