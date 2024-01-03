package com.manorama.SpringProject.services.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.manorama.SpringProject.entities.Role;
import com.manorama.SpringProject.entities.User;
import com.manorama.SpringProject.models.UserReturn;
import com.manorama.SpringProject.payload.JWTAuthResponse;
import com.manorama.SpringProject.payload.LoginDto;
import com.manorama.SpringProject.payload.RegisterDto;
import com.manorama.SpringProject.repositories.RoleRepository;
import com.manorama.SpringProject.repositories.UserRepository;
import com.manorama.SpringProject.security.JwtTokenProvider;
import com.manorama.SpringProject.services.AuthService;
import com.manorama.SpringProject.services.UserService;

@Service
public class AuthServiceImpl implements AuthService {

	private AuthenticationManager authenticationManager;
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	private JwtTokenProvider jwtTokenProvider;
	private UserService userService;

	public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository,
			RoleRepository roleRepository,

			PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, UserService userService) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider = jwtTokenProvider;
		this.userService = userService;
	}

	@Override
	public JWTAuthResponse login(LoginDto loginDto) {
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
				loginDto.getUsernameOrEmail(), loginDto.getPassword());

		Optional<User> user = userRepository.findByUsername(loginDto.getUsernameOrEmail());
		if (user.isPresent()) {
			Authentication authentication = authenticationManager.authenticate(auth);
			String token = jwtTokenProvider.generateToken(authentication);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			Long userId = user.get().getId();
			return new JWTAuthResponse(token, userId);
		} else {
			return null;
		}


	}

	@Override
	public String register(RegisterDto registerDto) {

		// add check for username exists in database

		// add check for email exists in database

		User user = new User();
		user.setName(registerDto.getName());
		user.setUsername(registerDto.getUsername());
		user.setEmail(registerDto.getEmail());
		user.setPersonalNo(registerDto.getPersonalNo());
		user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByName("ROLE_USER").get();
		roles.add(userRole);
		user.setRoles(roles);

		userRepository.save(user);

		return "User registered successfully!.";
	}

	@Override
	public String resetPassword(int personalNo, String newPassword) {
		User user = userRepository.findByPersonalNo(personalNo);
		String result;
		if (user != null) {
			user.setPassword(passwordEncoder.encode(newPassword));
			userRepository.save(user);
			result = "Password has been reset!";
		} else {
			result = "No such user!";
		}
		return result;

	}
}
