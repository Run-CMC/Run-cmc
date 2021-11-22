package com.codeup.runcmc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TopsterController {

    @GetMapping("discover/topster/{id}")
    public String showIndividualTopsterPage(){
        return "topster.html";
    }
}
