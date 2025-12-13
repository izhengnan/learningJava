package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface SetmealDishMapper {

    ArrayList<Long> getSetmealIdsByDishIds(ArrayList<Long> dishIds);
}
