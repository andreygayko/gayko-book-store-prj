package com.gayko.bookstore.model.impl;

public class UserItemIdDTO {

    private Long itemId;

    private Long userId;

    public UserItemIdDTO(Long itemId, Long userId) {
        this.itemId = itemId;
        this.userId = userId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserItemIdDTO{" +
                "itemId=" + itemId +
                ", userId=" + userId +
                '}';
    }
}
