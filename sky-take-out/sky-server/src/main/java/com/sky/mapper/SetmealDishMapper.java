package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface SetmealDishMapper {

    ArrayList<Long> getSetmealIdsByDishIds(ArrayList<Long> dishIds);

    void addSetmealDishes(List<SetmealDish> setmealDishes);
}
