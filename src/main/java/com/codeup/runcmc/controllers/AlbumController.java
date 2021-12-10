package com.codeup.runcmc.controllers;

import com.codeup.runcmc.models.Album;
import com.codeup.runcmc.models.AlbumResponse;
import com.codeup.runcmc.models.Topster;
import com.codeup.runcmc.repositories.AlbumRepository;
import com.codeup.runcmc.repositories.TopsterRepository;
import com.codeup.runcmc.services.RestTemplateAlbumRequester;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class AlbumController {

    private AlbumRepository albumDAO;
    private TopsterRepository topsterDAO;

    public AlbumController(AlbumRepository albumDAO, TopsterRepository topsterDAO) {
        this.albumDAO = albumDAO;
        this.topsterDAO = topsterDAO;
    }

    @GetMapping("/album/{id}")
    public String show(@PathVariable long id, Model viewModel) {

        List<Topster> topsters = topsterDAO.findAllByAlbumID(id);

        viewModel.addAttribute("topsters", topsters);

        viewModel.addAttribute("album", albumDAO.getById(id));

        return "album.html";
    }

}
