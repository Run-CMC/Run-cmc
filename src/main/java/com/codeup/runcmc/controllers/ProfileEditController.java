package com.codeup.runcmc.controllers;

import com.codeup.runcmc.models.User;
import com.codeup.runcmc.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// why is this page not loading correctly in the browser?

@Controller
public class ProfileEditController {

    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;

    public ProfileEditController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/profile-edit/{id}")
    public String profileEdit(Model viewModel, @PathVariable long id) {
        viewModel.addAttribute("user", userDao.getById(id));
        return "user/profile-edit.html";
    }

    @PostMapping("/profile-edit/{id}")
    public String updateProfile(@ModelAttribute User user) {
        User editedUser = userDao.getById(user.getId());
        editedUser.setUsername(user.getUsername());
        editedUser.setEmail(user.getEmail());
        editedUser.setBio(user.getBio());
        userDao.save(editedUser);
        return "redirect:/profile";
    }
}