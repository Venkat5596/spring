package com.example.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // enable @PreAuthorize
public class Configuration1 {

    private final DataSource dataSource;

    @Autowired
    public Configuration1(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/about/**").permitAll()
                        .requestMatchers("/user").hasRole("USER")
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                // .formLogin(withDefaults()) // optional for browser
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        http.headers(headers ->
                headers.frameOptions(frameOptions -> frameOptions.sameOrigin())
        );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);

        userDetailsManager.deleteUser("user2");
        userDetailsManager.deleteUser("admin1");

        if (!userDetailsManager.userExists("user2")) {
            userDetailsManager.createUser(User.withUsername("user2")
                    .password(passwordEncoder().encode("12345"))
                    .roles("USER")
                    .build());
        }

        if (!userDetailsManager.userExists("admin1")) {
            userDetailsManager.createUser(User.withUsername("admin1")
                    .password(passwordEncoder().encode("12345"))
                    .roles("ADMIN")
                    .build());
        }

        return userDetailsManager;
    }
}










//package com.example.springsecurity.config;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Configuration;
//
////import static jdk.internal.org.jline.utils.InfoCmp.Capability.user1;
//import javax.sql.DataSource;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//@EnableWebSecurity
//public class Configuration1 {
//
//
//
//    private DataSource dataSource;
//
//    @Autowired
//    Configuration1(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
////    @Bean
////    public DataSource dataSource() {
////        return DataSourceBuilder.create()
////                .url("jdbc:h2:mem:testdb")
////                .driverClassName("org.h2.Driver")
////                .username("sa")
////                .password("")
////                .build();
////    }
//
//    @Bean
//    SecurityFilterChain filterChainDemo(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(requests -> requests
//                        .requestMatchers("/about/**").permitAll()
//                        .requestMatchers("/user").hasRole("USER")
//                        .requestMatchers("/admin").hasRole("ADMIN")
//                        .requestMatchers("/").authenticated()
//                )
//                .httpBasic(withDefaults())
////                .formLogin(withDefaults())
//                .sessionManagement(session ->
//                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                );
//
//        http.headers(headers ->
//                headers.frameOptions(frameOptions-> frameOptions.sameOrigin() ));
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//
//
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
//
//        if (!userDetailsManager.userExists("user1")) {
//            UserDetails user1 = User.withUsername("user1")
//                    .password(passwordEncoder().encode("12345"))
//                    .roles("USER")
//                    .build();
//            userDetailsManager.createUser(user1);
//        }
//
//        if (!userDetailsManager.userExists("admin")) {
//            UserDetails admin = User.withUsername("admin")
//                    .password(passwordEncoder().encode("12345"))
//                    .roles("ADMIN")
//                    .build();
//            userDetailsManager.createUser(admin);
//        }
//
//        return userDetailsManager;
//    }
//
//
//
//    // return new InMemoryUserDetailsManager(user1,admin);
//
//
//    }
//
//
//
//
////@Bean
////public PasswordEncoder passwordEncoder() {
////    return new BCryptPasswordEncoder();
////}
////
////@Bean
////public InMemoryUserDetailsManager userDetails() {
////    UserDetails admin = User.withUsername("admin")
////            .username("admin")
////            .password("12345")
////            .roles("ADMIN")
////            .build();
////
////    UserDetails user = User.withUsername("user")
////            .username("user")
////            .password("12345")
////            .roles("USER")
////            .build();
////
////    return new InMemoryUserDetailsManager(admin, user);


////}
////}