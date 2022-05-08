package com.example.foodpanda.dto;

import com.example.foodpanda.entity.User;

public class TokenDTO {
    User user;
    String token;

    public TokenDTO(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
