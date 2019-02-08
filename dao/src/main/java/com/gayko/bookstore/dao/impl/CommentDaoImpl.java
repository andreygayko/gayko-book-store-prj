package com.gayko.bookstore.dao.impl;

import com.gayko.bookstore.dao.CommentDao;
import com.gayko.bookstore.dao.model.Comment;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDaoImpl extends GenericDaoImpl<Comment> implements CommentDao {

    public CommentDaoImpl() { super (Comment.class);}

    @Override
    public List<Comment> findByNewsId(Long newsId) {
        return (List<Comment>) getCurrentSession().createQuery("from Comment as c where c.news.id=:newsId order by c.created desc")
                .setParameter("newsId", newsId)
                .list();
    }

    @Override
    public Long countAllComments() {
        String hql = "select count(*) from Comment as c";
        Query query = getCurrentSession().createQuery(hql);
        return (Long) query.uniqueResult();
    }
    }

