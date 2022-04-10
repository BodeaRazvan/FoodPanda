package com.example.foodpanda.service;

import com.example.foodpanda.entity.Food;
import com.example.foodpanda.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {
    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public List<Food> findAll(){
        return foodRepository.findAll();
    }

    public void save(Food food){
        foodRepository.save(food);
    }

    public Food findById(int id){
        return foodRepository.findById(id);
    }

}
