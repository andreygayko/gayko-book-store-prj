package com.gayko.bookstore.controller;

import com.gayko.bookstore.config.PageProperties;
import com.gayko.bookstore.model.impl.CommentDTO;
import com.gayko.bookstore.model.impl.NewsDTO;
import com.gayko.bookstore.model.impl.UserDTO;
import com.gayko.bookstore.model.impl.UserPrincipal;
import com.gayko.bookstore.service.CommentService;
import com.gayko.bookstore.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class NewsController {

    @Autowired
    @Qualifier("newsService")
    private NewsService newsService;
    @Autowired
    private PageProperties pageProperties;
    @Autowired
    @Qualifier("commentService")
    private CommentService commentService;

    @GetMapping("/news")
    @PreAuthorize("hasAuthority('VIEW_NEWS')")
    public String getNews(ModelMap modelMap){
        List<NewsDTO> news = newsService.findAll();
        modelMap.addAttribute("news", news);
        return pageProperties.getNewsPagePath();
    }

    @GetMapping("/news/create")
    @PreAuthorize("hasAuthority('CREATE_NEWS')")
    public String getCreateNewsPage(ModelMap modelMap) {
        modelMap.addAttribute("news", new NewsDTO());
        return pageProperties.getNewsCreatePagePath();
    }

    @PostMapping("/news/create")
    @PreAuthorize("hasAuthority('CREATE_NEWS')")
    public String createNews(
            @ModelAttribute("news") NewsDTO news,
            ModelMap modelMap) {
        modelMap.addAttribute("news", news);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Long userId = userPrincipal.getId();
        newsService.save(news, userId);
        return pageProperties.getNewsPagePath();
    }

    @GetMapping("/news/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE_NEWS')")
    public String getUpdateNewsPage(
            @PathVariable("id") Long id,
            ModelMap modelMap
    ) {
        NewsDTO news = newsService.findNews(id);
        modelMap.addAttribute("news", news);
        return pageProperties.getNewsUpdatePagePath();
    }

    @PostMapping("/news/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE_NEWS')")
    public String updateNews(
            @ModelAttribute("news") NewsDTO news,
            @PathVariable("id") Long id,
            @RequestParam String content,
            ModelMap modelMap) {
        modelMap.addAttribute("news", news);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Long userId = userPrincipal.getId();
        news.setContent(content);
        news.setTitle(news.getTitle());
        news.setCreated(LocalDateTime.now());
        newsService.update(content, userId, id);
        return "redirect:/news/update/{id}";
    }

    @GetMapping("/news/create.comments/{id}")
    @PreAuthorize("hasAuthority('CREATE_COMMENTS')")
    public String getCommentsPage(
            @PathVariable("id") Long id,
            ModelMap modelMap
    ) {
        NewsDTO news = newsService.findNews(id);
        modelMap.addAttribute("news", news);
        return pageProperties.getCommentsCreatePagePath();
    }

    @PostMapping("/news/create.comments/{id}")
    @PreAuthorize("hasAuthority('CREATE_COMMENTS')")
    public String createComment(
            @PathVariable("id") Long id,
            @RequestParam String content
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        commentService.addComment(content, authentication, id);
        return "redirect:/news/create.comments/{id}";
    }

    @GetMapping("/news/comments/{id}")
    @PreAuthorize("hasAuthority('VIEW_COMMENTS')")
    public String getComments(
            ModelMap modelMap,
            @PathVariable("id") Long id
    ) {
        List<CommentDTO> comments = commentService.findComments(id);
        modelMap.addAttribute("comments", comments);
        return pageProperties.getCommentsPagePath();
    }

    @GetMapping("/news.read.comments/{id}/delete")
    @PreAuthorize("hasAuthority('DELETE_COMMENTS')")
    public String deleteComment(
            @PathVariable("id") String id
    ) {
        commentService.removeById(Long.valueOf(id));
        return "redirect:/news/news.read.comments/{id}";
    }

    @GetMapping(value = "/{id}/delete")
    @PreAuthorize("hasAuthority('DELETE_NEWS')")
    public String deleteNews(
            @PathVariable("id") String id
    ) {
        newsService.removeById(Long.valueOf(id));
        return "redirect:/news";
    }
}
