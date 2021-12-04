package com.codeup.runcmc.controllers;

import com.codeup.runcmc.models.Album;
import com.codeup.runcmc.models.AlbumResponse;
import com.codeup.runcmc.repositories.AlbumRepository;
import com.codeup.runcmc.services.RestTemplateAlbumRequester;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class AlbumController {

    private AlbumRepository albumDAO;

    public AlbumController(AlbumRepository albumDAO) {
        this.albumDAO = albumDAO;
    }

    @GetMapping("/album/{id}")
    public String show(@PathVariable long id, Model viewModel) {

        viewModel.addAttribute("album", albumDAO.getById(id));

        return "album.html";
    }

}
