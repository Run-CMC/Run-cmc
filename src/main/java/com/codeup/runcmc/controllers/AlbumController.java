package com.codeup.runcmc.controllers;

import com.codeup.runcmc.models.AlbumResponse;
import com.codeup.runcmc.services.RestTemplateAlbumRequester;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AlbumController {

    @GetMapping("/album/{id}")
    public String show(@PathVariable String id, Model viewModel) {

        AlbumResponse albumResponse = RestTemplateAlbumRequester.requestAccessAlbum("AUTH_TOKEN", id);

        viewModel.addAttribute("album", albumResponse);

        return "album.html";
    }
}
