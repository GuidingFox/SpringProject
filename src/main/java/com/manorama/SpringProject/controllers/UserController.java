package com.manorama.SpringProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manorama.SpringProject.entities.User;
import com.manorama.SpringProject.services.UserService;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@PostMapping
	public void addUser(@RequestBody User user) {
		userService.addUser(user);
	}

	@PostMapping("/add")
	public void addManyUsers(@RequestBody List<User> users) {
		userService.addManyUsers(users);
	}

}
