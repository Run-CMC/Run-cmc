package com.codeup.runcmc.controllers;

import com.codeup.runcmc.models.User;
import com.codeup.runcmc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

// why is this page not loading correctly in the browser?

@Controller
public class ProfileEditController {

    private UserRepository userDao;

    public ProfileEditController(UserRepository userDao) {
        this.userDao = userDao;
    }
    @Value("${filestack.api.key}")
    private String filestackApiKey;

    @RequestMapping(path = "/keys.js", produces = "application/javascript")
    @ResponseBody
    public String apikey(){return "const filestack_api_key = `" + filestackApiKey +"`";
    }
    @GetMapping("/profile-edit/{id}")
    public String profileEdit(Model viewModel, @PathVariable long id) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(id != principal.getId()){
            return "redirect:/profile";
        }
        viewModel.addAttribute("user", userDao.getById(id));
        return "user/profile-edit.html";
    }

    @PostMapping("/profile-edit/{id}")
    public String updateProfile(@ModelAttribute @Valid User user, Errors validation, Model model) {
	    if (validation.hasErrors()) {
		    model.addAttribute("errors", validation);
		    model.addAttribute("user", user);
		    return "user/profile-edit";
	    } else {
		    User editedUser = userDao.getById(user.getId());
		    editedUser.setUsername(user.getUsername());
		    editedUser.setEmail(user.getEmail());
		    editedUser.setBio(user.getBio());
		    editedUser.setProfilePhotoURL(user.getProfilePhotoURL());
		    userDao.save(editedUser);
		    return "redirect:/profile";
	    }
    }
}