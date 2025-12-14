package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.SetmealVO;

import java.util.ArrayList;

public interface SetmealService {
    void addSetmeal(SetmealDTO setmealDTO);

    PageResult selectSetmealList(SetmealPageQueryDTO setmealPageQueryDTO);

    void deleteSetmealList(ArrayList<Long> ids);

    SetmealVO selectSetmealById(Long id);

    void updateSetmeal(SetmealDTO setmealDTO);
}
