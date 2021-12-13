package com.codeup.runcmc.controllers;

import com.codeup.runcmc.models.Comment;
import com.codeup.runcmc.models.User;
import com.codeup.runcmc.repositories.CommentRepository;
import com.codeup.runcmc.repositories.TopsterRepository;
import com.codeup.runcmc.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class CommentController {

    private CommentRepository commentsDao;
    private TopsterRepository topsterDao;
    private UserRepository usersDao;

    public CommentController(CommentRepository commentsDao, TopsterRepository topsterDao, UserRepository usersDao) {
        this.commentsDao = commentsDao;
        this.topsterDao = topsterDao;
        this.usersDao = usersDao;
    }

    @PostMapping("discover/topster/{id}")
    public String addComment(@PathVariable long id, @RequestParam(name = "body") String body){
        Comment comment = new Comment();
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User author = usersDao.getById(principal.getId());
        comment.setUser(author);
        comment.setBody(body);
        comment.setTopster(topsterDao.getById(id));
        commentsDao.save(comment);
        return "redirect:/discover/topster/" + id;
    }

    @PostMapping("/discover/topster/{id}/delete")
    public String deleteComment(@PathVariable long id, @ModelAttribute Comment comment) {
        commentsDao.deleteById(comment.getId());
        return "redirect:/discover/topster/" + id;
    }
}