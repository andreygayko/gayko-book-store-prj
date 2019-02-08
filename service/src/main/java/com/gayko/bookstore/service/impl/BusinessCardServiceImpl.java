package com.gayko.bookstore.service.impl;

import com.gayko.bookstore.converters.impl.dto.BusinessCardDTOConverter;
import com.gayko.bookstore.converters.impl.entity.BusinessCardConverter;
import com.gayko.bookstore.dao.BusinessCardDao;
import com.gayko.bookstore.dao.UserDaoNew;
import com.gayko.bookstore.dao.model.BusinessCard;
import com.gayko.bookstore.dao.model.User;
import com.gayko.bookstore.model.impl.BusinessCardDTO;
import com.gayko.bookstore.model.impl.UserPrincipal;
import com.gayko.bookstore.service.BusinessCardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("businessCardService")
@Transactional
public class BusinessCardServiceImpl implements BusinessCardService {

    private static final Logger logger = LogManager.getLogger(BusinessCardServiceImpl.class);

    @Autowired
    private BusinessCardDao businessCardDao;
    @Autowired
    private BusinessCardConverter businessCardConverter;
    @Autowired
    private BusinessCardDTOConverter businessCardDTOConverter;
    @Autowired
    private UserDaoNew userDao;

    @Override
    public List<BusinessCardDTO> findByUserId(Long id) {
        List<BusinessCard> businessCards = businessCardDao.findByUserId(id);
        return businessCardDTOConverter.toDTOList(businessCards);
    }

    @Override
    public BusinessCardDTO save(BusinessCardDTO businessCardDTO, Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Long userId = userPrincipal.getId();
        BusinessCard businessCard = businessCardConverter.toEntity(businessCardDTO);
        User user = userDao.findOne(userId);
        businessCard.setUser(user);
        businessCardDao.create(businessCard);
        return businessCardDTOConverter.toDTO(businessCard);
    }

    @Override
    public void delete(Long id) {
        List<BusinessCard> businessCards = businessCardDao.findByUserId(id);
        for (BusinessCard businessCard : businessCards) {
            businessCardDao.delete(businessCard);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            BusinessCard card = businessCardDao.findOne(id);
            businessCardDao.delete(card);
            logger.info("Delete card by Id successful!");
        } catch (Exception e) {
            logger.error("Delete card by Id failed!", e);
        }
    }

    @Override
    public BusinessCardDTO get(Long id) {
        BusinessCardDTO cardDTO = null;
        try {
            BusinessCard card = businessCardDao.findOne(id);
            cardDTO = businessCardDTOConverter.toDTO(card);
            logger.info("Get user successful!");
        } catch (Exception e) {
            logger.error("Get user failed!", e);
        }
        return cardDTO;
    }

}
