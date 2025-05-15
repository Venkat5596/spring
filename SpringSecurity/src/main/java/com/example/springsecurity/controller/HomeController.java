package com.example.springsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {


    @GetMapping("/")
    public ResponseEntity<String> helloWorld(){
        return ResponseEntity.ok("Hello world");
    }

    @GetMapping("/about")
    public ResponseEntity<String> about(){
        return ResponseEntity.ok("this contact page");
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public ResponseEntity<String> user(){
        return ResponseEntity.ok("this is user page");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<String> admin(){
        return ResponseEntity.ok("this is admin page");

    }
}
