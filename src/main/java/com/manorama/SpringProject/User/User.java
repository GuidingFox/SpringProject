package com.manorama.SpringProject.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int personalNo;
    private String name;
    private String username;
    private String password;
    public User(int personalNo, String name, String username, String password) {
        this.personalNo = personalNo;
        this.name = name;
        this.username = username;
        this.password = password;
    }
    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }


    public User() {
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
