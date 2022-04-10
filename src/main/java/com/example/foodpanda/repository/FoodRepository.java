package com.example.foodpanda.repository;

import com.example.foodpanda.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {
    List<Food>  findAll();
    Food findById(int id);
}
