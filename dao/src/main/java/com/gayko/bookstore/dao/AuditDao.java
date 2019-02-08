package com.gayko.bookstore.dao;

import com.gayko.bookstore.dao.model.Audit;

import java.util.List;


public interface AuditDao extends GenericDao<Audit> {

    List<Audit> findByEvent(String eventType);
}
