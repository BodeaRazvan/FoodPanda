package com.example.foodpanda.service;

import com.example.foodpanda.entity.Food;
import com.example.foodpanda.entity.Order;
import com.example.foodpanda.entity.Restaurant;
import com.example.foodpanda.entity.User;
import com.example.foodpanda.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final FoodService foodService;
    private final RestaurantService restaurantService;

    @Autowired
    public OrderService(OrderRepository orderRepository, FoodService foodService, RestaurantService restaurantService) {
        this.orderRepository = orderRepository;
        this.foodService = foodService;
        this.restaurantService = restaurantService;
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public void createOrderFromFoodId(List<Integer> orderedFoodsId, User user){
        List<Food> foods = new ArrayList<>();
        double price =0.0;
        for(Integer id : orderedFoodsId){
            Food foundFood = foodService.findById(id);
            price+=foundFood.getPrice();
            foods.add(foundFood);
        }
        Restaurant restaurant = foods.get(0).getRestaurant();
        BigDecimal bd = new BigDecimal(price).setScale(2, RoundingMode.HALF_UP);
        Order order = new Order("PENDING",bd.doubleValue(),user,foods,restaurant);
        try {
            orderRepository.save(order);
            restaurant.getOrders().add(order);
            restaurantService.save(restaurant);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Order findById(int id){
        return orderRepository.findById(id);
    }

    public Order denyOrder(int id, Restaurant restaurant){
        Order order = orderRepository.findById(id);
        restaurant.getOrders().removeIf(order1 -> order1.getId() == id);
        order.setStatus("DECLINED");;
        restaurant.getOrders().add(order);
        restaurantService.save(restaurant);
        return orderRepository.save(order);
    }

    public Order nextStatus(int id, Restaurant restaurant){
        Order order = orderRepository.findById(id);
        restaurant.getOrders().removeIf(order1 -> order1.getId() == id);
        switch (order.getStatus()) {
            case "PENDING" -> order.setStatus("ACCEPTED");
            case "ACCEPTED" -> order.setStatus("IN DELIVERY");
            case "IN DELIVERY" -> order.setStatus("DELIVERED");
        }
        restaurant.getOrders().add(order);
        restaurantService.save(restaurant);
        return orderRepository.save(order);
    }
}
