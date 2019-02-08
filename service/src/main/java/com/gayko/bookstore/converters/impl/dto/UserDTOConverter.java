package com.gayko.bookstore.converters.impl.dto;

import com.gayko.bookstore.converters.DTOConverter;
import com.gayko.bookstore.dao.model.Discount;
import com.gayko.bookstore.dao.model.Order;
import com.gayko.bookstore.dao.model.Role;
import com.gayko.bookstore.dao.model.User;
import com.gayko.bookstore.model.impl.DiscountDTO;
import com.gayko.bookstore.model.impl.OrderDTO;
import com.gayko.bookstore.model.impl.RoleDTO;
import com.gayko.bookstore.model.impl.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("userDTOConverter")
public class UserDTOConverter implements DTOConverter<User, UserDTO> {

    private static final Logger logger = LogManager.getLogger(UserDTOConverter.class);

    @Autowired
    @Qualifier("roleDTOConverter")
    private DTOConverter<Role, RoleDTO> roleDTOConverter;
    @Autowired
    @Qualifier("orderDTOConverter")
    private DTOConverter<Order, OrderDTO> orderDTOConverter;
    @Autowired
    @Qualifier("discountDTOConverter")
    private DTOConverter<Discount, DiscountDTO> discountDTOConverter;



    @Override
    public UserDTO toDTO(User entity) {

        if (entity == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(entity.getId());
        userDTO.setName(entity.getName());
        userDTO.setEmail(entity.getEmail());
        userDTO.setSurname(entity.getSurname());
        userDTO.setPassword(entity.getPassword());
        logger.info("getPassword");
        userDTO.setRole(roleDTOConverter.toDTO(entity.getRole()));
        logger.info("getRole");
     //   userDTO.setOrders(orderDTOConverter.toDTOList(entity.getOrders()));
       // userDTO.setDiscount(discountDTOConverter.toDTO(entity.getDiscount()));
        logger.info("getDiscount");
        return userDTO;
    }
}
