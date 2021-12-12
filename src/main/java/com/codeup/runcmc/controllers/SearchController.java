package com.codeup.runcmc.controllers;

import com.codeup.runcmc.models.Topster;
import com.codeup.runcmc.repositories.AlbumRepository;
import com.codeup.runcmc.repositories.TopsterRepository;
import com.codeup.runcmc.repositories.UserRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

   private TopsterRepository topsterDao;

    public SearchController(TopsterRepository topsterDao) {
        this.topsterDao = topsterDao;
    }

    @GetMapping("/search")
    public String showSearchPage(@RequestParam(name = "search-bar", required = false) String searchterms, Model model){
        List<Topster> resultSet = topsterDao.findAllBySearchTerm(searchterms);
        model.addAttribute("searchedTopsters", resultSet);
//        for(Topster topster : resultSet){
//            System.out.println(topster.getTitle());
//        }
        return "search";
    }

}
