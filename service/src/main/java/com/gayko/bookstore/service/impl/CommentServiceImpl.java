package com.gayko.bookstore.service.impl;

import com.gayko.bookstore.converters.Converter;
import com.gayko.bookstore.converters.DTOConverter;
import com.gayko.bookstore.dao.CommentDao;
import com.gayko.bookstore.dao.NewsDao;
import com.gayko.bookstore.dao.UserDaoNew;
import com.gayko.bookstore.dao.model.Comment;
import com.gayko.bookstore.dao.model.News;
import com.gayko.bookstore.dao.model.User;
import com.gayko.bookstore.model.impl.CommentDTO;
import com.gayko.bookstore.model.impl.UserPrincipal;
import com.gayko.bookstore.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service("commentService")
@Transactional
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    @Qualifier("commentConverter")
    private Converter<CommentDTO, Comment> commentConverter;
    @Autowired
    @Qualifier("commentDTOConverter")
    private DTOConverter<Comment, CommentDTO> commentDTOConverter;
    @Autowired
    @Qualifier("userDao")
    private UserDaoNew userDao;
    @Autowired
    @Qualifier("newsDao")
    private NewsDao newsDao;

    @Override
    public void addComment(String content, Long newsId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Long userId = userPrincipal.getId();
        User user = userDao.findById(userId);
        News news = newsDao.findOne(newsId);
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setNews(news);
        comment.setCreated(LocalDateTime.now());
        comment.setContent(content);
        commentDao.create(comment);
    }

    @Override
    public void save(CommentDTO comment) {
        Comment saveComment = commentConverter.toEntity(comment);
        commentDao.create(saveComment);
    }

    @Override
    public void removeById(Long commentId) {
        commentDao.deleteById(commentId);
    }

    @Override
    public List<CommentDTO> findComments(Long newsId) {
        List<Comment> findComments = commentDao.findByNewsId(newsId);
        return commentDTOConverter.toDTOList(findComments);
    }

    @Override
    public Long countPages(Long quantity) {
        Long count = commentDao.countAllComments();
        if (count % quantity != 0) {
            count = count / quantity + 1;
        } else {
            count = count / quantity;
        }
        return count;
    }
}
