package com.gayko.bookstore.model.impl;


import java.time.LocalDateTime;
import java.util.Objects;

public class OrderDTO {

    private UserItemIdDTO id;

    private LocalDateTime created;

    private int quantity;

    private String status;

    private ItemDTO itemDTO;

    private UserDTO userDTO;

    public OrderDTO() {
    }

    public UserItemIdDTO getId() {
        return id;
    }

    public void setId(UserItemIdDTO id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ItemDTO getItemDTO() {
        return itemDTO;
    }

    public void setItemDTO(ItemDTO itemDTO) {
        this.itemDTO = itemDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return quantity == orderDTO.quantity &&
                Objects.equals(id, orderDTO.id) &&
                Objects.equals(created, orderDTO.created) &&
                Objects.equals(status, orderDTO.status);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, created, quantity, status);
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", created=" + created +
                ", quantity=" + quantity +
                ", status='" + status + '\'' +
                ", itemDTO=" + itemDTO +
                ", userDTO=" + userDTO +
                '}';
    }
}
