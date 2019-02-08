package com.gayko.bookstore.converters.impl.entity;

import com.gayko.bookstore.converters.Converter;
import com.gayko.bookstore.dao.model.News;
import com.gayko.bookstore.dao.model.User;
import com.gayko.bookstore.model.impl.NewsDTO;
import com.gayko.bookstore.model.impl.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("newsConverter")
public class NewsConverter implements Converter<NewsDTO, News> {

    private final Converter<UserDTO,User> userConverter;

    @Autowired
    public NewsConverter(@Qualifier("userConverter") Converter<UserDTO, User> userConverter) {
        this.userConverter = userConverter;
    }

    @Override
    public News toEntity(NewsDTO dto) {

        if (dto == null) {
            return null;
        }
        News news = new News();
        news.setId(dto.getId());
        news.setTitle(dto.getTitle());
        news.setContent(dto.getContent());
        news.setCreated(dto.getCreated());
        news.setUser(userConverter.toEntity(dto.getUserDTO()));
        return news;
    }
}
