package com.codeup.runcmc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showHomePage(){
        return "user/login.html";
    }


//    @PostMapping("/")
//    String login() {
//        return "redirect:/discover";
//    }
}