package com.gayko.bookstore.converters.impl.entity;

import com.gayko.bookstore.converters.Converter;
import com.gayko.bookstore.dao.model.Comment;
import com.gayko.bookstore.dao.model.News;
import com.gayko.bookstore.dao.model.User;
import com.gayko.bookstore.model.impl.CommentDTO;
import com.gayko.bookstore.model.impl.NewsDTO;
import com.gayko.bookstore.model.impl.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("commentConverter")
public class CommentConverter implements Converter<CommentDTO, Comment> {

    @Autowired
    @Qualifier("userConverter") Converter<UserDTO, User> userConverter;
    @Autowired
    @Qualifier("newsConverter") Converter<NewsDTO, News> newsConverter;

    @Override
    public Comment toEntity(CommentDTO commentDTO) {

        if (commentDTO == null) {
            return null;
        }
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setContent(commentDTO.getContent());
        comment.setCreated(commentDTO.getCreated());
        comment.setUser(userConverter.toEntity(commentDTO.getUserDTO()));
        comment.setNews(newsConverter.toEntity(commentDTO.getNewsDTO()));
        return comment;
    }
}
