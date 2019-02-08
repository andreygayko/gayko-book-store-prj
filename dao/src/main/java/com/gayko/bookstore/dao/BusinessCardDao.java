package com.gayko.bookstore.dao;

import com.gayko.bookstore.dao.model.BusinessCard;

import java.util.List;

public interface BusinessCardDao extends GenericDao<BusinessCard> {

    List<BusinessCard> findByUserId(Long id);
}
