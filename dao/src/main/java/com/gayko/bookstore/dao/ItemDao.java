package com.gayko.bookstore.dao;

import com.gayko.bookstore.dao.model.Item;

import java.util.List;

public interface ItemDao extends GenericDao<Item> {

    Item findById(long id);

    List<Item> findAll();

    List<Item> findItems(int from, int to); //find items in price range from - to

    Long countItems(int from, int to); //count items in price range from - to
}
