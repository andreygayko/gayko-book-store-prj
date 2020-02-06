package com.gayko.bookstore.controller;

import com.gayko.bookstore.config.PageProperties;
import com.gayko.bookstore.model.impl.OrderDTO;
import com.gayko.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private PageProperties pageProperties;
    @Autowired
    @Qualifier("orderService")
    private OrderService orderService;

    //Get current user's orders
    @GetMapping("/user")
    @PreAuthorize("hasAuthority('SHOW_ORDERS')")
    public String getCurrentUserOrders(ModelMap modelMap){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<OrderDTO> orders = orderService.getOrders(authentication);
        modelMap.addAttribute("orders", orders);
        return pageProperties.getOrdersPagePath();
    }

    //Get all orders
    @GetMapping
    @PreAuthorize("hasAuthority('SHOW_USER_ORDERS')")
    public String getOrders(ModelMap modelMap){
        List<OrderDTO> orders = orderService.findAll();
        modelMap.addAttribute("orders", orders);
        return pageProperties.getOrdersPagePath();
    }

    //Get orders by user's id
    @GetMapping("/user/{id}")
    @PreAuthorize("hasAuthority('SHOW_USER_ORDERS')")
    public String getUserOrders(ModelMap modelMap, @PathVariable("id") Long id){
        List<OrderDTO> orders = orderService.findUserOrders(id);
        modelMap.addAttribute("orders", orders);
        return pageProperties.getOrdersPagePath();
    }

    @GetMapping("/status")
    @PreAuthorize("hasAuthority('CHANGE_ORDER_STATUS')")
    public String changeStatus (ModelMap modelMap){
        return "sd";
    }
}
