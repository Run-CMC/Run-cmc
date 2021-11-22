package com.codeup.runcmc.controllers;

import com.codeup.runcmc.services.RestTemplateTokenRequester;
import com.codeup.runcmc.services.TokenResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SpotifyTestController {

    @GetMapping("/spotify")
    @ResponseBody
    public TokenResponse requestToken() {
        return RestTemplateTokenRequester.requestAccessToken();

    }
}