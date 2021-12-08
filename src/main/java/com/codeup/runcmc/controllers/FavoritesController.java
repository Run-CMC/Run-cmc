package com.codeup.runcmc.controllers;

import com.codeup.runcmc.models.Topster;
import com.codeup.runcmc.models.User;
import com.codeup.runcmc.repositories.TopsterRepository;
import com.codeup.runcmc.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FavoritesController {
	private TopsterRepository topsterRepository;
	private UserRepository userRepository;

	public FavoritesController(TopsterRepository topsterRepository, UserRepository userRepository) {
		this.topsterRepository = topsterRepository;
		this.userRepository = userRepository;
	}

	@PostMapping("/favorites/user/{uid}/topster/{tid}")
	public String favoriteATopster(@PathVariable long uid, @PathVariable long tid){
		User userThatFavorited = userRepository.getById(uid);
		Topster TopsterToAddToFavorites = topsterRepository.getById(tid);
		userThatFavorited.addToFavorites(TopsterToAddToFavorites);
		userRepository.save(userThatFavorited);
		return "redirect:/discover/topster/" + tid;
	}

	@PostMapping("/favorites/user/{uid}/topster/{tid}/remove")
	public String removeAFavoriteTopster(@PathVariable long uid, @PathVariable long tid){
		User userThatFavorited = userRepository.getById(uid);
		Topster TopsterToAddToFavorites = topsterRepository.getById(tid);
		userThatFavorited.removeFromFavorites(TopsterToAddToFavorites);
		userRepository.save(userThatFavorited);
		return "redirect:/discover/topster/" + tid;
	}
}
