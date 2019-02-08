package com.gayko.bookstore.dao.impl;

import com.gayko.bookstore.dao.UserDaoNew;
import com.gayko.bookstore.dao.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImplNew extends GenericDaoImpl<User> implements UserDaoNew {

    private static final Logger logger = LogManager.getLogger(UserDaoImplNew.class);

    public UserDaoImplNew() {
        super(User.class);
    }

    //    @Override
//    public List<com.gayko.bookstore.newmodel.User> findAll() {
//        String hql = "from User";
//        Query query = getCurrentSession().createQuery(hql);
//        return query.list();
//    }


    public User findByEmail(String email){
        return (User) getCurrentSession().createQuery("from User where email=:email")
                .setParameter("email", email)
                .uniqueResult();
    }

    public User findById(long id){
        return (User) getCurrentSession().createQuery("from User where id=:id")
                .setParameter("id", id)
                .uniqueResult();

    }
}
