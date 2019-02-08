package com.gayko.bookstore.dao;

import com.gayko.bookstore.dao.model.Comment;

import java.util.List;

public interface CommentDao extends GenericDao<Comment> {

    List<Comment> findByNewsId(Long newsId);

    Long countAllComments();
}
