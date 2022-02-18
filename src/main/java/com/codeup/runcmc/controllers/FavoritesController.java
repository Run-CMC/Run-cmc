package com.codeup.runcmc.controllers;

import com.codeup.runcmc.models.Topster;
import com.codeup.runcmc.models.User;
import com.codeup.runcmc.repositories.TopsterRepository;
import com.codeup.runcmc.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class FavoritesController {
	private TopsterRepository topsterRepository;
	private UserRepository userRepository;

	public FavoritesController(TopsterRepository topsterRepository, UserRepository userRepository) {
		this.topsterRepository = topsterRepository;
		this.userRepository = userRepository;
	}

	@PostMapping("/favorites/topster/{tid}")
	public void favoriteButton(@PathVariable long tid){
		System.out.println("Post method is firing");
		if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser") && topsterRepository.existsById(tid)) {
			User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			User userThatFavoritedOrUnfavorited = userRepository.getById(principal.getId());
//			System.out.println("User that favorited: "+userThatFavorited.getId() + " id of topster to favorite "+ tid);
			Topster topsterSelectionToFavoriteOrUnfavorite = topsterRepository.getById(tid);
			if(userThatFavoritedOrUnfavorited.getFavoritedTopsters() == null){
				userThatFavoritedOrUnfavorited.setFavoritedTopsters(new ArrayList<Topster>());
			}

			if (!userThatFavoritedOrUnfavorited.getFavoritedTopsters().contains(topsterSelectionToFavoriteOrUnfavorite)){
				userThatFavoritedOrUnfavorited.addToFavorites(topsterSelectionToFavoriteOrUnfavorite);
				userRepository.save(userThatFavoritedOrUnfavorited);
			} else{
				userThatFavoritedOrUnfavorited.removeFromFavorites(topsterSelectionToFavoriteOrUnfavorite);
				userRepository.save(userThatFavoritedOrUnfavorited);
			}
		}
//		return "redirect:/discover/topster/" + tid;
	}

//	@PostMapping("/favorites/user/{uid}/topster/{tid}")
//	public String favoriteATopster(@PathVariable long uid, @PathVariable long tid){
//		User userThatFavorited = userRepository.getById(uid);
//		Topster TopsterToAddToFavorites = topsterRepository.getById(tid);
//		userThatFavorited.addToFavorites(TopsterToAddToFavorites);
//		userRepository.save(userThatFavorited);
//		return "redirect:/discover/topster/" + tid;
//	}

	@PostMapping("/favorites/user/{uid}/topster/{tid}/remove")
	public String removeAFavoriteTopster(@PathVariable long uid, @PathVariable long tid){
		User userThatFavorited = userRepository.getById(uid);
		Topster TopsterToAddToFavorites = topsterRepository.getById(tid);
		userThatFavorited.removeFromFavorites(TopsterToAddToFavorites);
		userRepository.save(userThatFavorited);
		return "redirect:/discover/topster/" + tid;
	}
}
