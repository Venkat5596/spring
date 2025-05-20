package com.example.spring_boot.jwt;

import com.example.spring_boot.entities.Login;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JWTService jwtService;

    public JwtAuthenticationSuccessHandler(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        Login user = (Login) authentication.getPrincipal();
        String token = jwtService.generateToken(user);
        response.setHeader("Authorization", "Bearer " + token);
        response.sendRedirect("/api/web/index");
    }
}