package com.example.foodpanda.dto;

import com.example.foodpanda.controller.LoginController;
import com.example.foodpanda.entity.Category;
import com.example.foodpanda.entity.Food;
import com.example.foodpanda.entity.User;

public class FoodMapper {

    private static volatile FoodMapper foodMapper = null;
    private FoodMapper(){}

    public static FoodMapper getInstance(){
        if(foodMapper == null){
            foodMapper = new FoodMapper();
        }
        return foodMapper;
    }

    public static Food toEntity(FoodDTO foodDTO) {
        User currentUser = LoginController.getCurrentUser();
        Food food = new Food();
        food.setName(foodDTO.getName());
        food.setDescription(foodDTO.getDescription());
        food.setPrice(Double.parseDouble(foodDTO.getPrice()));
        food.setOrders(null);
        food.setRestaurant(currentUser.getRestaurant());
        if(foodDTO.getCategory().equals("Breakfast")) {
            food.setCategory(Category.BREAKFAST);
        }
        if(foodDTO.getCategory().equals("Lunch")) {
            food.setCategory(Category.LUNCH);
        }
        if(foodDTO.getCategory().equals("Dessert")) {
            food.setCategory(Category.DESSERT);
        }
        if(foodDTO.getCategory().equals("Beverage")) {
            food.setCategory(Category.BEVERAGE);
        }
        return food;
    }
}
