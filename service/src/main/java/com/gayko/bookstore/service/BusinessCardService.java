package com.gayko.bookstore.service;

import com.gayko.bookstore.model.impl.BusinessCardDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface BusinessCardService {

    List<BusinessCardDTO> findByUserId(Long id);

    BusinessCardDTO save(BusinessCardDTO businessCardDTO, Authentication authentication);

    void delete(Long id);

    //List<BusinessCardDTO> getAllById(Long id);

    void deleteById(final Long id);

    BusinessCardDTO get(Long id);

}
