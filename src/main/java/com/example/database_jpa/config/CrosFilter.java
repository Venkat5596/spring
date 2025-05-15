//package com.example.database_jpa.config;
//
//import org.apache.catalina.filters.CorsFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
//
//import java.util.Arrays;
//
//@Bean
//public CorsFilter corsFilter() {
//    CorsConfiguration config = new CorsConfiguration();
//    config.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // Add your frontend URL
//    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//    config.setAllowedHeaders(Arrays.asList("*"));
//    config.setAllowCredentials(true);
//
//    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//    source.registerCorsConfiguration("/**", config);
//    return new CorsFilter(source);
//}
