package com.manorama.SpringProject.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private final Long userId;

    public CustomAuthenticationToken(Object principal, Object credentials, Long userId) {
        super(principal, credentials);
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
