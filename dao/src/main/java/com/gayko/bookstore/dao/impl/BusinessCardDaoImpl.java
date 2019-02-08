package com.gayko.bookstore.dao.impl;

import com.gayko.bookstore.dao.BusinessCardDao;
import com.gayko.bookstore.dao.model.BusinessCard;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BusinessCardDaoImpl extends GenericDaoImpl<BusinessCard> implements BusinessCardDao {

    public BusinessCardDaoImpl() {super (BusinessCard.class);}

    @Override
    public List<BusinessCard> findByUserId(Long id) {
        return (List<BusinessCard>) getCurrentSession().createQuery("from BusinessCard as b where b.user.id=:id")
                .setParameter("id", id)
                .list();
    }
}
