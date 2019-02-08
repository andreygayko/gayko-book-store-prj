package com.gayko.bookstore.controller;

import com.gayko.bookstore.config.PageProperties;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class AppExceptionHandler {
    private final PageProperties pageProperties;

    @Autowired
    public AppExceptionHandler(PageProperties pageProperties) {
        this.pageProperties = pageProperties;
    }

    @ExceptionHandler(HibernateException.class)
    public String defaultHibernateExceptionHandler(HttpServletRequest req, Exception e){
        req.setAttribute("exception", e);
        req.setAttribute("url", req.getRequestURL());
        return pageProperties.getErrorsPagePath();
    }
}
