package com.example.foodpanda.dto;

import com.example.foodpanda.entity.Restaurant;

public class RestaurantMapper {
    public Restaurant toEntity(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = new Restaurant();
        restaurant.setLocation(restaurantDTO.getLocation());
        restaurant.setAvailableZones(restaurantDTO.getAvailableZones());
        restaurant.setName(restaurantDTO.getName());
        return restaurant;
    }
}
