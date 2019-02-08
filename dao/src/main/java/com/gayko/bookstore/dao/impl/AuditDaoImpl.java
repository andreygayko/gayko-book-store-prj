package com.gayko.bookstore.dao.impl;

import com.gayko.bookstore.dao.AuditDao;
import com.gayko.bookstore.dao.model.Audit;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuditDaoImpl extends GenericDaoImpl<Audit> implements AuditDao {
    public AuditDaoImpl(){super(Audit.class);}

    @Override
    public List<Audit> findByEvent(String eventType) {
        return (List<Audit>) getCurrentSession().createQuery("from Audit as a where a.eventType=:eventtype")
                .setParameter("eventtype", eventType)
                .list();
    }

}
