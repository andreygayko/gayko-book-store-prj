

package com.gayko.bookstore.controller;

import com.gayko.bookstore.config.PageProperties;
//import com.gayko.bookstore.controller.validator.UserValidator;
import com.gayko.bookstore.model.impl.UserDTO;
import com.gayko.bookstore.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);


    final UserService userService;
  //  final UserValidator userValidator;
    final PageProperties pageProperties;

    @Autowired
    public UserController(UserService userService,/* UserValidator userValidator,*/ PageProperties pageProperties) {
        this.userService = userService;
       // this.userValidator = userValidator;
        this.pageProperties = pageProperties;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_USERS')")
    public String getUsers(ModelMap modelMap) {
        List<UserDTO> users = userService.findAll();
        UserDTO userDTO = userService.findById(2l);
        System.out.println("11111111111111111111111111111111111"+userDTO);
        System.out.println("2222222222222222222222222222222222"+userDTO.getRole());
        modelMap.addAttribute("users", users);
        return pageProperties.getUserPagePath();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('VIEW_USERS')")
    public String getUser(
            @PathVariable("id") Long id,
            ModelMap modelMap) {
        UserDTO user = userService.findById(id);
        modelMap.addAttribute("user", user);
        return pageProperties.getUpdateUserPagePath();
    }


    @PostMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('CHANGE_USER_ROLE')")
    public String updateUser(
            @PathVariable("id") Long id,
            @ModelAttribute UserDTO user,
            BindingResult result,
            ModelMap modelMap) {
        user.setId(id);
     //   userValidator.validate(user, result);
        if (result.hasErrors()) {
            return pageProperties.getUpdateUserPagePath();
        } else {
            user = userService.update(user);
            modelMap.addAttribute("user", user);
            return "redirect:/users";
        }
    }

    @GetMapping(value = "/registration")
    public String addUserPage(ModelMap modelMap) {
        modelMap.addAttribute("user", new UserDTO());
        return pageProperties.getCreateUserPagePath();
    }

    @PostMapping(value = "/registration")
    public String createUser(
            @ModelAttribute UserDTO user,
            BindingResult result,
            ModelMap modelMap) {
   //     userValidator.validate(user, result);
        if (result.hasErrors()) {
            return pageProperties.getCreateUserPagePath();
        } else {
            user = userService.create(user);
            modelMap.addAttribute("user", user);
            return pageProperties.getItemsPagePath();
        }
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('DELETE_USER')")
    public String deleteUser(@RequestParam("ids") Long[] ids) {
        for (Long id : ids) {
            userService.delete(id);
        }
        return "redirect:/users";
    }

    @GetMapping("/filter")
    public String getUser(
            @RequestParam(value = "email", defaultValue = "test@gmail.com") String email, ModelMap modelMap) {
        UserDTO user = userService.findUserByEmail(email);
        modelMap.addAttribute("user", user);
        return pageProperties.getUpdateUserPagePath();
    }

    @GetMapping(value = "/{id}/enable")
    @PreAuthorize("hasAuthority('DISABLE_USER')")
    public String enableUser(
            @PathVariable("id") Long id
    ) {
        userService.enable(id);
        return "redirect:/users";
    }

    @GetMapping(value = "/{id}/disable")
    @PreAuthorize("hasAuthority('DISABLE_USER')")
    public String disableUser(
            @PathVariable("id") String id
    ) {
        userService.disable(Long.valueOf(id));
        return "redirect:/users";
    }

    @GetMapping("/{id}/role")
    @PreAuthorize("hasAuthority('CHANGE_USER_ROLE')")
    public String getUserRole(
            @PathVariable("id") Long id,
            ModelMap modelMap
    ) {
        UserDTO user = userService.findById(id);
        modelMap.addAttribute("user", user);
        return pageProperties.getUserRolePagePath();
    }

    @PostMapping("/{id}/role")
    @PreAuthorize("hasAuthority('CHANGE_USER_ROLE')")
    public String updateRole(
            @RequestParam String role,
            @PathVariable("id") Long id,
            @ModelAttribute UserDTO user
    ) {
        user.setId(id);
        //userService.findById(id);
            userService.changeRole(Long.valueOf(role), id);
        System.out.println(user.getName()+"++++++++++++++++++++++++++++++++++++++");
            logger.info(role);
        return pageProperties.getUserPagePath();
    }

    @GetMapping("/{id}/password")
    @PreAuthorize("hasAuthority('CHANGE_USER_PASSWORD')")
    public String getUserPassword(
            @PathVariable("id") Long id,
            ModelMap modelMap
    ) {
        UserDTO user = userService.findById(id);
        modelMap.addAttribute("user", user);
        return pageProperties.getUserPasswordPagePath();
    }

    @PostMapping("/{id}/password")
    @PreAuthorize("hasAuthority('CHANGE_USER_PASSWORD')")
    public String updatePassword(
            @ModelAttribute("user") UserDTO user,
            @PathVariable("id") Long id,
            @RequestParam String password,
            ModelMap modelMap
    ) {
        user.setId(id);
        modelMap.addAttribute("user", user);
        userService.updatePassword(password, id);
        return pageProperties.getUserPasswordPagePath();
    }
    }


