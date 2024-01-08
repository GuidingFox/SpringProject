package com.manorama.SpringProject.payload;

import java.util.Set;

import com.manorama.SpringProject.entities.Role;

public class JWTAuthResponse {
	private String accessToken;
	private String tokenType = "Bearer";
	private Long userId;
	private Set<Role> roles;

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public JWTAuthResponse(String accessToken, Long userId, Set<Role> roles) {
		this.accessToken = accessToken;
		this.tokenType = "Bearer";
		this.userId = userId;
		this.roles = roles;
	}

}
