package com.example.expensetracker.security.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Token {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private Long expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
