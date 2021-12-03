package com.codeup.runcmc.controllers;

import com.codeup.runcmc.models.Topster;
import com.codeup.runcmc.models.TopsterContent;
import com.codeup.runcmc.models.User;
import com.codeup.runcmc.repositories.AlbumRepository;
import com.codeup.runcmc.repositories.TopsterRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DiscoverController {
    private TopsterRepository topsterDao;
    private AlbumRepository albumDao;

    public DiscoverController(TopsterRepository topsterDao, AlbumRepository albumDao) {
        this.topsterDao = topsterDao;
        this.albumDao = albumDao;
    }

    @GetMapping("/discover")
    public String landingPage(Model model) {
        List<Topster> topster=topsterDao.findAll();
        model.addAttribute("topster", topster);
        return "discover.html";
    }
}
