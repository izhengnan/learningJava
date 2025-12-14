package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.SetmealDish;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface SetmealDishMapper {

    ArrayList<Long> getSetmealIdsByDishIds(ArrayList<Long> dishIds);

    void addSetmealDishes(List<SetmealDish> setmealDishes);

    void deleteSetmealDishList(ArrayList<Long> ids);

    List<SetmealDish> selectSetmealDishById(Long id);

    ArrayList<Integer> selectSetmealDishByIdList(ArrayList<Long> ids);
}
