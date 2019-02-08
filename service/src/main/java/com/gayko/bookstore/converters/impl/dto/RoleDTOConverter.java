package com.gayko.bookstore.converters.impl.dto;

import com.gayko.bookstore.converters.DTOConverter;
import com.gayko.bookstore.dao.model.Permission;
import com.gayko.bookstore.dao.model.Role;
import com.gayko.bookstore.model.impl.PermissionDTO;
import com.gayko.bookstore.model.impl.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("roleDTOConverter")
public class RoleDTOConverter implements DTOConverter<Role, RoleDTO> {

    @Autowired
@Qualifier("permissionDTOConverter")
    private DTOConverter<Permission, PermissionDTO> permissionDTOConverter;


    @Override
    public RoleDTO toDTO(Role entity){

        if (entity==null){
            return null;
        }
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(entity.getId());
        roleDTO.setName(entity.getName());
        roleDTO.setPermissionsDTO(permissionDTOConverter.toDTOSet(entity.getPermissions()));
        return roleDTO;
    }
}
