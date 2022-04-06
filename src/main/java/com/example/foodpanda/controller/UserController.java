package com.example.foodpanda.controller;

import com.example.foodpanda.entity.Food;
import com.example.foodpanda.entity.Restaurant;
import com.example.foodpanda.entity.User;
import com.example.foodpanda.service.RestaurantService;
import com.example.foodpanda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getRestaurants")
    public ResponseEntity<List<Restaurant>> getAllRestaurants(){
        List<Restaurant> restaurants = restaurantService.findAll();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/getRestaurantMenu/{id}")
    public ResponseEntity<List<Food>> getRestaurantMenu(@PathVariable int id){
        System.out.println(id);
        Restaurant restaurant = restaurantService.findById(id);
        List<Food> foods = restaurant.getFoods();
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

}
