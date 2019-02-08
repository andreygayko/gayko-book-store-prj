package com.gayko.bookstore.service.impl;/*
package com.gayko.bookstore.service.impl;

import com.gayko.bookstore.converters.impl.entity.DiscountConverter;
import com.gayko.bookstore.converters.impl.dto.DiscountDTOConverter;
import com.gayko.bookstore.dao.DiscountDao;
import com.gayko.bookstore.dao.ItemDao;
import com.gayko.bookstore.dao.impl.ItemDaoImpl;
import com.gayko.bookstore.model.impl.DiscountDTO;
import com.gayko.bookstore.service.DiscountService;
import com.gayko.bookstore.dao.impl.DiscountDaoImpl;
import com.gayko.bookstore.dao.model.Discount;
import org.gayko.bookstore.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.gayko.bookstore.dao.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class DiscountServiceImpl implements DiscountService {
    private static final Logger logger = LogManager.getLogger(DiscountServiceImpl.class);

    private final DiscountDao discountDao;
    private final ItemDao itemDao;
    private final DiscountConverter discountConverter;
    private final DiscountDTOConverter discountDTOConverter;

    @Autowired
    public DiscountServiceImpl(
            ItemDao itemDao,
            DiscountDao discountDao,
            DiscountConverter discountConverter,
            DiscountDTOConverter discountDTOConverter
    ){
        this.discountDao = discountDao;
        this.itemDao = itemDao;
        this.discountConverter = discountConverter;
        this.discountDTOConverter = discountDTOConverter;
    }

    @Override
    public List<DiscountDTO> save(List<DiscountDTO> discountList) {
        Session session = discountDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Discount> savedItems = new ArrayList<>();
            for (DiscountDTO discountDTO : discountList) {
                Discount discount = discountConverter.toEntity(discountDTO);
                discountDao.create(discount);
                savedItems.add(discount);
            }
            List<DiscountDTO> listDiscountDTO = discountDTOConverter.toDTOList(savedItems);
            transaction.commit();

            return listDiscountDTO;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save discount", e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<DiscountDTO> getByInterestRate(BigDecimal percent) {
        Session session = discountDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Discount> discounts = discountDao.findByInterestRate(percent);
            List<DiscountDTO> discountsDTO = discountDTOConverter.toDTOList(discounts);
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to find discount", e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<DiscountDTO> findAllItems() {
        return Collections.emptyList();
    }

    @Override
    public void discountAssignment(int min, int max, BigDecimal percent) { //Assign discount to items in price range from min - to max
        Session session = discountDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Item> items = itemDao.findItems(min, max);
            List<Discount> discounts = discountDao.findByInterestRate(percent);
            List<Discount> discountList = new ArrayList<>(discounts);
            for (Item item : items) {
                item.setDiscounts(discountList);
            }
            List<Item> itemList = new ArrayList<>(items);
            for (Discount discount : discounts) {
                discount.setItems(itemList);
            }
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to assign discount", e);
        }
    }
}*/
