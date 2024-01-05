package com.manorama.SpringProject.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.manorama.SpringProject.models.PasswordReset;
import com.manorama.SpringProject.payload.JWTAuthResponse;
import com.manorama.SpringProject.payload.LoginDto;
import com.manorama.SpringProject.payload.RegisterDto;
import com.manorama.SpringProject.services.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

//    @PostMapping(value = {"/login", "/signin"})
//    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto){
//    	JWTAuthResponse jwtAuthResponse = authService.login(loginDto);
//    	if (jwtAuthResponse.)
//        return ResponseEntity.ok(jwtAuthResponse);
//    }

	@PostMapping(value = { "/login", "/signin" })
	public ResponseEntity login(@RequestBody LoginDto loginDto) {

		JWTAuthResponse jwtAuthResponse = authService.login(loginDto);
		if (jwtAuthResponse == null) {
			return ResponseEntity.status(401).body("Invalid user!");
		}
		return ResponseEntity.ok(jwtAuthResponse);
	}

	@PostMapping(value = { "/register", "/signup" })
	public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
		String response = authService.register(registerDto);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@JsonSerialize
	@PutMapping("/reset")
	public ResponseEntity<String> resetPassword(@RequestBody PasswordReset pr) {
		String response = authService.resetPassword(pr.getPersonalNo(), pr.getNewPassword());
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
