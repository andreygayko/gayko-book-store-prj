package com.gayko.bookstore.dao.model;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserItemId implements Serializable {

    @Column(name = "f_user_id")
    private Long userId;

    @Column(name = "f_item_id")
    private Long itemId;

    public UserItemId() {
    }

    public UserItemId(Long userId, Long itemId) {
        this.userId = userId;
        this.itemId = itemId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "UserItemId{" +
                "userId=" + userId +
                ", itemId=" + itemId +
                '}';
    }
}
