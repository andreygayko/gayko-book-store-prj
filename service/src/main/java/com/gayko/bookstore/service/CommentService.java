package com.gayko.bookstore.service;

import com.gayko.bookstore.model.impl.CommentDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CommentService {
    void addComment(String content, Long newsId);

    void save(CommentDTO comment);

    void removeById(Long commentId);

    List<CommentDTO> findComments(Long newsId);

    Long countPages(Long quantity);
}
