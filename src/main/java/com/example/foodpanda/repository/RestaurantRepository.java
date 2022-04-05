package com.example.foodpanda.repository;

import com.example.foodpanda.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {
    List<Restaurant> findAll();
}
