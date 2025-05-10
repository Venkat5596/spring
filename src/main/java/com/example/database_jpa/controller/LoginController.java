package com.example.database_jpa.controller;


//import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LoginController {

    @ModelAttribute("login")
    public String login(@ModelAttribute("login") String login){
        return "login";
    }
}
