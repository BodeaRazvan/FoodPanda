package com.example.foodpanda;

import com.example.foodpanda.entity.Category;
import com.example.foodpanda.entity.Food;
import com.example.foodpanda.entity.Restaurant;
import com.example.foodpanda.repository.FoodRepository;
import com.example.foodpanda.repository.RestaurantRepository;
import com.example.foodpanda.service.FoodService;
import com.example.foodpanda.service.RestaurantService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {
    @Mock
    private RestaurantRepository restaurantRepository;

    RestaurantService restaurantService;

    @BeforeEach
    public void init(){
        restaurantService = new RestaurantService(restaurantRepository);
    }

    @Test
    public void saveRestaurantSuccess(){
        Restaurant restaurant = new Restaurant("restaurant1","locations1","zones1",null);
        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(restaurant);

        Restaurant savedRestaurant = restaurantService.save(restaurant);
        assertEquals(savedRestaurant.getName(),"restaurant1");
    }

    @Test
    public void restaurantExistsSuccess(){
        Restaurant restaurant1 = new Restaurant("restaurant1","locations1","zones1",null);
        Restaurant restaurant2 = new Restaurant("restaurant2","locations2","zones2",null);
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(restaurant1);
        restaurants.add(restaurant2);

        when(restaurantRepository.findAll()).thenReturn(restaurants);

        List<Restaurant> foundRestaurants = restaurantService.findAll();
        assertThat(foundRestaurants).contains(restaurant1);
        assertThat(foundRestaurants).contains(restaurant2);
        assertEquals(foundRestaurants.size(),2);
    }
}
