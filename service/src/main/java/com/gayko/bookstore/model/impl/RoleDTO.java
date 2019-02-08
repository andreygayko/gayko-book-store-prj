package com.gayko.bookstore.model.impl;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class RoleDTO {

    private Long id;

    private String name;

    private Set<PermissionDTO> permissionsDTO = new HashSet<>();

    public RoleDTO() {
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

    public Set<PermissionDTO> getPermissionsDTO() {
        return permissionsDTO;
    }

    public void setPermissionsDTO(Set<PermissionDTO> permissionsDTO) {
        this.permissionsDTO = permissionsDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDTO roleDTO = (RoleDTO) o;
        return Objects.equals(id, roleDTO.id) &&
                Objects.equals(name, roleDTO.name) &&
                Objects.equals(permissionsDTO, roleDTO.permissionsDTO);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, permissionsDTO);
    }

    @Override
    public String toString() {
        return "RoleDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", permissionsDTO=" + permissionsDTO +
                '}';
    }
}
