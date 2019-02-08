package com.gayko.bookstore.dao.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_permission")
public class Permission implements Serializable {
    private static final Logger logger = LogManager.getLogger(Permission.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR (30)")
    private String name;

    @ManyToMany
    @JoinTable(name = "t_role_permission",
            joinColumns = @JoinColumn(name = "f_permission_id", foreignKey = @ForeignKey(name="foreign_key_permission_id")),
            inverseJoinColumns = @JoinColumn(name = "f_role_id", foreignKey = @ForeignKey(name="foreign_key_role_id")))
    private Set<Role> roles = new HashSet<>();

    public Permission() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        logger.info("get role");
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void removeAllRoles(){
        roles.clear();
    }


}
