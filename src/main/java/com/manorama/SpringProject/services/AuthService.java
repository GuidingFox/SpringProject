package com.manorama.SpringProject.services;

import com.manorama.SpringProject.models.UserReturn;
import com.manorama.SpringProject.payload.JWTAuthResponse;
import com.manorama.SpringProject.payload.LoginDto;
import com.manorama.SpringProject.payload.RegisterDto;

public interface AuthService {
    JWTAuthResponse login(LoginDto loginDto);

    String register(RegisterDto registerDto);

	String resetPassword(int personalNo,String newPassword);
}
