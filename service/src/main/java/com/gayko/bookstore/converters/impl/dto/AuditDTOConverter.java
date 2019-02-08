package com.gayko.bookstore.converters.impl.dto;

import com.gayko.bookstore.converters.DTOConverter;
import com.gayko.bookstore.dao.model.Audit;
import com.gayko.bookstore.dao.model.User;
import com.gayko.bookstore.model.impl.AuditDTO;
import com.gayko.bookstore.model.impl.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("auditDTOConverter")
public class AuditDTOConverter implements DTOConverter<Audit, AuditDTO> {

    @Autowired
    @Qualifier("userDTOConverter") DTOConverter<User, UserDTO> userDTOConverter;


    @Override
    public  AuditDTO toDTO(Audit entity) {

        if (entity == null) {
            return null;
        }
        AuditDTO auditDTO = new AuditDTO();
        auditDTO.setId(entity.getId());
        auditDTO.setEventType(entity.getEventType());
        auditDTO.setCreated(entity.getCreated());
        auditDTO.setUserDTO(userDTOConverter.toDTO(entity.getUser()));
        return auditDTO;
    }
}
