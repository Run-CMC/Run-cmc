package com.codeup.runcmc.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TopsterController {

    @Value("${spotify_client_id}")
    private String spotifyClientID;

    @Value("${spotify_client_secret}")
    private String spotifyClientSecret;

    @GetMapping("discover/topster/{id}")
    public String showIndividualTopsterPage(){
        return "topster.html";
    }

    @GetMapping("/edit-topster")
    public String showEditProfilePage(){ return "user/edit-topster";}

    @GetMapping("/create-topster")
    public String showCreateTopsterPage (){ return "create-topster";}

    @RequestMapping(path = "/keys.js", produces = "application/javascript")
    @ResponseBody
    public String apikey(){
        System.out.println(spotifyClientID +"/n" + spotifyClientSecret);
        return "const SPOTIFY_CLIENT_ID = `" + spotifyClientID + "`;\n" +
                "const SPOTIFY_CLIENT_SECRET = `" + spotifyClientSecret +"`;";
    }
}
