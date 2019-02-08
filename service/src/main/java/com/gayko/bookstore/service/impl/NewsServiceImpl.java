package com.gayko.bookstore.service.impl;

import com.gayko.bookstore.converters.Converter;
import com.gayko.bookstore.converters.DTOConverter;
import com.gayko.bookstore.dao.NewsDao;
import com.gayko.bookstore.dao.UserDaoNew;
import com.gayko.bookstore.dao.model.News;
import com.gayko.bookstore.dao.model.User;
import com.gayko.bookstore.model.impl.NewsDTO;
import com.gayko.bookstore.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service("newsService")
@Transactional
public class NewsServiceImpl implements NewsService {


    @Autowired
    @Qualifier("userDao")
    private UserDaoNew userDao;
    @Autowired
    @Qualifier("newsDao")
    private NewsDao newsDao;
    @Autowired
    @Qualifier("newsConverter")
    private Converter<NewsDTO, News> newsConverter;
    @Autowired
    @Qualifier("newsDTOConverter")
    private DTOConverter<News, NewsDTO> newsDTOConverter;

    @Override
    public List<NewsDTO> findAll() {
        List<News> news = newsDao.findAll();
        return newsDTOConverter.toDTOList(news);
    }

    @Override
    public void save(NewsDTO news, Long userId) {
        System.out.println("------------------------------"+news);
        News convertNews = newsConverter.toEntity(news);
        convertNews.setCreated(LocalDateTime.now());
        System.out.println("0000000000000000000000000000000"+userId);
        convertNews.setUser(userDao.findById(userId));
        System.out.println("++++++++++++++++++++++++++++++"+convertNews);
        newsDao.create(convertNews);
    }

    @Override
    public void update(String content, Long userId, Long newsId) {
        News findNews = newsDao.findOne(newsId);
        findNews.setContent(content);
        newsDao.update(findNews);
    }

    @Override
    public void update(NewsDTO news) {
        News updateNews = newsConverter.toEntity(news);
        newsDao.update(updateNews);
    }

    @Override
    public void removeById(Long newsId) {
        newsDao.deleteById(newsId);
    }

    @Override
    public NewsDTO findNews(Long newsId) {
        News news = newsDao.findOne(newsId);
        return newsDTOConverter.toDTO(news);
    }

    @Override
    public void addNews(String title, String content, Long userId) {
        User user = new User();
        user.setId(userId);
        News news = new News();
        news.setTitle(title);
        news.setContent(content);
        news.setCreated(LocalDateTime.now());
        news.setUser(user);
        newsDao.create(news);
    }

}
