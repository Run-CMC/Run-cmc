package com.codeup.runcmc.controllers;

import com.codeup.runcmc.services.RestTemplateTokenRequester;
import com.codeup.runcmc.services.TokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    public String showCreateTopsterPage (Model viewModel){
        TokenResponse authToken = RestTemplateTokenRequester.requestAccessToken();
        viewModel.addAttribute("authToken", authToken);
        return "create-topster";
    }

    @GetMapping("dragdropdemo")
    public String showDragAndDropDemoPage(){return "drag-n-drop-practice.html";}

    @RequestMapping(path = "/keys.js", produces = "application/javascript")
    @ResponseBody
    public String apikey(){
        System.out.println(spotifyClientID +"/n" + spotifyClientSecret);
        return "const SPOTIFY_CLIENT_ID = `" + spotifyClientID + "`;\n" +
                "const SPOTIFY_CLIENT_SECRET = `" + spotifyClientSecret +"`;";
    }
}
