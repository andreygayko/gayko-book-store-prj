package com.gayko.bookstore.controller;

import com.gayko.bookstore.config.PageProperties;
import com.gayko.bookstore.controller.validator.ItemValidator;
import com.gayko.bookstore.model.impl.ItemDTO;
import com.gayko.bookstore.service.ItemService;
import com.gayko.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    @Qualifier("pageProperties")
    private PageProperties pageProperties;
    @Autowired
    private ItemService itemService;
    @Autowired
    private OrderService orderService;
    @Autowired
    ItemValidator itemValidator;

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_ITEMS')")
    public String getItems(ModelMap modelMap) {
        List<ItemDTO> items = itemService.findAll();
        modelMap.addAttribute("items", items);
        return pageProperties.getItemsPagePath();
    }

    @GetMapping(value = "/{id}/order")
    @PreAuthorize("hasAuthority('CREATE_ORDER')")
    public String createOrder(@PathVariable("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        orderService.create(id, authentication);
        return pageProperties.getItemsPagePath();
    }

    @PostMapping(value = "/{id}/remove")
    @PreAuthorize("hasAuthority('REMOVE_ITEMS')")
    public String removeItem(@PathVariable("id") Long id){
        itemService.remove(id);
        return pageProperties.getItemsPagePath();}

    @GetMapping("/upload")
    @PreAuthorize("hasAuthority('UPLOAD_ITEMS')")
    public String displayForm() {
        return pageProperties.getItemsUploadPagePath();
    }

    @PostMapping("/upload")
    @PreAuthorize("hasAuthority('UPLOAD_ITEMS')")
    public String uploadFile(@RequestParam(value = "file") MultipartFile file, ModelMap modelMap) throws IOException {
        modelMap.addAttribute("file", file);
        file.transferTo(new File(pageProperties.getItemsFilePath()));
       // itemService.upload(file);
        return pageProperties.getItemsUploadPagePath();
    }

    @GetMapping("/create")
    @PreAuthorize("hasAuthority('CREATE_ITEMS')")
    public String createItem(ModelMap modelMap) {
        modelMap.addAttribute("item", new ItemDTO());
        return pageProperties.getCreateItemPagePath();
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE_ITEMS')")
    public String createItem(
            @ModelAttribute("item") ItemDTO item,
            ModelMap modelMap,
            BindingResult bindingResult) {
        itemValidator.validate(item, bindingResult);
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("item", item);
            return pageProperties.getCreateItemPagePath();
        } else {
            itemService.create(item);
            return pageProperties.getCreateItemPagePath();
        }
    }

}
