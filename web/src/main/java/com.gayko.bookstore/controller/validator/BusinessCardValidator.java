package com.gayko.bookstore.controller.validator;

import com.gayko.bookstore.dao.model.BusinessCard;
import com.gayko.bookstore.model.impl.BusinessCardDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class BusinessCardValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return BusinessCard.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "title", "businessCard.title.empty");
        BusinessCardDTO businessCard = (BusinessCardDTO) o;
        if (businessCard.getWorkingTelephone().length() > 20) {
            errors.rejectValue("workingTelephone", "businessCard.workingTelephone.long.length");

        }
    }
}
