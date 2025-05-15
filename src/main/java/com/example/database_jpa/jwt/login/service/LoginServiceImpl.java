//package com.example.database_jpa.service;
package com.example.database_jpa.jwt.login.service;

import com.example.database_jpa.entities.Login;
import com.example.database_jpa.jwt.JWTService;
//import com.example.database_jpa.jwt.LoginRequestDto;
//import com.example.database_jpa.jwt.LoginResponseDto;
import com.example.database_jpa.jwt.login.LoginRequestDto;
import com.example.database_jpa.jwt.login.LoginResponseDto;
//import com.example.database_jpa.jwt.login.service.LoginService;
import com.example.database_jpa.repo.LoginRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final LoginRepo loginRepo;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Override
    public Login saveLogin(Login login) {
        login.setPassword(passwordEncoder.encode(login.getPassword()));
        return loginRepo.save(login);
    }

    @Override
    public LoginResponseDto verify(LoginRequestDto loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        Login login = (Login) authentication.getPrincipal();
        String token = jwtService.generateToken(login);

        return new LoginResponseDto(
                token,
                login.getId(),
                login.getUsername(),
                login.getRole().name()
        );
    }
}