package com.example.foodpanda.controller;

import com.example.foodpanda.dto.OrderDTO;
import com.example.foodpanda.entity.Food;
import com.example.foodpanda.entity.Order;
import com.example.foodpanda.entity.Restaurant;
import com.example.foodpanda.entity.User;
import com.example.foodpanda.logger.MyLogger;
import com.example.foodpanda.mail.MailHandler;
import com.example.foodpanda.service.OrderService;
import com.example.foodpanda.service.RestaurantService;
import com.example.foodpanda.service.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {
    private static final Logger LOGGER = MyLogger.getInstance();
    private final UserService userService;
    private final RestaurantService restaurantService;
    private final OrderService orderService;

    @Autowired
    public UserController(UserService userService, RestaurantService restaurantService, OrderService orderService, MailHandler mailHandler) {
        this.userService = userService;
        this.restaurantService = restaurantService;
        this.orderService = orderService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        LOGGER.info("Request for getting all users");
        List<User> users = userService.findAll();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded","UserController");
        return  ResponseEntity.accepted().headers(headers).body(users);
    }

    @GetMapping("/getRestaurants")
    public ResponseEntity<List<Restaurant>> getAllRestaurants(){
        LOGGER.info("Request for getting all restaurants");
        List<Restaurant> restaurants = restaurantService.findAll();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/getRestaurantMenu/{id}")
    public ResponseEntity<List<Food>> getRestaurantMenu(@PathVariable int id){
        LOGGER.info("Request for getting menu of restaurant with id: " + id);
        System.out.println(id);
        Restaurant restaurant = restaurantService.findById(id);
        List<Food> foods = restaurant.getFoods();
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<OrderDTO> placeOrder(@RequestBody OrderDTO orderDTO){
        LOGGER.info("Request for placing order");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded","LoginController");
        orderService.createOrderFromFoodId(orderDTO,LoginController.getCurrentUser());
        return ResponseEntity.accepted().headers(headers).body(orderDTO);
    }

    @GetMapping("/getUserOrders")
    public ResponseEntity<List<Order>> getOrders(){
        LOGGER.info("Request for getting orders of user");
        List<Order> orders = LoginController.getCurrentUser().getOrder();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/getOrderFoods/{id}")
    public ResponseEntity<List<Food>> getOrderFoods(@PathVariable int id){
        LOGGER.info("Request for getting foods of order with id: " + id);
        List<Food> foods = orderService.findById(id).getFood();
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

}
