package com.gayko.bookstore.converters.impl.dto;

import com.gayko.bookstore.converters.DTOConverter;
import com.gayko.bookstore.dao.model.News;
import com.gayko.bookstore.dao.model.User;
import com.gayko.bookstore.model.impl.NewsDTO;
import com.gayko.bookstore.model.impl.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("newsDTOConverter")
public class NewsDTOConverter implements DTOConverter<News, NewsDTO> {

    @Autowired
    @Qualifier("userDTOConverter") DTOConverter<User, UserDTO> userDTOConverter;

    @Override
    public NewsDTO toDTO(News entity) {

        if (entity == null) {
            return null;
        }
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setId(entity.getId());
        newsDTO.setTitle(entity.getTitle());
        newsDTO.setContent(entity.getContent());
        newsDTO.setCreated(entity.getCreated());
        newsDTO.setUserDTO(userDTOConverter.toDTO(entity.getUser()));
        return newsDTO;
    }
}
