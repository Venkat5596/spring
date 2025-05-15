package com.example.database_jpa.jwt;

import com.example.database_jpa.jwt.login.MyLoginService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {


    private JWTService jwtService;
    private MyLoginService loginService;


    public JwtFilter(JWTService jwtService, MyLoginService loginService) {
        this.jwtService = jwtService;
        this.loginService = loginService;
    }



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();

        if(path.contains("/web/login") || path.contains("/register")|| path.contains("/login")){
            filterChain.doFilter(request,response);
            return;
        }

        String auth = request.getHeader("Authorization");
        if(auth == null || !auth.startsWith("Bearer ")){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Unauthorized");
            filterChain.doFilter(request,response);
            return;
        }

        String token = auth.substring(7);
        String username = jwtService.extractUsername(token);

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = loginService.loadUserByUsername(username);

            if(jwtService.validateToken(token,userDetails)) {
                UsernamePasswordAuthenticationToken tokenAuth =
                        new UsernamePasswordAuthenticationToken(userDetails, userDetails, null);
                tokenAuth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(tokenAuth);
                filterChain.doFilter(request, response);
            }
            else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Unauthorized");

            }
        }
    }
}
