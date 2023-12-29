package com.manorama.SpringProject.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private int personalNo;
	private String name;
	private String username;
	private String password;
	private String userType;

//	public User(int personalNo, String name, String username, String password, String userType) {
//		this.personalNo = personalNo;
//		this.name = name;
//		this.username = username;
//		this.password = password;
//		this.userType = userType;
//	}
//
//	public User(String name, String username, String password, String userType) {
//		this.name = name;
//		this.username = username;
//		this.password = password;
//		this.userType = userType;
//	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPersonalNo() {
		return personalNo;
	}

	public void setPersonalNo(int personalNo) {
		this.personalNo = personalNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
