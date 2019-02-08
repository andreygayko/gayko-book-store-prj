package com.gayko.bookstore.service;

import com.gayko.bookstore.model.impl.ItemDTO;

import java.util.List;

public interface ItemDTOService {

        List<ItemDTO> save(List<ItemDTO> itemDTO);
        //List<Item> findAll();
}
