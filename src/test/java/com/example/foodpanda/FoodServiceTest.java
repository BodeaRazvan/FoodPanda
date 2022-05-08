package com.example.foodpanda;

import com.example.foodpanda.entity.Category;
import com.example.foodpanda.entity.Food;
import com.example.foodpanda.entity.Restaurant;
import com.example.foodpanda.repository.FoodRepository;
import com.example.foodpanda.service.FoodService;
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
public class FoodServiceTest {
   @Mock
   private FoodRepository foodRepository;

   FoodService foodService;

   @BeforeEach
    public void init(){
       foodService = new FoodService(foodRepository);
   }

   @Test
    public void saveFoodSuccess(){
      Food food = new Food(1,"test","cheap",10.0, Category.BEVERAGE,null,null);
      when(foodRepository.save(any(Food.class))).thenReturn(food);

      Food savedFood = foodService.save(food);
      assertEquals(savedFood.getName(),"test");
   }

   @Test
    public void foodExistsInDbSuccess(){
      Food food = new Food(1,"test","cheap",10.0, Category.BEVERAGE,null,null);
      Food food1 = new Food(2,"test1","cheap",10.0, Category.BEVERAGE,null,null);
      List<Food> foods = new ArrayList<>();
      foods.add(food);
      foods.add(food1);

      when(foodRepository.findAll()).thenReturn(foods);

      List<Food> foundFoods = foodService.findAll();
      assertThat(foundFoods).contains(food);
      assertThat(foundFoods).contains(food1);
      assertEquals(foundFoods.size(),2);
   }
}
