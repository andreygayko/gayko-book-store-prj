package com.gayko.bookstore.converters.impl.dto;


import com.gayko.bookstore.converters.DTOConverter;
import com.gayko.bookstore.dao.model.Discount;
import com.gayko.bookstore.dao.model.Item;
import com.gayko.bookstore.model.impl.DiscountDTO;
import com.gayko.bookstore.model.impl.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("discountDTOConverter")
public class DiscountDTOConverter implements DTOConverter<Discount, DiscountDTO> {

    @Autowired
    @Qualifier("itemDTOConverter") DTOConverter<Item, ItemDTO> itemDTOConverter;

    @Override
    public DiscountDTO toDTO(Discount entity) {

        if (entity == null) {
            return null;
        }
        DiscountDTO discountDTO = new DiscountDTO();
        discountDTO.setName(entity.getName());
        discountDTO.setInterestRate(entity.getInterestRate());
        discountDTO.setFinalDate(entity.getFinalDate());
        discountDTO.setName(entity.getName());
        discountDTO.setItems(itemDTOConverter.toDTOList(entity.getItems()));
        return discountDTO;
    }
}

