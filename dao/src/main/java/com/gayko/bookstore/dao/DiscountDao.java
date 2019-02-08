package com.gayko.bookstore.dao;

import com.gayko.bookstore.dao.model.Discount;

import java.math.BigDecimal;
import java.util.List;

public interface DiscountDao extends GenericDao<Discount> {

    List<Discount> findByInterestRate(BigDecimal interestRate);

    Discount findById(Long id);
}
