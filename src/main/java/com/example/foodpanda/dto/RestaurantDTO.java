package com.example.foodpanda.dto;

public class RestaurantDTO {
    private String name;
    private String location;
    private String availableZones;

    public RestaurantDTO(String name, String location, String availableZones) {
        this.name = name;
        this.location = location;
        this.availableZones = availableZones;
    }
    public RestaurantDTO(){
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
}
