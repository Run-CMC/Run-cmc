package com.codeup.runcmc.controllers;

import com.codeup.runcmc.models.User;
import com.codeup.runcmc.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    String showRegisterPage(Model model){
        model.addAttribute("user", new User());
        return "register.html";
    }

    @GetMapping("/profile")
	String showProfile(Model viewModel){
	    User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    User currentUser = userDao.getById(principal.getId());
	    viewModel.addAttribute("user", currentUser);
        return "discover.html"; // Changed the redirect to discover when a user signs in
//    	return "user/profile.html"; // this will lead user to their profile page after logging in
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/discover";
    }
}