package com.gayko.bookstore.dao.impl;

import com.gayko.bookstore.dao.ProfileDao;
import com.gayko.bookstore.dao.model.Profile;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileDaoImpl extends GenericDaoImpl<Profile> implements ProfileDao {

    public ProfileDaoImpl() {
        super(Profile.class);
    }
}
