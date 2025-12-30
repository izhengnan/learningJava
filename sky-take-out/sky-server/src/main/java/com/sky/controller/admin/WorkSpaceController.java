package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.service.WorkspaceService;
import com.sky.vo.BusinessDataVO;
import com.sky.vo.DishOverViewVO;
import com.sky.vo.OrderOverViewVO;
import com.sky.vo.SetmealOverViewVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.LocalTime;
@RestController
@RequestMapping("/admin/workspace")
@Slf4j
public class WorkSpaceController {
    @Autowired
    private WorkspaceService workspaceService;

    @GetMapping("/overviewOrders")
    public Result<OrderOverViewVO> orderOverView(){
        log.info("查询订单管理数据");
        return Result.success(workspaceService.getOrderOverView());
    }

    @GetMapping("/overviewDishes")
    public Result<DishOverViewVO> dishOverView(){
        log.info("查询菜品总览");
        return Result.success(workspaceService.getDishOverView());
    }

    @GetMapping("/overviewSetmeals")
    public Result<SetmealOverViewVO> setmealOverView(){
        log.info("查询套餐总览");
        return Result.success(workspaceService.getSetmealOverView());
    }
}
