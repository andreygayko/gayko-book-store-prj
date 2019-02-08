package com.gayko.bookstore.service;

import com.gayko.bookstore.model.impl.DiscountDTO;

import java.math.BigDecimal;
import java.util.List;

public interface DiscountService {

    List<DiscountDTO> save(List<DiscountDTO> itemList);

    List<DiscountDTO> getByInterestRate(BigDecimal percent);

    List<DiscountDTO> findAllItems();

    void discountAssignment(int min, int max, BigDecimal percent); //Assign discount to items in price range from min - to max

}


