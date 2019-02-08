package com.gayko.bookstore.controller.validator;

import com.gayko.bookstore.model.impl.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "user.name.empty");
        ValidationUtils.rejectIfEmpty(errors, "email", "user.email.empty");
        UserDTO user = (UserDTO) o;

        Pattern pattern = Pattern.compile(
                "^[A-Z0-9._%+-]-@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE);
        String q = user.getSurname();
        System.out.println("********************************************"+q);
        String a = user.getEmail();
        System.out.println("********************************************"+a);
        Boolean b = pattern.matcher(a).matches();
        if (!b) {
            errors.rejectValue("email", "user.email.invalid");
        }
    }
}
