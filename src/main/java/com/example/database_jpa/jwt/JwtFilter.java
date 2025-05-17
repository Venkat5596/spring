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
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final MyLoginService loginService;

    public JwtFilter(JWTService jwtService, MyLoginService loginService) {
        this.jwtService = jwtService;
        this.loginService = loginService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String path = request.getRequestURI();
        
        // Skip authentication for public paths
        if (shouldSkipAuthentication(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Try to get JWT token
        String token = extractToken(request);
        
        try {
            if (token != null) {
                // Process JWT token
                processJwtToken(token, request);
            } else if (!isAuthenticated()) {
                // If no token and not authenticated, check if it's an API call
                if (isApiRequest(request)) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid token");
                    return;
                }
            }
        } catch (Exception e) {
            log.error("Authentication error: ", e);
            if (isApiRequest(request)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private void processJwtToken(String token, HttpServletRequest request) {
        String username = jwtService.extractUsername(token);
        if (username != null && !isAuthenticated()) {
            UserDetails userDetails = loginService.loadUserByUsername(username);
            if (jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
    }

    private boolean isAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication() != null &&
               SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }

    private boolean isApiRequest(HttpServletRequest request) {
        String accept = request.getHeader("Accept");
        return accept != null && accept.contains("application/json");
    }

    private boolean shouldSkipAuthentication(String path) {
        return path.contains("/web/login") ||
               path.contains("/register") ||
               path.contains("/api/web/login") ||
               path.contains("/swagger-ui") ||
               path.contains("/swagger-resources") ||
               path.contains("/v3/api-docs") ||
               path.contains("/authors") ||
               path.contains("/favicon.ico") ||
               path.contains("/css/") ||
               path.contains("/js/") ||
               path.contains("/images/");
    }
}