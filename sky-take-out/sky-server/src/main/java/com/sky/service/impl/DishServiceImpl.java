package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.context.BaseContext;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.mapper.CategoryMapper;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;


    @Override
    @Transactional
    public void addDish(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        dish.setCreateTime(LocalDateTime.now());
        dish.setUpdateTime(LocalDateTime.now());
        dish.setCreateUser(BaseContext.getCurrentId());
        dish.setUpdateUser(BaseContext.getCurrentId());
        dishMapper.addDish(dish);

        Long id = dish.getId();

        List<DishFlavor> flavorList = dishDTO.getFlavors();
        if(flavorList != null&& !flavorList.isEmpty()){
            flavorList.forEach(flavor -> flavor.setDishId(id));
            dishFlavorMapper.addDishFlavor(flavorList);
        }

    }

    @Override
    public DishVO selectDishById(Long id) {
        DishVO dishVO = dishMapper.selectById(id);
        dishVO.setFlavors(dishMapper.selectDishFlavorsById(id));
        dishVO.setCategoryName(dishMapper.selectDishCategoryNameById(dishVO.getCategoryId()));
        return dishVO;
    }

    @Override
    public PageResult selectDishList(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        PageResult pageResult = new PageResult();
        Page<DishVO> page = dishMapper.selectDishList(dishPageQueryDTO);
        pageResult.setTotal(page.getTotal());
        pageResult.setRecords(page.getResult());
        return pageResult;
    }

    @Override
    @Transactional
    public void updateDish(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        dish.setUpdateTime(LocalDateTime.now());
        dish.setUpdateUser(BaseContext.getCurrentId());
        dishMapper.updateDish(dish);

        dishFlavorMapper.deleteDishFlavor(dishDTO.getId());
        List<DishFlavor> flavorList = dishDTO.getFlavors();
        if(flavorList != null&& !flavorList.isEmpty()){
            flavorList.forEach(flavor -> flavor.setDishId(dishDTO.getId()));
            dishFlavorMapper.addDishFlavor(flavorList);
        }


    }


}
