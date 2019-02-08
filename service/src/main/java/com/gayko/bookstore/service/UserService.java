package com.gayko.bookstore.service;

import com.gayko.bookstore.model.impl.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO findById(Long id);

    UserDTO create(UserDTO user);

    UserDTO update(UserDTO userDTO);

    void delete(Long id);

    UserDTO findUserByEmail(String email);

    List<UserDTO> findAll();

    void assignDiscountToUser();

    void enable(Long id);

    void disable(Long id);

    void changeRole(Long roleId, Long userId);

    void updatePassword(String password, Long userId);
    }
