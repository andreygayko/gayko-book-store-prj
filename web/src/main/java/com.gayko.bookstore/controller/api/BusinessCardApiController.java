package com.gayko.bookstore.controller.api;

import com.gayko.bookstore.model.impl.BusinessCardDTO;
import com.gayko.bookstore.service.BusinessCardService;
import com.gayko.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class BusinessCardApiController {

    @Autowired
    @Qualifier("businessCardService")
    private BusinessCardService businessCardService;
/*    @Autowired
    @Qualifier("userService")
    private UserService userService;*/

    @GetMapping(value = "/users/{id}/cards")
    @PreAuthorize("hasAuthority('MANAGE_BUSINESS_CARD')")
    @ResponseBody
    public List<BusinessCardDTO> getCardsById(@PathVariable("id") Long id) {
        return businessCardService.findByUserId(id);
    }

    @GetMapping(value = "/users/cards/{id}")
    @PreAuthorize("hasAuthority('MANAGE_BUSINESS_CARD')")
    @ResponseBody
    public BusinessCardDTO getOne(@PathVariable("id") Long id) {
        return businessCardService.get(id);
    }

    @DeleteMapping(value = "/user.cards/{id}/delete")
    @PreAuthorize("hasAuthority('MANAGE_BUSINESS_CARD')")
    public String deleteBusinessCard(@PathVariable(name = "id") Long id) {
        businessCardService.delete(id);
        return ("User " + id + "card is deleted");
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('MANAGE_BUSINESS_CARD')")
    public String deleteBusinessCardById(@PathVariable(name = "id") Long id) {
        businessCardService.deleteById(id);
        return ("Card " + id + " is deleted");
    }


}
