package com.example.foodpanda;

import com.example.foodpanda.entity.Category;
import com.example.foodpanda.entity.Food;
import com.example.foodpanda.entity.Order;
import com.example.foodpanda.entity.Restaurant;
import com.example.foodpanda.repository.FoodRepository;
import com.example.foodpanda.repository.RestaurantRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class RestaurantRepositoryTest {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @BeforeEach
    public void init(){
        List<Restaurant> restaurants = Arrays.asList(
                new Restaurant("res1","test1","cheap1",null),
                new Restaurant("res2","test2","cheap2",null)
        );
        restaurantRepository.saveAll(restaurants);
    }

    @AfterEach
    public void clean(){
        restaurantRepository.deleteAll();
    }

    @Test
    public void saveAllSuccess(){
        List<Restaurant> restaurants = Arrays.asList(
                new Restaurant("res3","test3","cheap3",null),
                new Restaurant("res4","test4","cheap4",null),
                new Restaurant("res5","test5","cheap5",null)
        );

        Iterable<Restaurant> allRestaurants = restaurantRepository.saveAll(restaurants);
        assert restaurants.iterator().hasNext();
    }

    @Test
    public void findAllSuccess(){
        List<Restaurant> restaurants = restaurantRepository.findAll();
        assertThat(restaurants).hasSize(2);
    }

    @Test
    public void findByIdSuccess(){
        Restaurant restaurant = restaurantRepository.findById(1);
        assertEquals("res1", restaurant.getName());
    }
}
