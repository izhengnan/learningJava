package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;

import java.util.ArrayList;

public interface ShoppingCartService {
    void addShoppingCart(ShoppingCartDTO shoppingCartDTO);

    ArrayList<ShoppingCart> getShoppingCart();

    void cleanShoppingCart();

    void deleteShoppingCartDishOrSetmeal(ShoppingCartDTO shoppingCartDTO);
}
