package com.gayko.bookstore.model.impl;

import java.time.LocalDateTime;
import java.util.Objects;

public class AuditDTO {

    private Long id;

    private String eventType;

    private LocalDateTime created;

    private UserDTO userDTO;

    public AuditDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
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
        AuditDTO auditDTO = (AuditDTO) o;
        return Objects.equals(id, auditDTO.id) &&
                Objects.equals(eventType, auditDTO.eventType) &&
                Objects.equals(created, auditDTO.created) &&
                Objects.equals(userDTO, auditDTO.userDTO);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, eventType, created, userDTO);
    }

    @Override
    public String toString() {
        return "AuditDTO{" +
                "id=" + id +
                ", eventType='" + eventType + '\'' +
                ", created=" + created +
                ", userDTO=" + userDTO +
                '}';
    }
}
