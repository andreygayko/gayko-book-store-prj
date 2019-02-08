package com.gayko.bookstore.model.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemDTO {

    private Long id;

    private String name;

    private String description;

    private String uniqueNumber;

    private BigDecimal price;

    private List<OrderDTO> orders = new ArrayList<>();

    private List<DiscountDTO> discounts = new ArrayList<>();

    public ItemDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUniqueNumber() {
        return uniqueNumber;
    }

    public void setUniqueNumber(String uniqueNumber) {
        this.uniqueNumber = uniqueNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<OrderDTO> getOrders() { return orders; }

    public void setOrders(List<OrderDTO> orders) { this.orders = orders; }

    public List<DiscountDTO> getDiscounts() { return discounts; }

    public void setDiscounts(List<DiscountDTO> discounts) { this.discounts = discounts; }
}