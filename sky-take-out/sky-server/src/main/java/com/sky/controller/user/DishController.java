package com.sky.controller.user;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("userDishController")
@Slf4j
@RequestMapping("/user/dish")
public class DishController {
    @Autowired
    private DishService dishService;
    @GetMapping("/list")
    public Result<List<DishVO>> selectDishListByCategoryId(Long categoryId){
        log.info("根据分类id查询菜品：{}",categoryId);
        List<DishVO> dishVOSList= dishService.selectDishListByCategoryId(categoryId);
        return Result.success(dishVOSList);
    }
}
