package com.gayko.bookstore.controller;

import com.gayko.bookstore.config.PageProperties;
import com.gayko.bookstore.model.impl.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final PageProperties pageProperties;

    @Autowired
    public LoginController(PageProperties pageProperties) {
        this.pageProperties = pageProperties;
    }

    @GetMapping
    public String getLoginPage(ModelMap modelMap){
        modelMap.addAttribute("user", new UserDTO());
        return pageProperties.getLoginPagePath();
    }

/*    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication!=null){
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return pageProperties.getLoginPagePath();
    }*/
}
