package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.entity.Dish;
import com.sky.vo.DishVO;

public interface DishService {
    void addDish(DishDTO dishDTO);

    DishVO selectDishById(Long id);
}
