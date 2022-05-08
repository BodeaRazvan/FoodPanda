package com.example.foodpanda.service;

import com.example.foodpanda.entity.Food;
import com.example.foodpanda.logger.MyLogger;
import com.example.foodpanda.repository.FoodRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * FoodService class
 * Handles all the business logic for the Food entity
 */
@Service
public class FoodService {
    private final FoodRepository foodRepository;
    private static final Logger LOGGER = MyLogger.getInstance();

    /**
     * Instantiates a new Food service.
     *
     * @param foodRepository the food repository
     */
    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    /**
     * Find all foods
     *
     * @return a list with all the foods
     */
    public List<Food> findAll()
    {   LOGGER.info("Finding all foods");
        return foodRepository.findAll();
    }

    /**
     * Save food to the database
     *
     * @param food we want to save
     * @return
     */
    public Food save(Food food){
        LOGGER.info("Saving food");
        foodRepository.save(food);
        return food;
    }

    /**
     * Find food by id
     *
     * @param id the id of the food we want to find
     * @return the food that was found with the specified id
     */
    public Food findById(int id){
        LOGGER.info("Finding food with id {}", id);
        return foodRepository.findById(id);
    }

}
