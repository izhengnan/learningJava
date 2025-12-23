package com.qkl.auctionsystem.service.impl;

import com.qkl.auctionsystem.filter.TokenFilter;
import com.qkl.auctionsystem.mapper.OrderMapper;
import com.qkl.auctionsystem.pojo.entity.Order;
import com.qkl.auctionsystem.service.ItemService;
import com.qkl.auctionsystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ItemService itemService;

    @Override
    public void payOrder(Long orderId) {
        Long userId = TokenFilter.getCurrentUserId();
        orderMapper.updateStatusByUserId(orderId,userId);
    }

    @Override
    public List<Order> getMyOrders() {
        Long userId = TokenFilter.getCurrentUserId();
        
        // 检查用户是否已登录
        if (userId == null) {
            throw new RuntimeException("用户未登录，请先登录");
        }

        try {
            return orderMapper.selectOrdersByUserId(userId);
        } catch (Exception e) {
            throw new RuntimeException("查询订单失败: " + e.getMessage(), e);
        }
    }
}