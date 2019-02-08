package com.gayko.bookstore.converters.impl.entity;

import com.gayko.bookstore.converters.Converter;
import com.gayko.bookstore.dao.model.UserItemId;
import com.gayko.bookstore.model.impl.UserItemIdDTO;
import org.springframework.stereotype.Component;

@Component("userItemIdConverter")
public class UserItemIdConverter implements Converter<UserItemIdDTO,UserItemId> {
    @Override
    public UserItemId toEntity(UserItemIdDTO dto) {
        return new UserItemId(dto.getItemId(), dto.getUserId());
    }
}
