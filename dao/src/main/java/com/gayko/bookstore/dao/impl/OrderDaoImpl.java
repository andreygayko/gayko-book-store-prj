package com.gayko.bookstore.dao.impl;

import com.gayko.bookstore.dao.OrderDao;
import com.gayko.bookstore.dao.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {
    public OrderDaoImpl() {
        super(Order.class);
    }

    @Override
    public List<Order> find() {
        return getCurrentSession()
                .createQuery("from Order as o")
                .list();
    }

    @Override
    public List<Order> findByUserId(Long userId) {
        return getCurrentSession()
                .createQuery("from Order as o where o.user.id=:userId", Order.class)
                .setParameter("userId", userId)
                .list();
    }
}
