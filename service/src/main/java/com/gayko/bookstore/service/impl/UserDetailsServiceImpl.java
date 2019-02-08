package com.gayko.bookstore.service.impl;

import com.gayko.bookstore.dao.UserDaoNew;
import com.gayko.bookstore.dao.model.User;
import com.gayko.bookstore.model.impl.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDaoNew userDao;

    @Autowired
    public UserDetailsServiceImpl(UserDaoNew userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        User user = userDao.findByEmail(username);
        System.out.println("-----------------------------------------------"+username+user);
        UserPrincipal userPrincipal;
        if (user != null) {
            userPrincipal = new UserPrincipal(user);
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
        return userPrincipal;
}
}
