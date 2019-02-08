package com.gayko.bookstore.converters.impl.entity;

import com.gayko.bookstore.converters.Converter;
import com.gayko.bookstore.dao.model.BusinessCard;
import com.gayko.bookstore.dao.model.User;
import com.gayko.bookstore.model.impl.BusinessCardDTO;
import com.gayko.bookstore.model.impl.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("businessCardConverter")
public class BusinessCardConverter implements Converter<BusinessCardDTO, BusinessCard> {

    @Autowired
    @Qualifier("userConverter") Converter<UserDTO, User> userConverter;

    @Override
    public BusinessCard toEntity(BusinessCardDTO dto) {

        if (dto == null) {
            return null;
        }
        BusinessCard businessCard = new BusinessCard();
        businessCard.setId(dto.getId());
        businessCard.setTitle(dto.getTitle());
        businessCard.setFullName(dto.getFullName());
        businessCard.setWorkingTelephone(dto.getWorkingTelephone());
        businessCard.setUser(userConverter.toEntity(dto.getUser()));
        return businessCard;
    }
}
