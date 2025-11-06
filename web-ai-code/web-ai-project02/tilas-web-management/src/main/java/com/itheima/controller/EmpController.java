package com.itheima.controller;


import com.itheima.pojo.Emp;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
     * @param page 页码，默认值为1
     * @param pageSize 每页显示条数，默认值为10
     * @return 分页查询结果，包含员工数据和分页信息
     */
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        // 记录分页查询日志
        log.info("分页查询:{},{}",page,pageSize);
        // 调用服务层进行分页查询
        PageResult<Emp> pageResult = empService.page(page, pageSize);
        // 返回成功结果
        return Result.success(pageResult);
    }
}