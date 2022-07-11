package com.example.deploy.models;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AuthenticationResponse {
    private String accessToken;
    private int expired;
    private String username;
    private Collection<? extends GrantedAuthority> authorities;

    public AuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public AuthenticationResponse(String accessToken, String username, Collection<? extends GrantedAuthority> authorities) {
        this.accessToken = accessToken;
        this.username = username;
        this.authorities = authorities;
    }

    public AuthenticationResponse(String accessToken, int expired, String username, Collection<? extends GrantedAuthority> authorities) {
        this.accessToken = accessToken;
        this.expired = expired;
        this.username = username;
        this.authorities = authorities;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpired() {
        return expired;
    }

    public void setExpired(int expired) {
        this.expired = expired;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
