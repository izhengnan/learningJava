package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;

import java.util.ArrayList;

public interface SetmealService {
    void addSetmeal(SetmealDTO setmealDTO);

    PageResult selectSetmealList(SetmealPageQueryDTO setmealPageQueryDTO);

    void deleteSetmealList(ArrayList<Long> ids);

    SetmealVO selectSetmealById(Long id);

    void updateSetmeal(SetmealDTO setmealDTO);

    void startOrStopSetmeal(Integer status,Long id);

    ArrayList<Setmeal> selectSetmealByCategoryId(Long categoryId);

    ArrayList<DishItemVO> selectDishBySetmealId(Long id);
}
