package com.gayko.bookstore.controller;

import com.gayko.bookstore.config.PageProperties;
import com.gayko.bookstore.controller.validator.BusinessCardValidator;
import com.gayko.bookstore.dao.UserDaoNew;
import com.gayko.bookstore.dao.model.Permission;
import com.gayko.bookstore.dao.model.User;
import com.gayko.bookstore.model.impl.BusinessCardDTO;
import com.gayko.bookstore.model.impl.UserDTO;
import com.gayko.bookstore.model.impl.UserPrincipal;
import com.gayko.bookstore.service.BusinessCardService;
import com.gayko.bookstore.service.impl.BusinessCardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
//@RequestMapping("/business-cards")
public class BusinessCardController {

    @Autowired
    private BusinessCardService businessCardService;
    @Autowired
    private PageProperties pageProperties;
    @Autowired
    private BusinessCardValidator businessCardValidator;
    @Autowired
    private UserDaoNew userDao;



    /*@GetMapping
    @PreAuthorize("hasAnyAuthority('MANAGE_BUSINESS_CARD')")
    public String getCards(ModelMap modelMap){
        List<BusinessCardDTO> businessCards = businessCardService.findByUserId(1L);
        return pageProperties.getBusinessCardPagePath();
    }*/
/*
    @PostMapping
    @PreAuthorize("hasAnyAuthority('MANAGE_BUSINESS_CARD')")
    public String createCard(
            @ModelAttribute BusinessCardDTO businessCardDTO,
            BindingResult result,
            ModelMap modelMap) {
        businessCardValidator.validate(businessCardDTO, result);
        if (result.hasErrors()) {
            return pageProperties.getCreateUserPagePath();
        } else {
            businessCardDTO = businessCardService.save(businessCardDTO);
            modelMap.addAttribute("user", businessCardDTO);
            return "redirect:/business.cards";
        }
    }*/

    /*@PostMapping("/delete")
    @PreAuthorize("hasAnyAuthority('MANAGE_BUSINESS_CARD')")
    public String deleteUser(@RequestParam("ids") Long[] ids) {
        for (Long id : ids) {
            businessCardService.delete(id);
        }
        return "redirect:/business.cards";
    }*/

    @GetMapping("/cards")
    @PreAuthorize("hasAuthority('MANAGE_BUSINESS_CARD')")
    public String getBusinessCardPage(
            ModelMap modelMap
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Long userId = userPrincipal.getId();
        List<BusinessCardDTO> businessCards = businessCardService.findByUserId(userId);
        modelMap.addAttribute("businessCards", businessCards);
        return pageProperties.getBusinessCardPagePath();
    }

    @GetMapping("/card.create")
    @PreAuthorize("hasAuthority('MANAGE_BUSINESS_CARD')")
    public String getCreateBusinessCardPage(ModelMap modelMap) {
        modelMap.addAttribute("businessCard", new BusinessCardDTO());
        return pageProperties.getCreateBusinessCardPagePath();
    }

    @PostMapping("/card.create")
    @PreAuthorize("hasAuthority('MANAGE_BUSINESS_CARD')")
    public String createBusinessCard(
            @ModelAttribute("businessCard") BusinessCardDTO businessCard,
            ModelMap modelMap,
            BindingResult bindingResult) {
        businessCardValidator.validate(businessCard, bindingResult);
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("businessCard", businessCard);
            return pageProperties.getCreateBusinessCardPagePath();
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            businessCardService.save(businessCard, authentication);
            return "redirect:/cards";
        }
    }
}
