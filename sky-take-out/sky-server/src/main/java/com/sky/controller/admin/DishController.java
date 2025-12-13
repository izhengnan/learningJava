package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@Slf4j
@RequestMapping("/admin/dish")
public class DishController {
    @Autowired
    private DishService dishService;

    @PostMapping
    public Result addDish(@RequestBody DishDTO dishDTO){
        log.info("新增菜品：{}",dishDTO);
        dishService.addDish(dishDTO);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<DishVO> SelectDishById(@PathVariable Long id){
        log.info("查询菜品：{}",id);
        DishVO dishVO = dishService.selectDishById(id);
        return Result.success(dishVO);
    }

    @GetMapping("/page")
    public Result<PageResult> SelectDishList(DishPageQueryDTO dishPageQueryDTO){
        log.info("分页查询菜品:{}",dishPageQueryDTO);
        PageResult pageResult = dishService.selectDishList(dishPageQueryDTO);
        return Result.success(pageResult);
    }
    @PutMapping
    public Result updateDish(@RequestBody DishDTO dishDTO){
        log.info("修改菜品：{}",dishDTO);
        dishService.updateDish(dishDTO);
        return Result.success();
    }

    @DeleteMapping
    public Result deleteDishList(@RequestParam("ids") String ids){
        log.info("删除菜品：{}",ids);
        ArrayList<Long> id = new ArrayList<>();
        if (ids != null && !ids.isEmpty()) {
            String[] idArray = ids.split(",");
            for (String idStr : idArray) {
                id.add(Long.parseLong(idStr.trim()));
            }
        }
        dishService.deleteDishList(id);
        return Result.success();
    }

    @PostMapping("status/{status}")
    public Result startOrStopDish(@PathVariable Integer status,Long id){
        log.info("启用或禁用菜品：{}",id);
        dishService.startOrStopDish(status,id);
        return Result.success();
    }
}
