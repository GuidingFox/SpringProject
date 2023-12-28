package com.manorama.SpringProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manorama.SpringProject.entities.User;
import com.manorama.SpringProject.repositories.OrderRepository;
import com.manorama.SpringProject.repositories.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final OrderRepository orderRepository;

	@Autowired
	public UserService(UserRepository userRepository, OrderRepository orderRepository) {
		this.userRepository = userRepository;
		this.orderRepository = orderRepository;
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public void addUser(User user) {
		userRepository.save(user);
	}

	public void addManyUsers(List<User> users) {
		userRepository.saveAll(users);
	}
}
