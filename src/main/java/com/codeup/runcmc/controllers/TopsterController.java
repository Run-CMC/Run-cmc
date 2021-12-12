package com.codeup.runcmc.controllers;

import com.codeup.runcmc.models.Album;
import com.codeup.runcmc.models.Topster;
import com.codeup.runcmc.models.TopsterContent;
import com.codeup.runcmc.models.User;
import com.codeup.runcmc.repositories.AlbumRepository;
import com.codeup.runcmc.repositories.TopsterContentRepository;
import com.codeup.runcmc.repositories.TopsterRepository;

import com.codeup.runcmc.repositories.UserRepository;
import com.codeup.runcmc.services.RestTemplateTokenRequester;
import com.codeup.runcmc.services.TokenResponse;
import com.codeup.runcmc.services.TopsterCreation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TopsterController {

//    @Value("${spotify_client_id}")
//    private String spotifyClientID;
//
//    @Value("${spotify_client_secret}")
//    private String spotifyClientSecret;

    private RestTemplateTokenRequester restTemplateTokenRequester;

    private TopsterRepository topsterRepository;

    private AlbumRepository albumRepository;

    private UserRepository userRepository;

    private TopsterContentRepository topsterContentRepository;

    public TopsterController(TopsterRepository topsterRepository, RestTemplateTokenRequester restTemplateTokenRequester, AlbumRepository albumRepository, UserRepository userRepository, TopsterContentRepository topsterContentRepository) {
        this.topsterRepository = topsterRepository;
        this.restTemplateTokenRequester = restTemplateTokenRequester;
        this.albumRepository = albumRepository;
        this.userRepository=userRepository;
        this.topsterContentRepository = topsterContentRepository;
    }

    @GetMapping("discover/topster/{id}")
    public String showIndividualTopsterPage(Model model, @PathVariable long id) {

        if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User currentUser = userRepository.getById(principal.getId());
            model.addAttribute("user", currentUser);
        }
        model.addAttribute("topster",topsterRepository.getById(id));
        return "topster";

    }

    @GetMapping("/edit-topster/{id}")
    public String showEditTopsterPage(Model model, @PathVariable long id){
        model.addAttribute("topster",topsterRepository.getById(id));
        return "user/edit-topster";
    }

    @PostMapping("/edit-topster/{id}")
    public String editTopster(@ModelAttribute Topster topster) {
        Topster editTopsterInfo = topsterRepository.getById(topster.getId());
        editTopsterInfo.setTitle(topster.getTitle());
        editTopsterInfo.setBody(topster.getBody());
        topsterRepository.save(editTopsterInfo);
        return "redirect:/profile";
    }

    @GetMapping("/create-topster")
    public String showCreateTopsterPage (Model viewModel){
        TokenResponse authToken = restTemplateTokenRequester.requestAccessToken();
        viewModel.addAttribute("authToken", authToken);
        viewModel.addAttribute("topster", new Topster());
        return "create-topster";
    }

    @PostMapping("/create-topster")
    public String createTopster(
            Model viewModel,
            @ModelAttribute @Valid Topster topster,
            Errors validation,
            @RequestParam(name= "isPublic", required = false, defaultValue = "") String isPublic,
            @RequestParam (name = "topster-type") String topsterType,
            @RequestParam(name = "src[]") String[] srcs,
            @RequestParam(name = "title[]") String[] titles,
            @RequestParam(name = "artist[]") String[] artists,
            @RequestParam(name = "releaseDate[]") String[] releaseDates,
            @RequestParam(name = "position[]") int[] positions,
            @RequestParam(name = "spotifyID[]") String[] spotifyIDs, HttpServletRequest request) throws MessagingException {
        System.out.println(topster.isPublic());
        if(!TopsterCreation.topsterValidator(topsterType,srcs,titles)){
            validation.rejectValue(
                    "title",
                    "blank_album_fields",
                    "Topsters must be completely filled with albums."
            );
        }
        if(validation.hasErrors()){
            viewModel.addAttribute("errors", validation);
            viewModel.addAttribute("topster", topster);
            return "/create-topster";
        }
        List<TopsterContent> topsterContents = TopsterCreation.createTopsters(topster, topsterType, srcs, titles, artists, releaseDates, positions, spotifyIDs, albumRepository, topsterContentRepository, validation);
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User author = userRepository.getById(principal.getId());
        System.out.println(principal.getUsername());
        topster.setPublic(isPublic.equals("public"));
        topster.setUser(author);
        topster.setTopsterContents(topsterContents);
        topsterRepository.save(topster);
        return "redirect:/profile";
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