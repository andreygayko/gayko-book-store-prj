package com.gayko.bookstore.dao.impl;

import com.gayko.bookstore.dao.NewsDao;
import com.gayko.bookstore.dao.model.News;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("newsDao")
public class NewsDaoImpl extends GenericDaoImpl<News> implements NewsDao {
    public NewsDaoImpl() { super(News.class); }

    @Override
    public List<News> findByUserId(long userId){
    return getCurrentSession().createQuery("from News as n where n.user.id=:id")
            .setParameter("id", userId)
            .list();
}
    /*@Override
    public List<News> findAllNews(long userId){
        return getCurrentSession().createQuery("from News as n order by n.created desc")
                .setParameter("id", userId)
                .list();*/

}
