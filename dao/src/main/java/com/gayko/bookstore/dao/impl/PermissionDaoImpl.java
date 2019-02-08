package com.gayko.bookstore.dao.impl;

import com.gayko.bookstore.dao.PermissionDao;
import com.gayko.bookstore.dao.model.Permission;
import org.springframework.stereotype.Repository;

@Repository
public class PermissionDaoImpl extends GenericDaoImpl<Permission> implements PermissionDao {

    public PermissionDaoImpl() {
        super(Permission.class);
    }

    @Override
    public Permission findByName(String permissionName){
        return (Permission) getCurrentSession().createQuery("from Permission as p where p.name=:permissionname")
                .setParameter("permissionname", permissionName)
                .uniqueResult();
    }
}
