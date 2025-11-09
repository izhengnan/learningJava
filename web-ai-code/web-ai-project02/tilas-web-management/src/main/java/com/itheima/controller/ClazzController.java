package com.itheima.controller;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {

    @Autowired
    ClazzService clazzService;

    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam){
        log.info("分页查询:{}",clazzQueryParam);
        PageResult<Clazz> clazzPageResult = clazzService.page(clazzQueryParam);
        return Result.success(clazzPageResult);
    }

    @PostMapping
    public Result insert(@RequestBody Clazz clazz){
        log.info("添加班级:{}",clazz);
        clazzService.addClazz(clazz);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id){
        log.info("根据id查询班级:{}",id);
        Clazz clazz = clazzService.selectById(id);
        return Result.success(clazz);
    }

    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("修改班级:{}",clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id){
        log.info("删除班级:{}",id);
        clazzService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result selectAll(){
        log.info("查询所有班级");
        List<Clazz> clazzList = clazzService.selectAll();
        return Result.success(clazzList);
    }
}
