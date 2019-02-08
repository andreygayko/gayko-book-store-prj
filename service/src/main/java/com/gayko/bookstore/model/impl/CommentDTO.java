package com.gayko.bookstore.model.impl;


import java.time.LocalDateTime;
import java.util.Objects;

public class CommentDTO {
    private Long id;

    private String content;

    private LocalDateTime created;

    private UserDTO userDTO;

    private NewsDTO newsDTO;

    public CommentDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public NewsDTO getNewsDTO() {
        return newsDTO;
    }

    public void setNewsDTO(NewsDTO newsDTO) {
        this.newsDTO = newsDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentDTO that = (CommentDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(content, that.content) &&
                Objects.equals(created, that.created) &&
                Objects.equals(userDTO, that.userDTO) &&
                Objects.equals(newsDTO, that.newsDTO);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, content, created, userDTO, newsDTO);
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", created=" + created +
                ", userDTO=" + userDTO +
                ", newsDTO=" + newsDTO +
                '}';
    }
}
