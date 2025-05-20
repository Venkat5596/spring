package com.example.database_jpa.controller;


//import org.springframework.context.annotation.Configuration;
import com.example.database_jpa.entities.Login;
import com.example.database_jpa.jwt.login.LoginRequestDto;
import com.example.database_jpa.jwt.login.service.LoginService;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

   private final LoginService loginService;
   private final com.example.database_jpa.jwt.JWTService jwtService;

   public LoginController(LoginService loginService, com.example.database_jpa.jwt.JWTService jwtService){
       this.loginService=loginService;
       this.jwtService = jwtService;
   }

       @PostMapping("/register")
       public ResponseEntity<String> Register(@RequestBody Login login){
           Login saved = loginService.saveLogin(login);
           return ResponseEntity.ok("User Registered");
       }
       @PostMapping("/web/login")
    public ResponseEntity<String> Login(@RequestBody LoginRequestDto loginRequest){

               Login login = loginService.findByUsername(loginRequest.getUsername());
               String token = jwtService.generateToken(login);
               return ResponseEntity.ok()
                       .header("Authorization", "Bearer " + token)
                       .body("Login successful");

//return ResponseEntity.badRequest().body("Invalid credentials");
       }

}



