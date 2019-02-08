package com.gayko.bookstore.dao.impl;

import com.gayko.bookstore.dao.DiscountDao;
import com.gayko.bookstore.dao.model.Discount;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class DiscountDaoImpl extends GenericDaoImpl<Discount> implements DiscountDao {

    public DiscountDaoImpl() {
        super(Discount.class);
    }

    @Override
    public List<Discount> findByInterestRate(BigDecimal interestRate) {
        return (List<Discount>) getCurrentSession().createQuery("from Discount where interestRate=:interestRate")
                .setParameter("interestRate", interestRate)
                .list();
    }


    @Override
    public Discount findById(Long id) {
        return (Discount) getCurrentSession().createQuery("from Discount where id=:id")
                .setParameter("id", id)
                .uniqueResult();
    }
}
