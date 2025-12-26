package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.service.ShoppingCartService;
import com.sky.vo.DishVO;
import com.sky.vo.SetmealVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Override
    public void addShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        shoppingCart.setUserId(BaseContext.getCurrentId());
        shoppingCart.setCreateTime(LocalDateTime.now());

        if (shoppingCartMapper.selectDishExist(shoppingCart) != null) {
            shoppingCartMapper.updateShoppingCart(shoppingCart);
        } else {
            shoppingCart.setNumber(1);
            if (shoppingCartDTO.getDishId() != null) {
                DishVO dishVO = dishMapper.selectById(shoppingCartDTO.getDishId());
                shoppingCart.setName(dishVO.getName());
                shoppingCart.setImage(dishVO.getImage());
                shoppingCart.setAmount(dishVO.getPrice());
            } else if (shoppingCartDTO.getSetmealId() != null) {
                SetmealVO setmealVO = setmealMapper.selectSetmealById(shoppingCartDTO.getSetmealId());
                shoppingCart.setName(setmealVO.getName());
                shoppingCart.setImage(setmealVO.getImage());
                shoppingCart.setAmount(setmealVO.getPrice());
            }

            shoppingCartMapper.addShoppingCart(shoppingCart);
        }
    }

    @Override
    public ArrayList<ShoppingCart> getShoppingCart() {
        Long userId = BaseContext.getCurrentId();
        return shoppingCartMapper.getShoppingCart(userId);
    }
}
