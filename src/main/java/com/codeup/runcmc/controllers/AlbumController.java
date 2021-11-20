package com.codeup.runcmc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AlbumController {

    @GetMapping("/album")
    @ResponseBody
    public String album() {
        return "/album.html";
    }
}
