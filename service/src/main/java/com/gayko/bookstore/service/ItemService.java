package com.gayko.bookstore.service;

import com.gayko.bookstore.model.impl.ItemDTO;

import java.util.List;

public interface ItemService {

    List<ItemDTO> save(List<ItemDTO> items);

    void update(ItemDTO itemDTO);

    //void delete(ItemDTO itemDTO);

    List<ItemDTO> findAll();

    List<ItemDTO> findItemsInPriceRange(int from, int to);

    ItemDTO findById(Long id);

    void upload(List<ItemDTO> items);

    void remove(Long id);

    void create(ItemDTO item);

    //void upload(MultipartFile file);
}
