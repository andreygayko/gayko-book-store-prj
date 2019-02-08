package com.gayko.bookstore.converters.impl.dto;

import com.gayko.bookstore.converters.DTOConverter;
import com.gayko.bookstore.dao.model.BusinessCard;
import com.gayko.bookstore.dao.model.User;
import com.gayko.bookstore.model.impl.BusinessCardDTO;
import com.gayko.bookstore.model.impl.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("businessCardDTOConverter")
public class BusinessCardDTOConverter implements DTOConverter<BusinessCard, BusinessCardDTO> {

    @Autowired
    @Qualifier("userDTOConverter") DTOConverter<User, UserDTO> userDTOConverter;

    @Override
    public BusinessCardDTO toDTO(BusinessCard entity) {

        if (entity == null) {
            return null;
        }
        BusinessCardDTO businessCardDTO = new BusinessCardDTO();
        businessCardDTO.setId(entity.getId());
        businessCardDTO.setTitle(entity.getTitle());
        businessCardDTO.setFullName(entity.getFullName());
        businessCardDTO.setWorkingTelephone(entity.getWorkingTelephone());
        businessCardDTO.setUser(userDTOConverter.toDTO(entity.getUser()));
        return businessCardDTO;
    }
}
