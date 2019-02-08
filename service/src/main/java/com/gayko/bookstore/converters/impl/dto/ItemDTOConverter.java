package com.gayko.bookstore.converters.impl.dto;

import com.gayko.bookstore.converters.DTOConverter;
import com.gayko.bookstore.dao.model.Discount;
import com.gayko.bookstore.dao.model.Item;
import com.gayko.bookstore.dao.model.Order;
import com.gayko.bookstore.model.impl.DiscountDTO;
import com.gayko.bookstore.model.impl.ItemDTO;
import com.gayko.bookstore.model.impl.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("itemDTOConverter")
public class ItemDTOConverter implements DTOConverter<Item, ItemDTO> {

    @Autowired
    @Qualifier("orderDTOConverter") DTOConverter<Order, OrderDTO> orderDTOConverter;
    @Autowired
    @Qualifier("discountDTOConverter") DTOConverter<Discount, DiscountDTO> discountDTOConverter;


    @Override
    public ItemDTO toDTO(Item entity) {

        if (entity == null) {
            return null;
        }
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(entity.getId());
        itemDTO.setName(entity.getName());
        itemDTO.setDescription(entity.getDescription());
        itemDTO.setPrice(entity.getPrice());
        itemDTO.setUniqueNumber(entity.getUniqueNumber());
        //itemDTO.setOrders(orderDTOConverter.toDTOList(entity.getOrders()));
        itemDTO.setDiscounts(discountDTOConverter.toDTOList(entity.getDiscounts()));
        return itemDTO;
    }
}
