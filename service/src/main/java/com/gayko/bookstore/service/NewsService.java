package com.gayko.bookstore.service;

import com.gayko.bookstore.model.impl.NewsDTO;

import java.util.List;

public interface NewsService {

    List<NewsDTO> findAll();

    //Long countPages(Long quantity);

    void save(NewsDTO news, Long userId);

    void update(String content, Long userId, Long newsId);

    void update(NewsDTO news);

    void removeById(Long newsId);

    NewsDTO findNews(Long newsId);

    void addNews(String title, String content, Long userId);
}
