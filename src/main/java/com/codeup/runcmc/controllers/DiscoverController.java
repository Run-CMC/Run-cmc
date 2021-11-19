package com.codeup.runcmc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DiscoverController {

    @GetMapping("/discover")
    public String landingPage() {
        return "/discover.html";
    }
}
