package com.gayko.bookstore.dao;

import com.gayko.bookstore.dao.model.Role;

public interface RoleDao extends GenericDao<Role> {
    Role getByName(String roleName);

    int deleteAll();

    int unbind(String commonRoleName); // Unbind permission from it's role

    void delete(); // delete roles without permissions

    }
