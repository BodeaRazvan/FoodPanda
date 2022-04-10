package com.example.foodpanda.controller;

import com.example.foodpanda.entity.Food;
import com.example.foodpanda.entity.Order;
import com.example.foodpanda.entity.Restaurant;
import com.example.foodpanda.entity.User;
import com.example.foodpanda.service.OrderService;
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
    private final OrderService orderService;

    @Autowired
    public UserController(UserService userService, RestaurantService restaurantService, OrderService orderService) {
        this.userService = userService;
        this.restaurantService = restaurantService;
        this.orderService = orderService;
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

    @PostMapping("/placeOrder")
    public ResponseEntity<List<Integer>> placeOrder(@RequestBody List<Integer> orderedFoodsId){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded","LoginController");
        orderService.createOrderFromFoodId(orderedFoodsId,LoginController.getCurrentUser());
        return ResponseEntity.accepted().headers(headers).body(orderedFoodsId);
    }

    @GetMapping("/getUserOrders")
    public ResponseEntity<List<Order>> getOrders(){
        List<Order> orders = LoginController.getCurrentUser().getOrder();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/getOrderFoods/{id}")
    public ResponseEntity<List<Food>> getOrderFoods(@PathVariable int id){
        System.out.println("Fetching ordered foods");
        System.out.println(" ");
        List<Food> foods = orderService.findById(id).getFood();
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

}
