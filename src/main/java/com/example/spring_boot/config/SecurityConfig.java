package com.example.spring_boot.config;

import com.example.spring_boot.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//import static com.example.database_jpa.jwt.Role.ADMIN;
//import static com.example.database_jpa.jwt.Role.MANAGER;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Autowired
    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(15);
    }




        private static final String[] SWAGGER_PATHS = {
                "/swagger-ui/**",
                "/v3/api-docs/**",
                "/swagger-ui.html",
                "/swagger-resources/**",
                "/webjars/**",
                "/configuration/**",
                "/web/authors/**"
        };

        private static final String[] PUBLIC_PATHS = {
                "/register",
                "/web/login",
                "/api/web/login",
                "/api/web/register",
                "/WEB-INF/jsp/**"


        };

    private static final String[] PROTECTED_PATHS = {
            "/books/**",
            "/authors/**",
            "/web/authors/**",
            "/web/books/**",
            "/api/web/index"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(SWAGGER_PATHS).permitAll()
                        .requestMatchers(PUBLIC_PATHS).permitAll()
                        .requestMatchers("/WEB-INF/jsp/**").permitAll()
                        .requestMatchers("/favicon.ico").permitAll()
                        .requestMatchers(PROTECTED_PATHS).hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/api/web/login")
                        //.loginProcessingUrl("/api/web/login")
                        .defaultSuccessUrl("/api/web/index", true)
                        .failureUrl("/web/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/web/login")
                        .permitAll()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

//    private static final String[] PUBLIC_PATHS = {
//            "/register",
//            "/web/login",
//            "/api/web/login",
//            "/WEB-INF/jsp/**",
//            "/login",
//            "/css/**",
//            "/js/**",authoritystring
//            "/images/**"
//    };



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }
}