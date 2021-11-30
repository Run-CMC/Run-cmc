package com.codeup.runcmc.controllers;

import com.codeup.runcmc.models.User;
import com.codeup.runcmc.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// why is this page not loading correctly in the browser?

@Controller
public class ProfileEditController {

    private UserRepository userDao;

    public ProfileEditController(UserRepository userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/profile-edit/{id}")
    public String profileEdit(Model viewModel, @PathVariable long id) {
        viewModel.addAttribute("user", userDao.getById(id));
        return "user/profile-edit.html";
    }

    @PostMapping("/profile-edit/{id}")
    public String updateProfile(@ModelAttribute User user) {
        User editedProfile = userDao.getById(user.getId());
        editedProfile.setUsername(user.getUsername());
        editedProfile.setPassword(user.getPassword());
        editedProfile.setEmail(user.getEmail());
        editedProfile.setBio(user.getBio());
        System.out.println(user.getId());
        System.out.println(user.getUsername());
        userDao.save(editedProfile);
        return "redirect:profile";
    }
}