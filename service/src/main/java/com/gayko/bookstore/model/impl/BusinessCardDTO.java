package com.gayko.bookstore.model.impl;

public class BusinessCardDTO {

    private Long id;
    private String title;
    private String fullName;
    private String WorkingTelephone;
    private UserDTO user;

    public BusinessCardDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getWorkingTelephone() {
        return WorkingTelephone;
    }

    public void setWorkingTelephone(String workingTelephone) {
        WorkingTelephone = workingTelephone;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
