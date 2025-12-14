package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.exception.BaseException;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealDishMapper;
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
import java.util.Objects;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;
    @Autowired
    private SetmealDishMapper setmealDishMapper;


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
        if (dishVO == null) {
            throw new BaseException(MessageConstant.DISH_NOT_FOUND);
        }
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
        //TODO 事务一致性风险待解决
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

    @Override
    @Transactional
    public void deleteDishList(ArrayList<Long> dishId) {
        //TODO 循环查表后期需修改
        for (Long aLong : dishId) {
            if (Objects.equals(dishMapper.selectById(aLong).getStatus(), StatusConstant.ENABLE)) {
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        }
        ArrayList<Long> setmealIds = setmealDishMapper.getSetmealIdsByDishIds(dishId);
        if (setmealIds != null && !setmealIds.isEmpty()) {
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }
        dishMapper.deleteDishList(dishId);
        dishFlavorMapper.deleteDishFlavorList(dishId);
    }

    @Override
    @Transactional
    public void startOrStopDish(Integer status, Long id) {
        if(Objects.equals(status, StatusConstant.DISABLE)) {
            Integer mealDishStatus = setmealDishMapper.selectSetmealStatusByDishId(id);
            if(Objects.equals(mealDishStatus, StatusConstant.ENABLE)){
                ArrayList<Long> dishIds = new ArrayList<>();
                dishIds.add(id);
                ArrayList<Long> mealDishList = setmealDishMapper.getSetmealIdsByDishIds(dishIds);
                if (mealDishList != null && !mealDishList.isEmpty()) {
                    throw new DeletionNotAllowedException(MessageConstant.DISH_BE_STOP_BY_SETMEAL);
                }
            }
        }
        Dish dish=Dish.builder()
                .status(status)
                .updateTime(LocalDateTime.now())
                .updateUser(BaseContext.getCurrentId())
                .id(id).build();
        dishMapper.startOrStopDish(dish);
    }

    @Override
    public List<DishVO> selectDishListByCategoryId(Long categoryId) {
        return dishMapper.selectDishListByCategoryId(categoryId);
    }


}
