package com.gayko.bookstore.converters.impl.dto;

import com.gayko.bookstore.converters.DTOConverter;
import com.gayko.bookstore.dao.model.Permission;
import com.gayko.bookstore.dao.model.Role;
import com.gayko.bookstore.model.impl.PermissionDTO;
import com.gayko.bookstore.model.impl.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("permissionDTOConverter")
public class PermissionDTOConverter implements DTOConverter<Permission, PermissionDTO> {


    @Autowired
    @Qualifier("roleDTOConverter") DTOConverter<Role, RoleDTO> roleDTOConverter;

    @Override
    public PermissionDTO toDTO(Permission entity) {

        if (entity == null) {
            return null;
        }
        PermissionDTO permissionDTO = new PermissionDTO();
        permissionDTO.setId(entity.getId());
        permissionDTO.setName(entity.getName());
        //permissionDTO.setRolesDTO(roleDTOConverter.toDTOSet(entity.getRoles()));
        return permissionDTO;
    }
}
