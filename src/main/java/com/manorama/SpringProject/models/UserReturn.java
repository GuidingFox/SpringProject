package com.manorama.SpringProject.models;

public class UserReturn {
	private long id;
	private String token;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserReturn(long id, String token) {
		this.id = id;
		this.token = token;
	}

}
