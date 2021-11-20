package com.codeup.runcmc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// why is this page not loading correctly in the browser?

@Controller
public class ProfileEditController {

    @GetMapping("/profile-edit")
    @ResponseBody // might not need this annotation
    public String profileEdit() {
        return "/profile-edit.html";
    }
}
