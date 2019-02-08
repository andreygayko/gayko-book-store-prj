package com.gayko.bookstore.converters.impl.dto;

import com.gayko.bookstore.converters.DTOConverter;
import com.gayko.bookstore.dao.model.Comment;
import com.gayko.bookstore.dao.model.News;
import com.gayko.bookstore.dao.model.User;
import com.gayko.bookstore.model.impl.CommentDTO;
import com.gayko.bookstore.model.impl.NewsDTO;
import com.gayko.bookstore.model.impl.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("commentDTOConverter")
public class CommentDTOConverter implements DTOConverter<Comment, CommentDTO> {

    @Autowired
    @Qualifier("userDTOConverter") DTOConverter<User, UserDTO> userDTOConverter;
    @Autowired
    @Qualifier("newsDTOConverter") DTOConverter<News, NewsDTO> newsDTOConverter;

    @Override
    public CommentDTO toDTO(Comment entity) {

        if(entity == null) {
            return null;
        }
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(entity.getId());
        commentDTO.setContent(entity.getContent());
        commentDTO.setCreated(entity.getCreated());
        commentDTO.setUserDTO(userDTOConverter.toDTO(entity.getUser()));
        commentDTO.setNewsDTO(newsDTOConverter.toDTO(entity.getNews()));
        return commentDTO;
    }
}
