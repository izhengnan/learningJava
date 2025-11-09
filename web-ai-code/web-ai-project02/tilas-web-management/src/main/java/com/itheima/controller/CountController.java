package com.itheima.controller;

import com.itheima.pojo.EmpGender;
import com.itheima.pojo.JobOption;
import com.itheima.pojo.Result;
import com.itheima.service.CountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/report")
@RestController
public class CountController {
    @Autowired
    private CountService countService;

    @GetMapping("/empJobData")
    public Result CountEmpJob(){
        log.info("统计员工职位数据");
        JobOption jobOption = countService.countEmpJob();
        return Result.success(jobOption);
    }

    @GetMapping("/empGenderData")
    public Result CountEmpGender(){
        log.info("统计员工姓别数据");
        List<EmpGender> list = countService.CountEmpGender();
        return Result.success(list);
    }
}
