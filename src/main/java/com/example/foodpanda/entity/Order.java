package com.example.foodpanda.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String status;

    @Column
    private double price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("order")
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @JsonIgnoreProperties("orders")
    @JsonBackReference
    private Restaurant restaurant;

    @ManyToMany
    @JoinTable(name= "ordered_food",
                joinColumns = @JoinColumn(name = "order_id"),
                inverseJoinColumns = @JoinColumn(name = "food_id"))
    @JsonIgnoreProperties("orders")
    @JsonBackReference
    private List<Food> food;

    public Order(int id, String status, double price, User user, List<Food> food) {
        this.id = id;
        this.status = status;
        this.price = price;
        this.user = user;
        this.food = food;
    }
    public Order(String status, double price, User user, List<Food> food,Restaurant restaurant) {
        this.status = status;
        this.price = price;
        this.user = user;
        this.food = food;
        this.restaurant = restaurant;;
    }
    public Order(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Food> getFood() {
        return food;
    }

    public void setFood(List<Food> food) {
        this.food = food;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
