package com.gayko.bookstore.dao;

import com.gayko.bookstore.dao.model.Order;

import java.util.List;

public interface OrderDao extends GenericDao<Order> {

    List<Order> find();

    List<Order> findByUserId(Long userId);
}
