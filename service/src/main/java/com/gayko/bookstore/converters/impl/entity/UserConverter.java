package com.gayko.bookstore.converters.impl.entity;

import com.gayko.bookstore.converters.Converter;
import com.gayko.bookstore.dao.model.Discount;
import com.gayko.bookstore.dao.model.Role;
import com.gayko.bookstore.dao.model.User;
import com.gayko.bookstore.model.impl.DiscountDTO;
import com.gayko.bookstore.model.impl.RoleDTO;
import com.gayko.bookstore.model.impl.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("userConverter")
public class UserConverter implements Converter<UserDTO, User> {

    @Autowired
    @Qualifier("roleConverter")
    Converter<RoleDTO, Role> roleConverter;
    @Autowired
    @Qualifier("discountConverter")
    Converter<DiscountDTO, Discount> discountConverter;

    public User toEntity(UserDTO dto) {

        if (dto == null) {
            return null;
        }
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setSurname(dto.getSurname());
        user.setPassword(dto.getPassword());
        user.setRole(roleConverter.toEntity(dto.getRole()));
        //   user.setOrders(orderConverter.toEntityList(dto.getOrders()));
        user.setDiscount(discountConverter.toEntity(dto.getDiscount()));
        return user;
    }
}
