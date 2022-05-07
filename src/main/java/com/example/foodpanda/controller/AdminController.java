package com.example.foodpanda.controller;

import com.example.foodpanda.dto.FoodDTO;
import com.example.foodpanda.dto.FoodMapper;
import com.example.foodpanda.dto.RestaurantDTO;
import com.example.foodpanda.dto.RestaurantMapper;
import com.example.foodpanda.entity.Food;
import com.example.foodpanda.entity.Order;
import com.example.foodpanda.entity.Restaurant;
import com.example.foodpanda.logger.MyLogger;
import com.example.foodpanda.service.FoodService;
import com.example.foodpanda.service.OrderService;
import com.example.foodpanda.service.RestaurantService;
import com.example.foodpanda.service.UserService;
import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@CrossOrigin
public class AdminController {
    private static final Logger LOGGER = MyLogger.getInstance();
    private final RestaurantService restaurantService;
    private final UserService userService;
    private final FoodService foodService;
    private final OrderService orderService;
    private final RestaurantMapper restaurantMapper = new RestaurantMapper();

    public AdminController(RestaurantService restaurantService, UserService userService, FoodService foodService, OrderService orderService) {
        this.restaurantService = restaurantService;
        this.userService = userService;
        this.foodService = foodService;
        this.orderService = orderService;
    }

    @PostMapping("/addRestaurant")
        public ResponseEntity<Restaurant> addRestaurant(@RequestBody RestaurantDTO restaurantDTO){
        LOGGER.info("Request for adding restaurant");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded","LoginController");
        Restaurant currentRestaurant = LoginController.getCurrentUser().getRestaurant();
        if(currentRestaurant!=null){
            LOGGER.warn("Restaurant already exists");
            if(!restaurantDTO.getName().equals("")){
                currentRestaurant.setName(restaurantDTO.getName());
            }
            if(!restaurantDTO.getAvailableZones().equals("")){
                currentRestaurant.setAvailableZones(restaurantDTO.getAvailableZones());
            }
            if(!restaurantDTO.getLocation().equals("")){
                currentRestaurant.setLocation(restaurantDTO.getLocation());
            }
            try{
            restaurantService.save(currentRestaurant);
            LOGGER.info("Restaurant updated");
            return  ResponseEntity.badRequest().headers(headers).body(currentRestaurant);
            }catch (Exception e){
                LOGGER.error("Error while adding restaurant");
                e.printStackTrace();
                return ResponseEntity.badRequest().headers(headers).body(null);}
        }else{ LOGGER.info("Adding new restaurant");
            if (restaurantDTO.getAvailableZones().equals("") || restaurantDTO.getLocation().equals("") || restaurantDTO.getName().equals("")){
                LOGGER.warn("Empty fields");
                return ResponseEntity.badRequest().headers(headers).body(null);
            }
            Restaurant restaurant = restaurantMapper.toEntity(restaurantDTO);
            restaurant.setOwner(LoginController.getCurrentUser());
            restaurant.setOrders(null);
            LoginController.getCurrentUser().setRestaurant(restaurant);
            try{
            restaurantService.save(restaurant);
            LOGGER.info("Restaurant added");
            return ResponseEntity.ok().headers(headers).body(restaurant);
            }catch (Exception e){
                LOGGER.error("Error while adding restaurant");
                e.printStackTrace();
                return ResponseEntity.badRequest().headers(headers).body(null);}
        }
    }

    @GetMapping("/getRestaurant")
    public ResponseEntity<Restaurant> getRestaurant(){
       LOGGER.info("Request for getting restaurant");
       // System.out.println(LoginController.getCurrentUser().getRestaurant().getFoods().get(0).getCategory());
       // System.out.println(LoginController.getCurrentUser().getUsername());
        /*
        if(LoginController.getCurrentUser().getRestaurant()!=null){
            for(Food food: LoginController.getCurrentUser().getRestaurant().getFoods()){
                System.out.println(food.getName());
            }
        }*/
        return new ResponseEntity<>(LoginController.getCurrentUser().getRestaurant(), HttpStatus.OK);
    }

    @PostMapping("/addFood")
    public ResponseEntity<Food> addFood(@RequestBody FoodDTO foodDTO){
        LOGGER.info("Request for adding food");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded","LoginController");
        Restaurant currentRestaurant = LoginController.getCurrentUser().getRestaurant();
        if(foodDTO.getCategory().equals("") || foodDTO.getName().equals("") || foodDTO.getPrice().equals("") || foodDTO.getDescription().equals("")){
            LOGGER.warn("Empty fields");
            return ResponseEntity.badRequest().headers(headers).body(null);
        }
        FoodMapper foodMapper = FoodMapper.getInstance();
        Food food = foodMapper.toEntity(foodDTO);
        try{
            foodService.save(food);
            LoginController.getCurrentUser().getRestaurant().getFoods().add(food);
        }catch (Exception e){
            LOGGER.error("Error while adding food");
            e.printStackTrace();
        }
        LOGGER.info("Food added");
        return ResponseEntity.ok().headers(headers).body(food);
    }

    @GetMapping("/getRestaurantFoods")
    public ResponseEntity<List<Food>> getRestaurantFoods(){
        LOGGER.info("Request for getting restaurant foods");
        List<Food> foods = LoginController.getCurrentUser().getRestaurant().getFoods();
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @GetMapping("/getOrders")
    public ResponseEntity<List<Order>> getRestaurantOrders(){
        LOGGER.info("Request for getting restaurant orders");
        List<Order> orders = LoginController.getCurrentUser().getRestaurant().getOrders();
        System.out.println(orders.size());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("/declineOrder")
    public ResponseEntity<Order> denyOrder(@RequestBody Integer orderId){
        LOGGER.info("Request for declining order");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded","LoginController");
        System.out.println("Decline order" + orderId);
        Order order = orderService.denyOrder(orderId,LoginController.getCurrentUser().getRestaurant());
        return ResponseEntity.ok().headers(headers).body(order);
    }

    @PostMapping("/nextOrderStatus")
    public ResponseEntity<Order> nextStatus(@RequestBody Integer orderId){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded","LoginController");
        Order order = orderService.nextStatus(orderId,LoginController.getCurrentUser().getRestaurant());
        return ResponseEntity.ok().headers(headers).body(order);
    }
}
