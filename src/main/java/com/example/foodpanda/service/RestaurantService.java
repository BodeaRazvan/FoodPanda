package com.example.foodpanda.service;

import com.example.foodpanda.entity.Restaurant;
import com.example.foodpanda.logger.MyLogger;
import com.example.foodpanda.repository.RestaurantRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RestaurantService class
 * Handles operations related to restaurants
 */
@Service
public class RestaurantService {
    private static final Logger LOGGER = MyLogger.getInstance();
    private final RestaurantRepository restaurantRepository;

    /**
     * Instantiates a new Restaurant service.
     *
     * @param restaurantRepository the restaurant repository
     */
    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * Find all restaurants.
     *
     * @return a list containing all restaurants
     */
    public List<Restaurant> findAll() {
        LOGGER.info("Finding all restaurants");
        return restaurantRepository.findAll();
    }

    /**
     * Save a restaurant.
     *
     * @param restaurant the restaurant we want to save
     */
    public Restaurant save(Restaurant restaurant){
        LOGGER.info("Saving restaurant");
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    /**
     * Find restaurant by id.
     *
     * @param id the id of the restaurant we want to find
     * @return the restaurant that was found for the specified id
     */
    public Restaurant findById(int id){
        LOGGER.info("Finding restaurant with id {}", id);
        return restaurantRepository.findById(id);
    }

}
