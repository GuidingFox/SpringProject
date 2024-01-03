package com.manorama.SpringProject.services;

import com.manorama.SpringProject.payload.LoginDto;
import com.manorama.SpringProject.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);

	String resetPassword(int personalNo,String newPassword);
}
