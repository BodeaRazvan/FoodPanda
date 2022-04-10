package com.example.foodpanda.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String name;

    @Column
    private String location;

    @Column
    private String availableZones;

    @OneToMany
    @JsonIgnoreProperties("restaurant")
    private List<Order> orders;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("restaurant")
    @JsonBackReference
    private User owner;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.DETACH, orphanRemoval = true)
    @JsonIgnoreProperties("restaurant")
    private List<Food> foods;

    public Restaurant(String name, String location, String availableZones, User owner) {
        this.name = name;
        this.location = location;
        this.availableZones = availableZones;
        this.owner = owner;
    }

    public Restaurant() {

    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAvailableZones() {
        return availableZones;
    }

    public void setAvailableZones(String availableZones) {
        this.availableZones = availableZones;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
