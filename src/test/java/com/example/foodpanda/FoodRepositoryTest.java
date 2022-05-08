package com.example.foodpanda;

import com.example.foodpanda.entity.Category;
import com.example.foodpanda.entity.Food;
import com.example.foodpanda.entity.Order;
import com.example.foodpanda.entity.Restaurant;
import com.example.foodpanda.repository.FoodRepository;
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
public class FoodRepositoryTest {
    @Autowired
    private FoodRepository foodRepository;

    @BeforeEach
    public void init(){
        List<Food> foods = Arrays.asList(
                new Food(1,"test","cheap",10.0, Category.BEVERAGE,null,null),
                new Food(2,"test2","cheap2",15.0, Category.BREAKFAST,null,null)
        );
        foodRepository.saveAll(foods);
    }

    @AfterEach
    public void clean(){
        foodRepository.deleteAll();
    }

    @Test
    public void saveAllSuccess(){
        Restaurant restaurant = new Restaurant();
        Order order = new Order();
        List<Order> orders = List.of(order);
        List<Food> foods = Arrays.asList(
                new Food(3,"test3","cheap3",20.0, Category.BREAKFAST,restaurant,null),
                new Food(4,"test4","cheap4",25.0, Category.LUNCH,null,orders),
                new Food(5,"test5","cheap5",30.0, Category.BREAKFAST,null,null)
        );

        Iterable<Food> allFoods = foodRepository.saveAll(foods);
        assert allFoods.iterator().hasNext();
    }

    @Test
    public void findAllSuccess(){
        List<Food> foods = foodRepository.findAll();
        assertThat(foods).hasSize(2);
    }

    @Test
    public void findByIdSuccess(){
        Food food = foodRepository.findById(1);
        assertEquals("test", food.getName());
    }
}
