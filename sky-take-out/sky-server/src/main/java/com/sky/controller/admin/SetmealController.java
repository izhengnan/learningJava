package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController("adminSetmealController")
@RequestMapping("/admin/setmeal")
@Slf4j
public class SetmealController {
    @Autowired
    private SetmealService setmealService;

    @PostMapping()
    @Cacheable(cacheNames = "setmealCache", key = "#setmealDTO.categoryId")
    public Result addSetmeal(@RequestBody SetmealDTO setmealDTO){
        log.info("新增套餐：{}",setmealDTO);
        setmealService.addSetmeal(setmealDTO);
        return Result.success();
    }

    @GetMapping("/page")
    public Result<PageResult> selectSetmealList(SetmealPageQueryDTO setmealPageQueryDTO){
        log.info("分页查询：{}",setmealPageQueryDTO);
        PageResult pageResult = setmealService.selectSetmealList(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    @DeleteMapping
    @CacheEvict(cacheNames = "setmealCache", allEntries = true)
    public Result deleteSetmealList(@RequestParam("ids") ArrayList<Long> ids){
        log.info("删除套餐,id：{}",ids);
        setmealService.deleteSetmealList(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<SetmealVO> selectSetmealById(@PathVariable Long id){
        log.info("查询套餐：{}",id);
        SetmealVO setmealVO = setmealService.selectSetmealById(id);
        return Result.success(setmealVO);
    }

    @PutMapping
    @CacheEvict(cacheNames = "setmealCache", allEntries = true)
    public Result updateSetmeal(@RequestBody SetmealDTO setmealDTO){
        log.info("修改套餐：{}",setmealDTO);
        setmealService.updateSetmeal(setmealDTO);
        return Result.success();
    }

    @CacheEvict(cacheNames = "setmealCache", allEntries = true)
    @PostMapping("status/{status}")
    public Result startOrStopSetmeal(@PathVariable Integer status,Long id){
        log.info("启用或禁用id为{}的套餐：{}",id,status);
        setmealService.startOrStopSetmeal(status,id);
        return Result.success();
    }
}
