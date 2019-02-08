package com.gayko.bookstore.converters.impl.dto;

import com.gayko.bookstore.converters.DTOConverter;
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

@Component("orderDTOConverter")
public class OrderDTOConverter implements DTOConverter<Order, OrderDTO> {

    @Autowired
    @Qualifier("itemDTOConverter") DTOConverter<Item, ItemDTO> itemDTOConverter;
    @Autowired
    @Qualifier("userDTOConverter") DTOConverter<User, UserDTO> userDTOConverter;
    @Autowired
    @Qualifier("userItemIdDTOConverter") DTOConverter<UserItemId, UserItemIdDTO> userItemIdDTODTOConverter;

    @Override
    public OrderDTO toDTO(Order entity) {

        if (entity == null) {
            return null;
        }
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(userItemIdDTODTOConverter.toDTO(entity.getId()));
        orderDTO.setCreated(entity.getCreated());
        orderDTO.setQuantity(entity.getQuantity());
        orderDTO.setStatus(entity.getStatus().name());
        orderDTO.setItemDTO(itemDTOConverter.toDTO(entity.getItem()));
        orderDTO.setUserDTO(userDTOConverter.toDTO((entity.getUser())));
        return orderDTO;
    }
}