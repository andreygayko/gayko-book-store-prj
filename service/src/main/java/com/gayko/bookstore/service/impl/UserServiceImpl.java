package com.gayko.bookstore.service.impl;

import com.gayko.bookstore.converters.Converter;
import com.gayko.bookstore.converters.DTOConverter;
import com.gayko.bookstore.dao.DiscountDao;
import com.gayko.bookstore.dao.RoleDao;
import com.gayko.bookstore.dao.UserDaoNew;
import com.gayko.bookstore.dao.impl.DiscountDaoImpl;
import com.gayko.bookstore.dao.model.Discount;
import com.gayko.bookstore.dao.model.Role;
import com.gayko.bookstore.dao.model.User;
import com.gayko.bookstore.model.impl.UserDTO;
import com.gayko.bookstore.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final BCryptPasswordEncoder passwordEncoder;
    private final DTOConverter<User, UserDTO> userDTOConverter;
    private final UserDaoNew userDao;
    private final Converter<UserDTO, User> userConverter;
    private final RoleDao roleDao;

    @Autowired
    public UserServiceImpl(@Qualifier("userConverter") Converter userConverter, @Qualifier("userDTOConverter") DTOConverter userDTOConverter, UserDaoNew userDao, BCryptPasswordEncoder passwordEncoder, RoleDao roleDao) {
        this.userConverter = userConverter;
        this.userDTOConverter = userDTOConverter;
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.roleDao = roleDao;
    }

    @Override
    public UserDTO findById(Long id) {
        User user = userDao.findOne(id);
        return userDTOConverter.toDTO(user);
    }

    @Override
    public UserDTO create(UserDTO user) {
        User createUser = userConverter.toEntity(user);
        createUser.setEnable(true);
        createUser.setRole(roleDao.findOne(3L)); //Customer default
        createUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.create(createUser);
        return userDTOConverter.toDTO(createUser);
    }

    @Override
    public UserDTO update(UserDTO user) {
        User updateUser = userDao.findOne(user.getId());
        updateUser.setName(user.getName());
        updateUser.setSurname(user.getSurname());
        updateUser.setEmail(user.getEmail());
        updateUser.setPassword(user.getPassword());
        userDao.update(updateUser);
        return userDTOConverter.toDTO(updateUser);
    }

    @Override
    public void updatePassword(String password, Long userId) {
        User updateUser = userDao.findOne(userId);
        updateUser.setPassword(passwordEncoder.encode(password));
        userDao.update(updateUser);
    }

    @Override
    public void changeRole(Long roleId, Long userId) {
        User user = userDao.findOne(userId);
        logger.info("user set");
        Role role = roleDao.findOne(roleId);
        logger.info("role set");
        user.setRole(role);
    }

    @Override
    public void delete(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        User user = userDao.findByEmail(email);
        return userDTOConverter.toDTO(user);
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userDao.findAll();
        return userDTOConverter.toDTOList(users);
    }

    @Override
    public void disable(Long id) {
        User user = userDao.findOne(id);
        user.setEnable(false);
        userDao.update(user);
    }

    @Override
    public void enable(Long id) {
        User user = userDao.findOne(id);
        user.setEnable(true);
        userDao.update(user);
    }

    @Override
    public void assignDiscountToUser() {
        DiscountDao discountDao = new DiscountDaoImpl();
        Session session = userDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            User user = userDao.findById(1L);
            Discount ds = discountDao.findById(1L);
            user.setDiscount(ds);

            transaction.commit();

        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to assign discount to user", e);
        }
    }
}


