package com.gayko.bookstore.service;

import com.gayko.bookstore.model.impl.OrderDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface OrderService {

    void create(Long itemId, Authentication authentication);

    void createOrderInPriceRange(Long id, int amt, int min, int max);

    List<OrderDTO> getOrdersInfo();

    List<OrderDTO> getOrders(Authentication authentication);

    public List<OrderDTO> findOrders(Long userId);


    }
