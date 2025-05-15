package com.example.database_jpa.controller;


//import org.springframework.context.annotation.Configuration;
import com.example.database_jpa.entities.Login;
import com.example.database_jpa.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

   private final LoginService loginService;

   public LoginController(LoginService loginService){
       this.loginService=loginService;
   }

       @PostMapping("/register")
       public ResponseEntity<String> Register(@RequestBody Login login){
           Login saved = loginService.saveLogin(login);
           return ResponseEntity.ok("User Registered");
       }
       @PostMapping("/web/login")
    public ResponseEntity<String> Login(@RequestBody Login login){

       return ResponseEntity.ok("User Logged in : "+loginService.verify(login));

       }
   }

