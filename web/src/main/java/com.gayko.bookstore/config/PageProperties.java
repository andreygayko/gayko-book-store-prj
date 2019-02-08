
package com.gayko.bookstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@Configuration("pageProperties")
public class PageProperties {

    @Autowired
    private Environment environment;

    private String errorsPagePath;
    private String loginPagePath;
    private String userPagePath;
    private String updateUserPagePath;
    private String createUserPagePath;
    private String userRolePagePath;
    private String businessCardPagePath;
    private String createBusinessCardPagePath;
    private String itemsPagePath;
    private String itemsUploadPagePath;
    private String itemsFilePath;
    private String userPasswordPagePath;
    private String createItemPagePath;
    private String newsPagePath;
    private String commentsCreatePagePath;
    private String newsUpdatePagePath;
    private String newsCreatePagePath;
    private String commentsPagePath;
    private String ordersPagePath;

    @PostConstruct
    public void initialize() {
        this.errorsPagePath = environment.getProperty("errors.page.path");
        this.loginPagePath = environment.getProperty("login.page.path");
        this.userPagePath = environment.getProperty("users.page.path");
        this.updateUserPagePath = environment.getProperty("update.users.page.path");
        this.createUserPagePath = environment.getProperty("create.users.page.path");
        this.userRolePagePath = environment.getProperty("user.role.page.path");
        this.businessCardPagePath = environment.getProperty("business.card.page.path");
        this.itemsPagePath = environment.getProperty("items.page.path");
        this.createBusinessCardPagePath = environment.getProperty("business.card.create.page.path");
        this.itemsUploadPagePath = environment.getProperty("items.upload.page.path");
        this.itemsFilePath = environment.getProperty("items.file.path");
        this.userPasswordPagePath = environment.getProperty("user.password.page.path");
        this.createItemPagePath = environment.getProperty("create.item.page.path");
        this.newsPagePath = environment.getProperty("news.page.path");
        this.commentsCreatePagePath = environment.getProperty("comments.create.page.path");
        this.newsUpdatePagePath = environment.getProperty("news.update.page.path");
        this.newsCreatePagePath = environment.getProperty("news.create.page.path");
        this.commentsPagePath = environment.getProperty("comments.page.path");
        this.ordersPagePath = environment.getProperty("orders.page.path");
    }

    public String getUpdateUserPagePath() {
        return updateUserPagePath;
    }

    public String getCreateUserPagePath() {
        return createUserPagePath;
    }

    public String getErrorsPagePath() {
        return errorsPagePath;
    }

    public String getLoginPagePath() {
        return loginPagePath;
    }

    public String getUserPagePath() {
        return userPagePath;
    }

    public String getBusinessCardPagePath() {
        return businessCardPagePath;
    }

    public String getCreateBusinessCardPagePath() {
        return createBusinessCardPagePath;
    }

    public String getItemsPagePath() {
        return itemsPagePath;
    }

    public String getItemsUploadPagePath() {
        return itemsUploadPagePath;
    }

    public String getUserRolePagePath() {
        return userRolePagePath;
    }

    public String getItemsFilePath() {
        return itemsFilePath;
    }

    public String getUserPasswordPagePath() {
        return userPasswordPagePath;
    }

    public String getCreateItemPagePath() {
        return createItemPagePath;
    }

    public String getNewsPagePath() {
        return newsPagePath;
    }

    public String getCommentsCreatePagePath() {
        return commentsCreatePagePath;
    }

    public String getNewsUpdatePagePath() {
        return newsUpdatePagePath;
    }

    public String getNewsCreatePagePath() {
        return newsCreatePagePath;
    }

    public String getCommentsPagePath() {
        return commentsPagePath;
    }

    public String getOrdersPagePath() {
        return ordersPagePath;
    }
}