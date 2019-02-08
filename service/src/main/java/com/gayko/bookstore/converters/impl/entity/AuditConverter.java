package com.gayko.bookstore.converters.impl.entity;

import com.gayko.bookstore.converters.Converter;
import com.gayko.bookstore.dao.model.Audit;
import com.gayko.bookstore.dao.model.User;
import com.gayko.bookstore.model.impl.AuditDTO;
import com.gayko.bookstore.model.impl.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("auditConverter")
public class AuditConverter implements Converter<AuditDTO, Audit> {

    @Autowired
    @Qualifier("userConverter") Converter<UserDTO, User> userConverter;

    @Override
    public Audit toEntity(AuditDTO dto) {


        if (dto == null) {
            return null;
        }
        Audit audit = new Audit();
        audit.setId(dto.getId());
        audit.setEventType(dto.getEventType());
        audit.setCreated(dto.getCreated());
        audit.setUser(userConverter.toEntity(dto.getUserDTO()));
        return audit;
    }
}
