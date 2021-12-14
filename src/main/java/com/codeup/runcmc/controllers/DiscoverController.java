package com.codeup.runcmc.controllers;

import com.codeup.runcmc.models.Topster;
import com.codeup.runcmc.models.TopsterContent;
import com.codeup.runcmc.models.User;
import com.codeup.runcmc.repositories.AlbumRepository;
import com.codeup.runcmc.repositories.TopsterRepository;
import com.codeup.runcmc.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DiscoverController {
    private TopsterRepository topsterDao;
    private AlbumRepository albumDao;
    private UserRepository userRepository;


    public DiscoverController(TopsterRepository topsterDao, AlbumRepository albumDao, UserRepository userDao) {
        this.topsterDao = topsterDao;
        this.albumDao = albumDao;
        this.userRepository = userDao;
    }

    @GetMapping("/discover")
    public String landingPage(Model model) {
        List<Topster> topster = topsterDao.findAll();
        model.addAttribute("topster", topster);

        if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User currentUser = userRepository.getById(principal.getId());
            model.addAttribute("user", currentUser);
        }


        return "discover.html";
    }
}
