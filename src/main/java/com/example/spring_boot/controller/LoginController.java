package com.example.spring_boot.controller;


//import org.springframework.context.annotation.Configuration;
import com.example.spring_boot.entities.Login;
import com.example.spring_boot.jwt.login.LoginRequestDto;
import com.example.spring_boot.jwt.login.service.LoginService;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

   private final LoginService loginService;
   private final com.example.spring_boot.jwt.JWTService jwtService;

   public LoginController(LoginService loginService, com.example.spring_boot.jwt.JWTService jwtService){
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



