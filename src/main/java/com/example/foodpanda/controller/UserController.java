package com.example.foodpanda.controller;

import com.example.foodpanda.entity.Restaurant;
import com.example.foodpanda.entity.User;
import com.example.foodpanda.service.RestaurantService;
import com.example.foodpanda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {
    private final UserService userService;
    private final RestaurantService restaurantService;

    @Autowired
    public UserController(UserService userService, RestaurantService restaurantService) {
        this.userService = userService;
        this.restaurantService = restaurantService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.findAll();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded","UserController");
        return  ResponseEntity.accepted().headers(headers).body(users);
    }

    @GetMapping("/restaurants")
    public ResponseEntity<List<Restaurant>> getAllRestaurants(){
        List<Restaurant> restaurants = restaurantService.findAll();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded","UserController");
        return  ResponseEntity.accepted().headers(headers).body(restaurants);
    }

}
