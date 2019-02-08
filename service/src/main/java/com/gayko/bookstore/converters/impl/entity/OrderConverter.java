package com.gayko.bookstore.converters.impl.entity;

import com.gayko.bookstore.converters.Converter;
import com.gayko.bookstore.dao.model.Item;
import com.gayko.bookstore.dao.model.Order;
import com.gayko.bookstore.dao.model.User;
import com.gayko.bookstore.dao.model.UserItemId;
import com.gayko.bookstore.model.impl.ItemDTO;
import com.gayko.bookstore.model.impl.OrderDTO;
import com.gayko.bookstore.model.impl.UserDTO;
import com.gayko.bookstore.model.impl.UserItemIdDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("orderConverter")
public class OrderConverter implements Converter<OrderDTO, Order> {

    private final Converter<ItemDTO, Item> itemConverter;
    private final Converter<UserDTO, User> userConverter;
    private final Converter<UserItemIdDTO, UserItemId> userItemIdConverter;

    @Autowired
    public OrderConverter(@Qualifier("itemConverter") Converter<ItemDTO, Item> itemConverter, @Qualifier("userConverter") Converter<UserDTO, User> userConverter, @Qualifier("userItemIdConverter") Converter<UserItemIdDTO, UserItemId> userItemIdConverter) {
        this.itemConverter = itemConverter;
        this.userConverter = userConverter;
        this.userItemIdConverter = userItemIdConverter;
    }

    @Override
    public Order toEntity(OrderDTO dto) {

        if (dto == null) {
            return null;
        }
        Order order = new Order();
        order.setId(userItemIdConverter.toEntity(dto.getId()));
        order.setQuantity(dto.getQuantity());
        order.setCreated(dto.getCreated());
        order.setItem(itemConverter.toEntity(dto.getItemDTO()));
        order.setUser(userConverter.toEntity((dto.getUserDTO())));
        return order;

    }
}
