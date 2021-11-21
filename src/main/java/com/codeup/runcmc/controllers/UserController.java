package com.codeup.runcmc.controllers;

import com.codeup.runcmc.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/register")
    String showRegisterPage(Model model){
        model.addAttribute("user", new User());
        return "/register.html";
    }

    @GetMapping("/profile")
	String showProfile(){
    	return "/user/profile.html";
    }
}