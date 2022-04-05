package com.example.foodpanda.controller;

import com.example.foodpanda.dto.LoginDTO;
import com.example.foodpanda.entity.User;
import com.example.foodpanda.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Controller
@CrossOrigin
public class LoginController {
    private static User currentUser;

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        LoginController.currentUser = currentUser;
    }

    @PostMapping(value = "/loginuser", consumes = "application/json")
    public ResponseEntity<User> login(@RequestBody LoginDTO loginDTO){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("parola"));
        User user = userService.findByUsername(loginDTO.getUsername());

        if (user != null && encoder.matches(loginDTO.getPassword(), user.getPassword())) {
            setCurrentUser(user);
            System.out.println("Login success!");
            HttpHeaders headers = new HttpHeaders();
            headers.add("Responded","LoginController");
            return  ResponseEntity.accepted().headers(headers).body(user);
        }
        //Fail to login
        System.out.println("Login failed!");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded","LoginController");
        return  ResponseEntity.badRequest().headers(headers).body(null);
    }


}
