package com.gayko.bookstore.converters.impl.entity;

import com.gayko.bookstore.converters.Converter;
import com.gayko.bookstore.dao.model.Profile;
import com.gayko.bookstore.dao.model.User;
import com.gayko.bookstore.model.impl.ProfileDTO;
import com.gayko.bookstore.model.impl.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("profileConverter")
public class ProfileConverter implements Converter<ProfileDTO, Profile> {

    private final Converter<UserDTO,User> userConverter;

    @Autowired
    public ProfileConverter(@Qualifier("userConverter") Converter<UserDTO, User> userConverter) {
        this.userConverter = userConverter;
    }
    @Override
    public Profile toEntity(ProfileDTO dto) {

        if (dto == null) {
            return null;
        }
        Profile profile = new Profile();
        profile.setId(dto.getId());
        profile.setAddress(dto.getAddress());
        profile.setPhone(dto.getPhone());
        profile.setUser(userConverter.toEntity(dto.getUserDTO()));
        return profile;
    }
}
