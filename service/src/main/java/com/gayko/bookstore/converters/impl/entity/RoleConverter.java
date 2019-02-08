package com.gayko.bookstore.converters.impl.entity;

import com.gayko.bookstore.converters.Converter;
import com.gayko.bookstore.dao.model.Permission;
import com.gayko.bookstore.dao.model.Role;
import com.gayko.bookstore.model.impl.PermissionDTO;
import com.gayko.bookstore.model.impl.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("roleConverter")
public class RoleConverter implements Converter<RoleDTO, Role> {


    @Autowired
    @Qualifier("permissionConverter") Converter<PermissionDTO, Permission> permissionConverter;

    @Override
    public Role toEntity(RoleDTO dto) {

        if (dto == null) {
            return null;
        }
        Role role = new Role();
        role.setId(dto.getId());
        role.setName(dto.getName());
        role.setPermissions(permissionConverter.toEntitySet(dto.getPermissionsDTO()));
        return role;
    }
}
