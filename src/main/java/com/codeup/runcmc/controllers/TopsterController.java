package com.codeup.runcmc.controllers;

import com.codeup.runcmc.models.Topster;
import com.codeup.runcmc.repositories.TopsterRepository;
import com.codeup.runcmc.services.RestTemplateTokenRequester;
import com.codeup.runcmc.services.TokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TopsterController {

//    @Value("${spotify_client_id}")
//    private String spotifyClientID;
//
//    @Value("${spotify_client_secret}")
//    private String spotifyClientSecret;

    @GetMapping("discover/topster/{id}")
    public String showIndividualTopsterPage(){
        return "topster.html";
    }

    private RestTemplateTokenRequester restTemplateTokenRequester;

    private TopsterRepository topsterRepository;

    public TopsterController(TopsterRepository topsterRepository, RestTemplateTokenRequester restTemplateTokenRequester) {
        this.topsterRepository = topsterRepository;
        this.restTemplateTokenRequester = restTemplateTokenRequester;
    }

    @GetMapping("/edit-topster/{id}")
    public String showEditProfilePage(Model model, @PathVariable long id){
        model.addAttribute("topster",topsterRepository.getById(id));
        return "user/edit-topster";
    }

    @PostMapping("/edit-topster/{id}")
    public String editTopster(@ModelAttribute Topster topster) {
        topster = topsterRepository.getById(topster.getId());
        topster.setTitle(topster.getTitle());
        topster.setBody(topster.getBody());
        topsterRepository.save(topster);
        return "user/edit-topster/{id}";
    }

    @GetMapping("/create-topster")
    public String showCreateTopsterPage (Model viewModel){
        TokenResponse authToken = restTemplateTokenRequester.requestAccessToken();
        viewModel.addAttribute("authToken", authToken);
        return "create-topster";
    }

    @GetMapping("dragdropdemo")
    public String showDragAndDropDemoPage(){return "drag-n-drop-practice.html";}

//    @RequestMapping(path = "/keys.js", produces = "application/javascript")
//    @ResponseBody
//    public String apikey(){
//        System.out.println(spotifyClientID +"/n" + spotifyClientSecret);
//        return "const SPOTIFY_CLIENT_ID = `" + spotifyClientID + "`;\n" +
//                "const SPOTIFY_CLIENT_SECRET = `" + spotifyClientSecret +"`;";
//    }
}
