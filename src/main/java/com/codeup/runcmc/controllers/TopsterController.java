package com.codeup.runcmc.controllers;

import com.codeup.runcmc.models.*;
import com.codeup.runcmc.repositories.*;

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
import java.sql.SQLOutput;
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

    private CommentRepository commentRepository;

    public TopsterController(TopsterRepository topsterRepository, RestTemplateTokenRequester restTemplateTokenRequester, AlbumRepository albumRepository, UserRepository userRepository, TopsterContentRepository topsterContentRepository, CommentRepository commentRepository) {
        this.topsterRepository = topsterRepository;
        this.restTemplateTokenRequester = restTemplateTokenRequester;
        this.albumRepository = albumRepository;
        this.userRepository=userRepository;
        this.topsterContentRepository = topsterContentRepository;
        this.commentRepository = commentRepository;
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
    public String showEditTopsterPage(Model viewModel, @PathVariable long id){

        if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User currentUser = userRepository.getById(principal.getId());
            viewModel.addAttribute("user", currentUser);
        }
        TokenResponse authToken = restTemplateTokenRequester.requestAccessToken();
        viewModel.addAttribute("authToken", authToken);
        viewModel.addAttribute("topster",topsterRepository.getById(id));
        return "user/edit-topster";
    }

    @PostMapping("/edit-topster/{id}")
    public String editTopster(@PathVariable long id,
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
                              @RequestParam(name = "spotifyID[]") String[] spotifyIDs, HttpServletRequest request)
    {
        System.out.println("We made it to the start of the editTopster Method");
        System.out.println(isPublic);
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!TopsterCreation.topsterValidator(topsterType,srcs,titles)){
            validation.rejectValue(
                    "title",
                    "blank_album_fields",
                    "Topsters must be completely filled with albums."
            );
        }
        if(principal == null || principal.getId()==0){
            System.out.println("null user");
            validation.rejectValue(
                    "title",
                    "user_session_expired",
                    "Your session has expired. Please log out and log back in to edit a topster");
        }
        if(validation.hasErrors()){
            viewModel.addAttribute("errors", validation);
            viewModel.addAttribute("topster", topster);
            return "/edit-topster/"+ topster.getId();
        }

//        Next line gets the original topster, from there we'll apply the new contents and stuff to it.
        Topster newVersionOfTopster = topsterRepository.getById(id);
        newVersionOfTopster.setTitle(topster.getTitle());
        newVersionOfTopster.setBody(topster.getBody());
        newVersionOfTopster.setPublic(isPublic.equals("public"));

        newVersionOfTopster.setTopsterContents(new ArrayList<TopsterContent>());

        List<TopsterContent> newTopsterContents = TopsterCreation.createTopsters(newVersionOfTopster, topsterType, srcs, titles, artists, releaseDates, positions, spotifyIDs, albumRepository, topsterContentRepository, validation);
        newVersionOfTopster.setTopsterContents(newTopsterContents);

        topsterRepository.save(newVersionOfTopster);
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
        System.out.println(isPublic);
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        System.out.println(principal);
//        System.out.println(principal.getId());
        if(!TopsterCreation.topsterValidator(topsterType,srcs,titles)){
            validation.rejectValue(
                    "title",
                    "blank_album_fields",
                    "Topsters must be completely filled with albums."
            );
        }
        if(principal == null || principal.getId()==0){
            System.out.println("null user");
            validation.rejectValue(
                    "title",
                    "user_session_expired",
                    "Your session has expired. Please log out and log back in to create a topster");
        }
        if(validation.hasErrors()){
            viewModel.addAttribute("errors", validation);
            viewModel.addAttribute("topster", topster);
            return "/create-topster";
        }
        User author = userRepository.getById(principal.getId());
        topster.setUser(author);
        List<TopsterContent> topsterContents = TopsterCreation.createTopsters(topster, topsterType, srcs, titles, artists, releaseDates, positions, spotifyIDs, albumRepository, topsterContentRepository, validation);

//        System.out.println(principal.getUsername());
        topster.setPublic(isPublic.equals("public"));

        topster.setTopsterContents(topsterContents);
        topsterRepository.save(topster);
        return "redirect:/profile";
    }

    @PostMapping("/topster/{id}/delete")
    public String deleteTopster(@PathVariable long id){
        Topster topsterToDelete = topsterRepository.getById(id);
        List<User> usersWhoFavoritedThisTopster = topsterToDelete.getUsersWhoFavorited();
        while(usersWhoFavoritedThisTopster.size() !=0){
            usersWhoFavoritedThisTopster.get(0).removeFromFavorites(topsterToDelete);
        }
//        for(User userWhoFavorited : usersWhoFavoritedThisTopster){
//            userWhoFavorited.removeFromFavorites(topsterToDelete);
//            userRepository.save(userWhoFavorited);
//        }
//        commentRepository.deleteAllByTopster(topsterToDelete);
        topsterRepository.save(topsterToDelete);
        topsterRepository.delete(topsterToDelete);
        return "redirect:/discover";
    }
}