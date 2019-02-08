package com.gayko.bookstore.model.impl;

import java.time.LocalDateTime;
import java.util.Objects;

public class NewsDTO {

    private Long id;

    private String title;

    private String content;

    private LocalDateTime created;

    private UserDTO userDTO;

    public NewsDTO() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsDTO newsDTO = (NewsDTO) o;
        return Objects.equals(id, newsDTO.id) &&
                Objects.equals(title, newsDTO.title) &&
                Objects.equals(content, newsDTO.content) &&
                Objects.equals(created, newsDTO.created) &&
                Objects.equals(userDTO, newsDTO.userDTO);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, content, created, userDTO);
    }

    @Override
    public String toString() {
        return "NewsDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", created=" + created +
                ", userDTO=" + userDTO +
                '}';
    }
}
