package com.gayko.bookstore.dao;

import com.gayko.bookstore.dao.model.News;

import java.util.List;

public interface NewsDao extends GenericDao<News> {

    List<News> findByUserId(long id);
}
