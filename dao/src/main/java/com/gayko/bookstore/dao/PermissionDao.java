package com.gayko.bookstore.dao;

import com.gayko.bookstore.dao.model.Permission;

public interface PermissionDao extends GenericDao<Permission> {

    Permission findByName(String permissionName);
}
