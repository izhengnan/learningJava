package com.sky.mapper;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface ShoppingCartMapper {
    void addShoppingCart(ShoppingCart shoppingCart);

    ShoppingCart selectDishExist(ShoppingCart shoppingCart);

    void updateShoppingCart(ShoppingCart shoppingCart);

    ArrayList<ShoppingCart> getShoppingCart(Long userId);
}
