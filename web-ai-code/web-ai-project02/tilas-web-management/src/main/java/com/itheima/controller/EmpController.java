package com.itheima.controller;


import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工管理控制器类
 * 处理员工相关的HTTP请求
 */
@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;

    /**
     * 分页查询员工列表
     * @return 分页查询结果，包含员工数据和分页信息
     */
    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        // 记录分页查询日志
        log.info("分页查询:{}",empQueryParam);
        // 调用服务层进行分页查询
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        // 返回成功结果
        return Result.success(pageResult);
    }

    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工：{}",emp);
        empService.addEmp(emp);
        return Result.success();
    }

    @DeleteMapping
    public Result deleteById(Integer[] ids){
        log.info("删除员工：{}",ids);
        empService.deleteById(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result SelectById(@PathVariable Integer id){
        log.info("根据ID查询员工数据：{}",id);
        Emp emp = empService.SelectById(id);
        return Result.success(emp);
    }

    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工数据：{}",emp);
        empService.update(emp);
        return Result.success();
    }
}