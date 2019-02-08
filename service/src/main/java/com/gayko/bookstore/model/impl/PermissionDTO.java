package com.gayko.bookstore.model.impl;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class PermissionDTO {

    private Long id;

    private String name;

    private Set<RoleDTO> rolesDTO = new HashSet<>();

    public PermissionDTO() {
    }

    public PermissionDTO(Long id, String name, Set<RoleDTO> rolesDTO) {
        this.id = id;
        this.name = name;
        this.rolesDTO = rolesDTO;
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

    public Set<RoleDTO> getRolesDTO() {
        return rolesDTO;
    }

    public void setRolesDTO(Set<RoleDTO> rolesDTO) {
        this.rolesDTO = rolesDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissionDTO that = (PermissionDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(rolesDTO, that.rolesDTO);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, rolesDTO);
    }

    @Override
    public String toString() {
        return "PermissionDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rolesDTO=" + rolesDTO +
                '}';
    }
}
