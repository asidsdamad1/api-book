package com.example.deploy.models;

public class AuthenticationResponse {
    private String accessToken;
    private int expired;

    public AuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public AuthenticationResponse(String accessToken, int expired) {
        this.accessToken = accessToken;
        this.expired = expired;
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
}
