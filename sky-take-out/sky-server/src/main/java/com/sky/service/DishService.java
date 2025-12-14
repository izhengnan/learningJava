package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.ArrayList;
import java.util.List;

public interface DishService {
    void addDish(DishDTO dishDTO);

    DishVO selectDishById(Long id);

    PageResult selectDishList(DishPageQueryDTO dishPageQueryDTO);

    void updateDish(DishDTO dishDTO);

    void deleteDishList(ArrayList<Long> id);

    void startOrStopDish(Integer status, Long id);

    List<DishVO> selectDishListByCategoryId(Long categoryId);
}
