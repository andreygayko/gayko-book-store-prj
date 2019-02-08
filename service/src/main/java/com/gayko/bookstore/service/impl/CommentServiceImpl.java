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
    public void addComment(String comment, Authentication authentication, Long newsId) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Long userId = userPrincipal.getId();
        User user = userDao.findById(userId);
        System.out.println("11111"+user);
        News news = newsDao.findOne(newsId);
        System.out.println("22222"+news);
        Comment saveComment = new Comment();
        saveComment.setUser(user);
        saveComment.setNews(news);
        saveComment.setCreated(LocalDateTime.now());
        saveComment.setContent(comment);
        System.out.println(saveComment);
        commentDao.create(saveComment);
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
        System.out.println("6666666666666"+newsId);
        List<Comment> findComments = commentDao.findByNewsId(1L);
        System.out.println("7777777777"+findComments);
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
