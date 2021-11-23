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

    @RequestMapping(path = "/keys.js", produces = "application/javascript")
    @ResponseBody
    public String apikey(){
        System.out.println("Client id: "+ spotifyClientID);
        System.out.println("Client secret: "+ spotifyClientSecret);
        return "const SPOTIFY_CLIENT_ID = `" + spotifyClientID +
                "`;\nconst SPOTIFY_CLIENT_SECRET = `" + spotifyClientSecret + "`;";
    }

    @GetMapping("discover/topster/{id}")
    public String showIndividualTopsterPage(){
        return "topster.html";
    }

    @GetMapping("/edit-topster")
    public String showEditProfilePage(){ return "user/edit-topster";}

    @GetMapping("/create-topster")
    public String showCreateTopsterPage (){ return "create-topster";}
}
