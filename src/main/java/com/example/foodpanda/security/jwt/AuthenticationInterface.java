package com.example.foodpanda.security.jwt;

import com.example.foodpanda.entity.User;

public interface AuthenticationInterface {
    String login(String username, String password);
    User findByToken(String token);
    void logout(User user);
}
