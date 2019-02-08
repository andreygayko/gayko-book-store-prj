package com.gayko.bookstore.converters.impl.entity;

import com.gayko.bookstore.converters.Converter;
import com.gayko.bookstore.dao.model.Permission;
import com.gayko.bookstore.dao.model.Role;
import com.gayko.bookstore.model.impl.PermissionDTO;
import com.gayko.bookstore.model.impl.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("permissionConverter")
public class PermissionConverter implements Converter<PermissionDTO, Permission> {

    private final Converter<RoleDTO, Role> roleConverter;

    @Autowired
    public PermissionConverter(@Qualifier("roleConverter") Converter<RoleDTO, Role> roleConverter) {
        this.roleConverter = roleConverter;
    }

    @Override
    public Permission toEntity(PermissionDTO dto) {

        if (dto == null) {
            return null;
        }
        Permission permission = new Permission();
        permission.setId(dto.getId());
        permission.setName(dto.getName());
        permission.setRoles(roleConverter.toEntitySet(dto.getRolesDTO()));
        return permission;
    }
}
