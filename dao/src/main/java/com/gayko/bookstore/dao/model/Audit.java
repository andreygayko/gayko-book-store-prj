package com.gayko.bookstore.dao.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_audit")
public class Audit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @Column(name = "event_type", columnDefinition = "VARCHAR (50)")
    private String eventType;

    @Column(name = "date_created")
    private LocalDateTime created;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "f_user_id", foreignKey = @ForeignKey(name="news_id"), nullable = false)
    private User user;

    public Audit() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Audit audit = (Audit) o;

        if (id != null ? !id.equals(audit.id) : audit.id != null) return false;
        if (eventType != null ? !eventType.equals(audit.eventType) : audit.eventType != null) return false;
        if (created != null ? !created.equals(audit.created) : audit.created != null) return false;
        return user != null ? user.equals(audit.user) : audit.user == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (eventType != null ? eventType.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Audit{" +
                "id=" + id +
                ", eventType='" + eventType + '\'' +
                ", created=" + created +
                ", user=" + user +
                '}';
    }
}
