package com.lap.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.lap.crud.entity.User;
import com.lap.crud.repository.UserRepository;

@Controller
public class RegistrationController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
    private PasswordEncoder passwordEncoder;


	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "registration-form";
	}

	@PostMapping("/register")
	public String registerUser(@ModelAttribute("user") User user, BindingResult bindingResult) {

		if (userRepository.findByUsername(user.getUsername()) != null) {
			bindingResult.rejectValue("username", "error.user", "Username is already taken.");
			return "registration-form";
		}

		if (userRepository.findByEmail(user.getEmail()) != null) {
			bindingResult.rejectValue("email", "error.user", "Email is already taken.");
			return "registration-form";
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return "redirect:/login?success";
	}

}
