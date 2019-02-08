package com.gayko.bookstore.model;

import com.gayko.bookstore.converters.DTOConverter;
import com.gayko.bookstore.dao.model.User;
import com.gayko.bookstore.model.impl.UserDTO;

public interface IUserDTO extends DTOConverter<User, UserDTO> {
}
