package com.example.foodpanda.service;

import com.example.foodpanda.entity.Restaurant;
import com.example.foodpanda.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> findAll(){
        return restaurantRepository.findAll();
    }

    public void save(Restaurant restaurant){
        restaurantRepository.save(restaurant);
    }

    public Restaurant findById(int id){
        return restaurantRepository.findById(id);
    }

}
