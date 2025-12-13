package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

public interface DishService {
    void addDish(DishDTO dishDTO);

    DishVO selectDishById(Long id);

    PageResult selectDishList(DishPageQueryDTO dishPageQueryDTO);

    void updateDish(DishDTO dishDTO);
}
