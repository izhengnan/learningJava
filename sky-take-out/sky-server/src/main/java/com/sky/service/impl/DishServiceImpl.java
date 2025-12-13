package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.context.BaseContext;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.mapper.DishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;


    @Override
    public void addDish(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        dish.setCreateTime(LocalDateTime.now());
        dish.setUpdateTime(LocalDateTime.now());
        dish.setCreateUser(BaseContext.getCurrentId());
        dish.setUpdateUser(BaseContext.getCurrentId());
        dishMapper.addDish(dish);
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
        //TODO 未实现菜品分类的查询
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        PageResult pageResult = new PageResult();
        Page<Dish> page = dishMapper.selectDishList(dishPageQueryDTO);
        pageResult.setTotal(page.getTotal());
        pageResult.setRecords(page.getResult());
        return pageResult;
    }


}
