package com.example.foodpanda;

import com.example.foodpanda.controller.AdminController;
import com.example.foodpanda.entity.Category;
import com.example.foodpanda.entity.Food;
import com.example.foodpanda.entity.Order;
import com.example.foodpanda.entity.Restaurant;
import com.example.foodpanda.repository.FoodRepository;
import com.example.foodpanda.repository.RestaurantRepository;
import com.example.foodpanda.security.jwt.JwtAuthenticationFilter;
import com.example.foodpanda.security.jwt.JwtService;
import com.example.foodpanda.service.FoodService;
import com.example.foodpanda.service.RestaurantService;
import com.example.foodpanda.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

/*
@RunWith(SpringRunner.class)
@WebMvcTest(AdminController.class)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    JwtService jwtService;

    @MockBean
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    UserService userService;

    @Mock
    private RestaurantRepository restaurantRepository;

    RestaurantService restaurantService;

    @BeforeEach
    public void init(){
        restaurantService = new RestaurantService(restaurantRepository);
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    public void getAllRestaurantsSuccess() throws Exception {
        List<Restaurant> restaurants = Arrays.asList(
                new Restaurant("name1","location1","zone1",null),
                new Restaurant("name2","location2","zone2",null),
                new Restaurant("name3","location3","zone3",null)
        );

        when(restaurantService.findAll()).thenReturn(restaurants);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/getRestaurants")
        ).andExpect(status().isOk()).andExpect(content().json("[{},{},{}]"));
    }

}*/
