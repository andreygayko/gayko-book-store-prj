package com.gayko.bookstore.converters.impl.dto;

import com.gayko.bookstore.converters.DTOConverter;
import com.gayko.bookstore.dao.model.Profile;
import com.gayko.bookstore.dao.model.User;
import com.gayko.bookstore.model.impl.ProfileDTO;
import com.gayko.bookstore.model.impl.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("profileDTOConverter")
public class ProfileDTOConverter implements DTOConverter<Profile, ProfileDTO> {


    @Autowired
    @Qualifier("userDTOConverter") DTOConverter<User, UserDTO> userDTOConverter;

    @Override
    public ProfileDTO toDTO(Profile entity) {

        if (entity == null) {
            return null;
        }
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(entity.getId());
        profileDTO.setAddress(entity.getAddress());
        profileDTO.setPhone(entity.getPhone());
        profileDTO.setUserDTO(userDTOConverter.toDTO(entity.getUser()));
        return profileDTO;
    }
}
