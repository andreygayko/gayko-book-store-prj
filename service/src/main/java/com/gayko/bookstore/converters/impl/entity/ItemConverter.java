package com.gayko.bookstore.converters.impl.entity;

import com.gayko.bookstore.converters.Converter;
import com.gayko.bookstore.dao.model.Discount;
import com.gayko.bookstore.dao.model.Item;
import com.gayko.bookstore.dao.model.Order;
import com.gayko.bookstore.model.impl.DiscountDTO;
import com.gayko.bookstore.model.impl.ItemDTO;
import com.gayko.bookstore.model.impl.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("itemConverter")
public class ItemConverter implements Converter<ItemDTO, Item> {

    @Autowired
    @Qualifier("orderConverter") Converter<OrderDTO, Order> orderConverter;
    @Autowired
    @Qualifier("discountConverter") Converter<DiscountDTO, Discount> discountConverter;

    @Override
    public Item toEntity(ItemDTO dto) {

        if (dto == null) {
            return null;
        }
        Item item = new Item();
        item.setId(dto.getId());
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setPrice(dto.getPrice());
        item.setUniqueNumber(dto.getUniqueNumber());
        item.setOrders(orderConverter.toEntityList(dto.getOrders()));
        item.setDiscounts(discountConverter.toEntityList(dto.getDiscounts()));
        return item;
    }
}
