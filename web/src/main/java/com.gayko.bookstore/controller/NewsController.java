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
@RequestMapping("/news")
public class NewsController {

    @Autowired
    @Qualifier("newsService")
    private NewsService newsService;
    @Autowired
    private PageProperties pageProperties;
    @Autowired
    @Qualifier("commentService")
    private CommentService commentService;

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_NEWS')")
    public String getNews(ModelMap modelMap) {
        List<NewsDTO> news = newsService.findAll();
        modelMap.addAttribute("news", news);
        return pageProperties.getNewsPagePath();
    }

    @GetMapping("/create")
    @PreAuthorize("hasAuthority('CREATE_NEWS')")
    public String getCreateNewsPage(ModelMap modelMap) {
        modelMap.addAttribute("news", new NewsDTO());
        return pageProperties.getNewsCreatePagePath();
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE_NEWS')")
    public String createNews(
            @ModelAttribute("news") NewsDTO news,
            ModelMap modelMap) {
        modelMap.addAttribute("news", news);
        newsService.save(news);
        return "redirect:/news";
    }

    @GetMapping("/{id}/update")
    @PreAuthorize("hasAuthority('UPDATE_NEWS')")
    public String getUpdateNewsPage(
            @PathVariable("id") Long id,
            ModelMap modelMap
    ) {
        NewsDTO news = newsService.findNews(id);
        modelMap.addAttribute("news", news);
        return pageProperties.getNewsUpdatePagePath();
    }

    @PostMapping("/{id}/update")
    @PreAuthorize("hasAuthority('UPDATE_NEWS')")
    public String updateNews(
            @ModelAttribute("news") NewsDTO news,
            @PathVariable("id") Long id,
            @RequestParam String content,
            ModelMap modelMap) {
        modelMap.addAttribute("news", news);
        newsService.update(news, content);
        return "redirect:/news";
    }

    @GetMapping("/create.comments/{id}")
    @PreAuthorize("hasAuthority('CREATE_COMMENTS')")
    public String getCommentsPage(
            @PathVariable("id") Long id,
            ModelMap modelMap
    ) {
        NewsDTO news = newsService.findNews(id);
        modelMap.addAttribute("news", news);
        return pageProperties.getCommentsCreatePagePath();
    }

    @PostMapping("/create.comments/{id}")
    @PreAuthorize("hasAuthority('CREATE_COMMENTS')")
    public String createComment(
            @PathVariable("id") Long id,
            @RequestParam String content
    ) {
        commentService.addComment(content, id);
        return "redirect:/news/comments/{id}";
    }

    @GetMapping("/comments/{id}")
    @PreAuthorize("hasAuthority('VIEW_COMMENTS')")
    public String getComments(
            ModelMap modelMap,
            @PathVariable("id") Long id
    ) {
        List<CommentDTO> comments = commentService.findComments(id);
        modelMap.addAttribute("comments", comments);
        return pageProperties.getCommentsPagePath();
    }

    @GetMapping("/comments/{id}/delete")
    @PreAuthorize("hasAuthority('DELETE_COMMENTS')")
    public String deleteComment(
            @PathVariable("id") String id
    ) {
        commentService.removeById(Long.valueOf(id));
        return "redirect:/news";
    }

    @GetMapping("/{id}/delete")
    @PreAuthorize("hasAuthority('DELETE_NEWS')")
    public String deleteNews(
            @PathVariable("id") Long id
    ) {
        newsService.removeById(id);
        return "redirect:/news";
    }
}
