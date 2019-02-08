package com.gayko.bookstore.converters.impl.dto;

import com.gayko.bookstore.converters.DTOConverter;
import com.gayko.bookstore.dao.model.UserItemId;
import com.gayko.bookstore.model.impl.UserItemIdDTO;
import org.springframework.stereotype.Component;

@Component("userItemIdDTOConverter")
public class UserItemIdDTOConverter implements DTOConverter<UserItemId, UserItemIdDTO> {
    @Override
    public UserItemIdDTO toDTO(UserItemId entity){
        return new UserItemIdDTO(entity.getItemId(), entity.getUserId());
    }
}
