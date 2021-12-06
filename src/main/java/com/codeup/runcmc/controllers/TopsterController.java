package com.codeup.runcmc.controllers;

import com.codeup.runcmc.models.Album;
import com.codeup.runcmc.models.Topster;
import com.codeup.runcmc.models.TopsterContent;
import com.codeup.runcmc.repositories.AlbumRepository;
import com.codeup.runcmc.repositories.TopsterRepository;

import com.codeup.runcmc.services.RestTemplateTokenRequester;
import com.codeup.runcmc.services.TokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
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

    public TopsterController(TopsterRepository topsterRepository, RestTemplateTokenRequester restTemplateTokenRequester, AlbumRepository albumRepository) {
        this.topsterRepository = topsterRepository;
        this.restTemplateTokenRequester = restTemplateTokenRequester;
        this.albumRepository = albumRepository;
    }

    @GetMapping("discover/topster/{id}")
    public String showIndividualTopsterPage(Model model, @PathVariable long id) {
        model.addAttribute("topster",topsterRepository.getById(id));
        return "topster.html";
    }

    @GetMapping("/edit-topster/{id}")
    public String showEditProfilePage(Model model, @PathVariable long id){
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
            @ModelAttribute Topster topster,
            @RequestParam (name = "topster-type") String topsterType,
            @RequestParam(name = "src[]") String[] srcs,
            @RequestParam(name = "title[]") String[] titles,
            @RequestParam(name = "artist[]") String[] artists,
            @RequestParam(name = "releaseDate[]") String[] releaseDates,
            @RequestParam(name = "position[]") int[] positions,
            @RequestParam(name = "spotifyID[]") String[] spotifyIDs, HttpServletRequest request) throws MessagingException {
        System.out.println(topsterType);
        List<TopsterContent> topsterContents = new ArrayList<TopsterContent>();
        if(topsterType.equals("3x3")){
            for(int i = 0; i > 9; i++){
                Album album;
                if(albumRepository.findBySpotifyAlbumID(spotifyIDs[i]) == null){
                    album = new Album();
                    album.setSpotifyAlbumArt(srcs[i]);
                    album.setReleaseDate(Date.valueOf(releaseDates[i]));
                    album.setSpotifyAlbumName(titles[i]);
                    album.setSpotifyArtist(artists[i]);
                    album.setSpotifyAlbumID(spotifyIDs[i]);
                } else {
                    album = albumRepository.findBySpotifyAlbumID(spotifyIDs[i]);
                }

                TopsterContent topsterContent = new TopsterContent();
                topsterContent.setTopster(topster);
                topsterContent.setPosition(positions[i]);
                topsterContent.setAlbum(album);
                List<TopsterContent> copyOfTopsterContents = album.getTopsterContents();
                copyOfTopsterContents.add(topsterContent);
                album.setTopsterContents(copyOfTopsterContents);

                topsterContents.add(topsterContent);
            }
        } else if(topsterType.equals("4x4")){

        }else if(topsterType.equals("5x5")){

        } else{
            System.out.println("Uhhhh.... this shouldn't be happening");
        }


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