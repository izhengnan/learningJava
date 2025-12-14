package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.context.BaseContext;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.mapper.SetmealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealMapper setmealMapper;

    @Autowired
    private SetmealDishMapper setmealDishMapper;


    @Override
    public void addSetmeal(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO,setmeal);
        setmeal.setCreateTime(LocalDateTime.now());
        setmeal.setUpdateTime(LocalDateTime.now());
        setmeal.setCreateUser(BaseContext.getCurrentId());
        setmeal.setUpdateUser(BaseContext.getCurrentId());
        setmealMapper.addSetmeal(setmeal);

        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        if (setmealDishes != null && !setmealDishes.isEmpty()){
            setmealDishes.forEach(setmealDish -> setmealDish.setSetmealId(setmeal.getId()));
        }
        setmealDishMapper.addSetmealDishes(setmealDishes);
    }

    @Override
    public PageResult selectSetmealList(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageHelper.startPage(setmealPageQueryDTO.getPage(),setmealPageQueryDTO.getPageSize());
        PageResult pageResult = new PageResult();
        Page<SetmealVO> page = setmealMapper.selectSetmealList(setmealPageQueryDTO);
        pageResult.setTotal(page.getTotal());
        pageResult.setRecords(page.getResult());
        return pageResult;
    }

    @Override
    @Transactional
    public void deleteSetmealList(ArrayList<Long> ids) {
        ArrayList<Integer> status = setmealDishMapper.selectSetmealDishByIdList(ids);
        for (int i = 0; i < status.size(); i++) {
            if (status.get(i) == 1){
                throw new RuntimeException(MessageConstant.SETMEAL_ON_SALE);
            }
        }
        setmealMapper.deleteSetmealList(ids);
        setmealDishMapper.deleteSetmealDishList(ids);
    }

    @Override
    public SetmealVO selectSetmealById(Long id) {
        SetmealVO setmealVO = setmealMapper.selectSetmealById(id);
        setmealVO.setSetmealDishes(setmealDishMapper.selectSetmealDishById(id));
        return setmealVO;
    }
}
