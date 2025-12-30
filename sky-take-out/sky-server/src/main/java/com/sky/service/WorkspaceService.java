package com.sky.service;

import com.sky.vo.DishOverViewVO;
import com.sky.vo.OrderOverViewVO;
import com.sky.vo.SetmealOverViewVO;

public interface WorkspaceService {
    OrderOverViewVO getOrderOverView();

    DishOverViewVO getDishOverView();

    SetmealOverViewVO getSetmealOverView();
}
