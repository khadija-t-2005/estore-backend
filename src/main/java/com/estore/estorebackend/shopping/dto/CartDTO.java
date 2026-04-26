package com.estore.estoreProject.shopping.dto;

import java.util.List;

public class CartResponse {

    private Long id;
    private Long userId;
    private List<CartItemResponse> items;
    private double total;

    public CartResponse() {
    }

    public CartResponse(Long id, Long userId, List<CartItemResponse> items, double total) {
        this.id = id;
        this.userId = userId;
        this.items = items;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public List<CartItemResponse> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setItems(List<CartItemResponse> items) {
        this.items = items;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
