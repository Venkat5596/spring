package com.example.spring_boot.controller;

import com.example.spring_boot.entities.Login;
import com.example.spring_boot.jwt.JWTService;
import com.example.spring_boot.jwt.login.LoginRequestDto;
import com.example.spring_boot.jwt.login.LoginResponseDto;
import com.example.spring_boot.jwt.login.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/web")

public class IndexController {

    private final LoginService loginService;
    private final JWTService jwtService;
    //private final LoginService loginService1;

    public IndexController(LoginService loginService, JWTService jwtService) {
        this.loginService = loginService;
        this.jwtService = jwtService;
        //this.loginService1 = loginService1;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Returns login.jsp or login.html
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequestDto loginRequest, HttpSession session, Model model) {
        LoginResponseDto loginResponse = loginService.verify(loginRequest);

        if (loginResponse == null) { // Authentication failed
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }



        // Retrieve user details from DB
        Login login = loginService.findByUsername(loginRequest.getUsername());

        // Generate JWT token
        String token = jwtService.generateToken(login);

        // Store token in session for frontend access
        session.setAttribute("jwtToken", "Bearer " + token);

        model.addAttribute("message", "User Logged in successfully");
        return "index"; // Redirect after login
    }

    @GetMapping("/index")
    public String index(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            return "index";
        }
        return "login";

    }

    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }

@PostMapping("/register")
    public String register(@ModelAttribute Login login){
        Login saved = loginService.saveLogin(login);

        return "redirect:/api/web/login";
    }

}

//package com.example.database_jpa.controller;
//
//import com.example.database_jpa.entities.Login;
//import com.example.database_jpa.jwt.JWTService;
//import com.example.database_jpa.jwt.login.LoginRequestDto;
//import com.example.database_jpa.jwt.login.LoginResponseDto;
//import com.example.database_jpa.jwt.login.service.LoginService;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/api/web")
//public class IndexController {
//
//    private final LoginService loginService;
//
//    private final JWTService jwtService;
//
//    public IndexController(LoginService loginService, JWTService jwtService) {
//        this.loginService = loginService;
//        this.jwtService = jwtService;
//    }
//
//    @GetMapping("/login")
//    public String loginPage() {
//        return "login"; // Returns login.jsp or login.html
//    }
//
//    @PostMapping("/login")
//    public String login(@ModelAttribute LoginRequestDto loginRequest, HttpSession session, Model model) {
//        LoginResponseDto loginResponse = loginService.verify(loginRequest);
//
//        if (loginResponse == null) { // Authentication failed
//            model.addAttribute("error", "Invalid credentials");
//            return "login"; // Stay on the login page
//        }
//
//        // Generate token & store it in session
//
//        Login login = loginService.findByUsername(loginRequest.getUsername());
//        String token = jwtService.generateToken(login);
//
//
//        session.setAttribute("jwtToken", "Bearer " + token);
//
//
//        model.addAttribute("message", "User Logged in successfully");
//        return "index"; // Redirect to home page after login
//    }
//    @GetMapping("/index")
//    public String index() {
//        return "index";  // This will look for index.jsp in /WEB-INF/jsp/
//    }
//
//}