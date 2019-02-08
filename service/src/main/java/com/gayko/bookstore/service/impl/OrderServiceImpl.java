package com.gayko.bookstore.service.impl;

import com.gayko.bookstore.converters.DTOConverter;
import com.gayko.bookstore.dao.ItemDao;
import com.gayko.bookstore.dao.OrderDao;
import com.gayko.bookstore.dao.UserDaoNew;
import com.gayko.bookstore.dao.model.*;
import com.gayko.bookstore.model.impl.OrderDTO;
import com.gayko.bookstore.model.impl.UserPrincipal;
import com.gayko.bookstore.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);

    private final ItemDao itemDao;
    private final UserDaoNew userDao;
    private final OrderDao orderDao;
@Autowired
@Qualifier("orderDTOConverter")
private DTOConverter<Order, OrderDTO> orderDTOConverter;
    @Autowired
    public OrderServiceImpl(
            UserDaoNew userDao,
            ItemDao itemDao,
            OrderDao orderDao
    ){
        this.userDao = userDao;
        this.itemDao = itemDao;
        this.orderDao = orderDao;
    }

    @Override
    public List<OrderDTO> findOrders(Long userId){
        List<Order> orders = orderDao.findByUserId(userId);
        return orderDTOConverter.toDTOList(orders);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<OrderDTO> getOrders(Authentication authentication){
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Long userId = userPrincipal.getId();
        System.out.println(userId+"12345678909876543234567890987654323456789098765432123456789876543234567899876543234");
        //List<Order> orders =  orderDao.find();
        List<Order> orders = orderDao.findByUserId(userId);
        System.out.println("oooooooooooooooooooooooooooooooooooooo"+orders);
        List<OrderDTO> k = orderDTOConverter.toDTOList(orders);
        System.out.println("ppppppppppppppppp"+k);
        return k;
    }

    @Override
    public void create(Long itemId, Authentication authentication){
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Long userId = userPrincipal.getId();
        Item item = itemDao.findOne(itemId);
        User user = userDao.findOne(userId);
        user.setId(userId);
        Order order = new Order();
        order.setUser(user);
        order.setItem(item);
        order.setId(new UserItemId(item.getId(), user.getId()));
        order.setCreated(LocalDateTime.now());
        order.setStatus(OrderStatusEnum.NEW);
        orderDao.create(order);
    }

    @Override
    public void createOrderInPriceRange(Long id, int amt, int min, int max) {
        User user = userDao.findOne(id);
        List<Item> itemList = itemDao.findItems(min, max);
            Long quantity = itemDao.countItems(min, max);
            Order order = null;
            for (int i = 0; i < amt; i++) {
                Item item = itemList.get(new Random().nextInt(itemList.size()));
                order = new Order();
                order.setCreated(LocalDateTime.now());
                order.setQuantity(Math.toIntExact(quantity));
                order.setId(new UserItemId(item.getId(), user.getId()));
                order.setItem(item);
                order.setUser(user);
                orderDao.create(order);
        }

    }

    @Override
    public List<OrderDTO> getOrdersInfo() {
        Session session = itemDao.getCurrentSession();
          try {
        Transaction transaction = session.getTransaction();
        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        List<Order> orders = orderDao.find();
        for(Order order : orders) {
            logger.info(order.getUser().getName() + " " + order.getUser().getSurname() +
                    "|" + order.getItem().getName() + "|"
                    + order.getQuantity() + "|" + order.getItem().getPrice()
                    + "|" + order.getItem().getPrice().subtract((order.getItem().getPrice().multiply(collectDiscounts(order.getItem().getDiscounts()).add(order.getUser().getDiscount().getInterestRate())).divide(new BigDecimal(100)))));
        }
        transaction.commit();

        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
    }  return Collections.emptyList();

    }

    private BigDecimal collectDiscounts(List<Discount> discounts) {
        BigDecimal totaldiscount = new BigDecimal(0);
        for (Discount d : discounts) {
            totaldiscount = totaldiscount.add(d.getInterestRate());
        }
        return totaldiscount;
    }
}





