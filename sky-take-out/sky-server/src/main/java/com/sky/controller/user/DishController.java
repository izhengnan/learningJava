package com.sky.controller.user;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("userDishController")
@Slf4j
@RequestMapping("/user/dish")
public class DishController {
    @Autowired
    private DishService dishService;
    @Autowired
    private RedisTemplate redisTemplate;
    @GetMapping("/list")
    public Result<List<DishVO>> selectDishListByCategoryId(Long categoryId){
        log.info("根据分类id查询菜品：{}",categoryId);
        //构造redis中的key，dish_categoryId
        String key = "dish_"+categoryId;
        //查询redis
        List<DishVO> dishVOSList = (List<DishVO>) redisTemplate.opsForValue().get(key);
        if (dishVOSList != null && !dishVOSList.isEmpty()){
            return Result.success(dishVOSList);
        }
        //不存在则查表，并将数据存入redis

        dishVOSList= dishService.selectDishListByCategoryId(categoryId);
        redisTemplate.opsForValue().set(key,dishVOSList);
        return Result.success(dishVOSList);
    }
}
