package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;

import java.util.ArrayList;

public interface SetmealService {
    void addSetmeal(SetmealDTO setmealDTO);

    PageResult selectSetmealList(SetmealPageQueryDTO setmealPageQueryDTO);

    void deleteSetmealList(ArrayList<Long> ids);
}
