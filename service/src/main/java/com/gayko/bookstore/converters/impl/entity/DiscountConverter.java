package com.gayko.bookstore.converters.impl.entity;

import com.gayko.bookstore.converters.Converter;
import com.gayko.bookstore.dao.model.Discount;
import com.gayko.bookstore.dao.model.Item;
import com.gayko.bookstore.dao.model.User;
import com.gayko.bookstore.model.impl.DiscountDTO;
import com.gayko.bookstore.model.impl.ItemDTO;
import com.gayko.bookstore.model.impl.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("discountConverter")
public class DiscountConverter implements Converter<DiscountDTO, Discount> {

    @Autowired
    @Qualifier("itemConverter") Converter<ItemDTO, Item> itemConverter;
    @Autowired
    @Qualifier("userConverter") Converter<UserDTO, User> userConverter;

    @Override
    public Discount toEntity(DiscountDTO dto) {

        if (dto == null) {
            return null;
        }
        Discount discount = new Discount();
        discount.setId(dto.getId());
        discount.setName(dto.getName());
        discount.setInterestRate(dto.getInterestRate());
        discount.setFinalDate(dto.getFinalDate());
        discount.setItems(itemConverter.toEntityList(dto.getItems()));
        return discount;
    }
}
