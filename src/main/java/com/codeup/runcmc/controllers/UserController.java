package com.codeup.runcmc.controllers;

import com.codeup.runcmc.models.Album;
import com.codeup.runcmc.models.Topster;
import com.codeup.runcmc.models.TopsterContent;
import com.codeup.runcmc.models.User;
import com.codeup.runcmc.repositories.AlbumRepository;
import com.codeup.runcmc.repositories.TopsterContentRepository;
import com.codeup.runcmc.repositories.TopsterRepository;
import com.codeup.runcmc.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

	private UserRepository userDao;
	private PasswordEncoder passwordEncoder;
	private TopsterRepository topsterDao;
	private TopsterContentRepository topsterContentRepository;
	private AlbumRepository albumRepository;

	public UserController(UserRepository userDao, PasswordEncoder passwordEncoder, TopsterRepository topsterDao, TopsterContentRepository topsterContentRepository, AlbumRepository albumRepository) {
		this.userDao = userDao;
		this.passwordEncoder = passwordEncoder;
		this.topsterDao = topsterDao;
		this.albumRepository = albumRepository;
		this.topsterContentRepository = topsterContentRepository;
	}

	@GetMapping("/register")
	String showRegisterPage(Model model){
		model.addAttribute("user", new User());
		return "register.html";
	}

	@GetMapping("/profile")
	String showProfile(Model viewModel){
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
			return "redirect:/";
		} else {
			User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			User currentUser = userDao.getById(principal.getId());
			viewModel.addAttribute("user", currentUser);
			viewModel.addAttribute("currentUser", currentUser);
			return "user/profile.html";
		}
	}

	@GetMapping("/profile/{id}")
	String showProfileOfOtherUser(@PathVariable long id, Model viewModel){
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
			System.out.println("redirecting");
			return "redirect:/";
		} else {
			System.out.println(id);
			User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			User currentUser = userDao.getById(principal.getId());
			User userWhoseProfileWeAreGetting = userDao.getById(id);
			viewModel.addAttribute("user", userWhoseProfileWeAreGetting);
			viewModel.addAttribute("currentUser", currentUser);
			return "user/profile.html";
		}
	}

	@PostMapping("/register")
	public String saveUser(@ModelAttribute @Valid User user, Errors validation, Model model){
		if (user.getPassword().isBlank()) {
			validation.rejectValue(
					"password",
					"user.password",
					"Password is blank."
			);
		}
		if (validation.hasErrors()) {
			model.addAttribute("errors", validation);
			model.addAttribute("user", user);
			return "/register";
		}
		String hash = passwordEncoder.encode(user.getPassword());
		user.setPassword(hash);
		userDao.save(user);
		return "redirect:/";
	}
}