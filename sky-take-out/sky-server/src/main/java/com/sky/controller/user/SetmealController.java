package com.sky.controller.user;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController("userSetmealController")
@RequestMapping("/user/setmeal")
@Slf4j
public class SetmealController {
    @Autowired
    private SetmealService setmealService;

    @GetMapping("/list")
    public Result<ArrayList<Setmeal>> selectSetmealByCategoryId(Long categoryId){
        log.info("根据分类id查询套餐：{}",categoryId);
        ArrayList<Setmeal> setmealArrayList = setmealService.selectSetmealByCategoryId(categoryId);
        return Result.success(setmealArrayList);
    }

    @GetMapping("/dish/{id}")
    public Result<ArrayList<DishItemVO>> selectDishBySetmealId(@PathVariable Long id){
        log.info("根据套餐id查询包含的菜品：{}",id);
        ArrayList<DishItemVO> dishItemVOArrayList = setmealService.selectDishBySetmealId(id);
        return Result.success(dishItemVOArrayList);
    }
}
