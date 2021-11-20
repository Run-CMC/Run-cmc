package com.codeup.runcmc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// why is this page not loading correctly in the browser?

@Controller
public class ProfileEditController {

    @GetMapping("/profile-edit")
    public String profileEdit() {
        return "/user/profile-edit.html";
    }
}