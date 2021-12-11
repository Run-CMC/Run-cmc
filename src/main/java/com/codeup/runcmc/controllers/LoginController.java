package com.codeup.runcmc.controllers;

import com.codeup.runcmc.models.User;
import com.codeup.runcmc.repositories.UserRepository;
import com.mysql.cj.log.Log;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @GetMapping("/")
    public String showHomePage(){
        return "index";
    }

    @GetMapping("/aboutus")
    public String showAboutUsPage(){
        return "aboutus";
    }
}