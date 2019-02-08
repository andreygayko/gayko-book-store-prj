package com.gayko.bookstore.dao.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_business_card")
public class BusinessCard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "working_telephone")
    private String workingTelephone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "f_user_id", foreignKey = @ForeignKey(name="f_user_id_card"), nullable = false)
    private User user;

    public BusinessCard() {
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
        return workingTelephone;
    }

    public void setWorkingTelephone(String workingTelephone) {
        this.workingTelephone = workingTelephone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
