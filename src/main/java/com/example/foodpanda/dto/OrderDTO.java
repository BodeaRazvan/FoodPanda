package com.example.foodpanda.dto;

import java.util.List;

public class OrderDTO {
    private List<Integer> ids;
    private String details;

    public OrderDTO() {}

    public OrderDTO(List<Integer> itemIds, String details) {
        this.ids = itemIds;
        this.details = details;
    }

    public List<Integer> getItemIds() {
        return ids;
    }

    public void setItemIds(List<Integer> itemIds) {
        this.ids = itemIds;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
