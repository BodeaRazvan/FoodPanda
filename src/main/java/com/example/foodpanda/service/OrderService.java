package com.example.foodpanda.service;

import com.example.foodpanda.dto.OrderDTO;
import com.example.foodpanda.entity.Food;
import com.example.foodpanda.entity.Order;
import com.example.foodpanda.entity.Restaurant;
import com.example.foodpanda.entity.User;
import com.example.foodpanda.logger.MyLogger;
import com.example.foodpanda.mail.MailHandler;
import com.example.foodpanda.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Order Service
 * Handles all the order related operations
 */
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final FoodService foodService;
    private final RestaurantService restaurantService;
    private static final Logger LOGGER = MyLogger.getInstance();
    @Autowired
    private MailHandler mailHandler;

    /**
     * Instantiates a new Order service.
     *
     * @param orderRepository   the order repository
     * @param foodService       the food service
     * @param restaurantService the restaurant service
     */
    @Autowired
    public OrderService(OrderRepository orderRepository, FoodService foodService, RestaurantService restaurantService) {
        this.orderRepository = orderRepository;
        this.foodService = foodService;
        this.restaurantService = restaurantService;
    }

    /**
     * Find all orders.
     *
     * @return a list containing all the orders
     */
    public List<Order> findAll() {
        LOGGER.info("Finding all orders");
        return orderRepository.findAll();
    }

    /**
     * Create a new order knowing the user and the foods he ordered.
     *
     * @param orderDTO Data transfer object containing information about the ordered food
     * @param user     the user we want to create the order for
     * @exception IllegalArgumentException if the user or the food is not found
     */
    public void createOrderFromFoodId(OrderDTO orderDTO, User user){
        LOGGER.info("Adding order for user " + user.getUsername());
        List<Food> foods = new ArrayList<>();
        double price =0.0;
        for(Integer id : orderDTO.getItemIds()){
            Food foundFood = foodService.findById(id);
            price+=foundFood.getPrice();
            foods.add(foundFood);
        }
        Restaurant restaurant = foods.get(0).getRestaurant();
        BigDecimal bd = new BigDecimal(price).setScale(2, RoundingMode.HALF_UP);
        Order order = new Order("PENDING",bd.doubleValue(),user,foods,restaurant,orderDTO.getDetails());
        try {
            orderRepository.save(order);
            restaurant.getOrders().add(order);
            restaurantService.save(restaurant);
            LOGGER.info("Order added for user " + user.getUsername());
            StringBuilder message = new StringBuilder("New order from: " + user.getUsername() + "\n\n" + "Order: \n");
            for(Food food : foods){
                message.append(food.getName()).append(", ").append(food.getPrice()).append("$, ").append(food.getDescription()).append("\n");
            }
            message.append("\nTotal price: ").append(bd).append("$\n\n");
            message.append("Address: ").append(user.getAddress()).append("\n\n");
            message.append("Special Details: ").append(orderDTO.getDetails()).append("\n");
            mailHandler.sendMail(foods.get(0).getRestaurant().getOwner().getEmail(), "New Order", message.toString());
            LOGGER.info("Email with the order was sent to the restaurant owner");
        }catch (Exception e){
            LOGGER.error("Error while adding order for user " + user.getUsername());
            e.printStackTrace();
        }
    }

    /**
     * Find order by id
     *
     * @param id the id of the order
     * @return the order with the given id
     */
    public Order findById(int id) {
        LOGGER.info("Finding order with id " + id);
        return orderRepository.findById(id);
    }

    /**
     * Deny an order (changes the status of the order to DECLINED)
     *
     * @param id         the id of the order we want to decline
     * @param restaurant the restaurant that has the specified order
     * @return           the order with the modified status (also saved in the database)
     */
    public Order denyOrder(int id, Restaurant restaurant){
        LOGGER.info("Denying order with id " + id);
        Order order = orderRepository.findById(id);
        restaurant.getOrders().removeIf(order1 -> order1.getId() == id);
        order.setStatus("DECLINED");;
        restaurant.getOrders().add(order);
        restaurantService.save(restaurant);
        LOGGER.info("Order with id " + id + " was denied");
        return orderRepository.save(order);
    }

    /**
     * Changes the status of the order to a new one based on the following order
     * PENDING -> ACCEPTED,
     * ACCEPTED -> IN DELIVERY,
     * IN DELIVERY -> DELIVERED
     *
     * @param id         the id of the order we want to change the status of
     * @param restaurant the restaurant that has the specified order
     * @return           the order with the modified status (also saved in the database)
     */
    public Order nextStatus(int id, Restaurant restaurant){
        LOGGER.info("Changing status of order with id " + id);
        Order order = orderRepository.findById(id);
        restaurant.getOrders().removeIf(order1 -> order1.getId() == id);
        switch (order.getStatus()) {
            case "PENDING" -> order.setStatus("ACCEPTED");
            case "ACCEPTED" -> order.setStatus("IN DELIVERY");
            case "IN DELIVERY" -> order.setStatus("DELIVERED");
        }
        restaurant.getOrders().add(order);
        restaurantService.save(restaurant);
        LOGGER.info("Status of order with id " + id + " was changed");
        return orderRepository.save(order);
    }


}
