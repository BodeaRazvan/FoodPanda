package com.example.foodpanda.controller;

import com.example.foodpanda.dto.FoodDTO;
import com.example.foodpanda.dto.FoodMapper;
import com.example.foodpanda.dto.RestaurantDTO;
import com.example.foodpanda.dto.RestaurantMapper;
import com.example.foodpanda.entity.Food;
import com.example.foodpanda.entity.Restaurant;
import com.example.foodpanda.service.FoodService;
import com.example.foodpanda.service.RestaurantService;
import com.example.foodpanda.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@CrossOrigin
public class AdminController {
    private final RestaurantService restaurantService;
    private final UserService userService;
    private final FoodService foodService;
    private final RestaurantMapper restaurantMapper = new RestaurantMapper();

    public AdminController(RestaurantService restaurantService, UserService userService, FoodService foodService) {
        this.restaurantService = restaurantService;
        this.userService = userService;
        this.foodService = foodService;
    }

    @PostMapping("/addRestaurant")
        public ResponseEntity<Restaurant> addRestaurant(@RequestBody RestaurantDTO restaurantDTO){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded","LoginController");
        Restaurant currentRestaurant = LoginController.getCurrentUser().getRestaurant();
        if(currentRestaurant!=null){
            if(!restaurantDTO.getName().equals("")){
                currentRestaurant.setName(restaurantDTO.getName());
            }
            if(!restaurantDTO.getAvailableZones().equals("")){
                currentRestaurant.setAvailableZones(restaurantDTO.getAvailableZones());
            }
            if(!restaurantDTO.getLocation().equals("")){
                currentRestaurant.setLocation(restaurantDTO.getLocation());
            }
            restaurantService.save(currentRestaurant);
            return  ResponseEntity.badRequest().headers(headers).body(currentRestaurant);
        }else{
            if (restaurantDTO.getAvailableZones().equals("") || restaurantDTO.getLocation().equals("") || restaurantDTO.getName().equals("")){
                return ResponseEntity.badRequest().headers(headers).body(null);
            }
            Restaurant restaurant = restaurantMapper.toEntity(restaurantDTO);
            restaurant.setOwner(LoginController.getCurrentUser());
            restaurantService.save(restaurant);
            return ResponseEntity.ok().headers(headers).body(restaurant);
        }
    }

    @GetMapping("/getRestaurant")
    public ResponseEntity<Restaurant> getRestaurant(){
       // System.out.println(LoginController.getCurrentUser().getRestaurant().getFoods().get(0).getCategory());
       // System.out.println(LoginController.getCurrentUser().getUsername());
        return new ResponseEntity<>(LoginController.getCurrentUser().getRestaurant(), HttpStatus.OK);
    }

    @PostMapping("/addFood")
    public ResponseEntity<Food> addFood(@RequestBody FoodDTO foodDTO){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded","LoginController");
        Restaurant currentRestaurant = LoginController.getCurrentUser().getRestaurant();
        if(foodDTO.getCategory().equals("") || foodDTO.getName().equals("") || foodDTO.getPrice().equals("") || foodDTO.getDescription().equals("")){
            return ResponseEntity.badRequest().headers(headers).body(null);
        }
        Food food = FoodMapper.toEntity(foodDTO);
        foodService.save(food);
        return ResponseEntity.ok().headers(headers).body(food);
    }


}
