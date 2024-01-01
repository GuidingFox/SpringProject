package com.manorama.SpringProject.payload;

public class LoginDto {
	public LoginDto(String usernameOrEmail, String password) {

		this.usernameOrEmail = usernameOrEmail;
		this.password = password;
	}

	private String usernameOrEmail;

	public String getUsernameOrEmail() {
		return usernameOrEmail;
	}

	public void setUsernameOrEmail(String usernameOrEmail) {
		this.usernameOrEmail = usernameOrEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String password;

}
