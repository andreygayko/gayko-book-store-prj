package com.gayko.bookstore.dao;

import com.gayko.bookstore.dao.model.User;

public interface UserDaoNew extends GenericDao<User> {

    User findByEmail(String email);

    User findById(long id);


}
