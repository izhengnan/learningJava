package com.sky.task;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class OrderTask {
    @Autowired
    private OrderMapper orderMapper;

    @Scheduled(cron = "0 * * * * *")
    public void processTimeoutOrder(){
        log.info("处理超时订单,{}", LocalDateTime.now());
        LocalDateTime orderTime = LocalDateTime.now().plusMinutes(-15);
        Integer status = Orders.PENDING_PAYMENT;
        List<Orders> ordersList = orderMapper.getByStatusAndOrderTime(status,orderTime);
        if(ordersList!=null&& !ordersList.isEmpty()){
            for (Orders orders : ordersList) {
                orders.setStatus(Orders.CANCELLED);
                orders.setCancelReason("订单超时");
                orders.setCancelTime(LocalDateTime.now());
            }
            orderMapper.updateList(ordersList);
        }
    }

    @Scheduled(cron = "0 0 2 * * ?")
    public void processDeliveryOrder(){
        log.info("定时处理处理派送订单,{}", LocalDateTime.now());
        LocalDateTime orderTime = LocalDateTime.now().plusHours(-2);
        Integer status = Orders.DELIVERY_IN_PROGRESS;
        List<Orders> ordersList = orderMapper.getByStatusAndOrderTime(status,orderTime);
        if(ordersList!=null&& !ordersList.isEmpty()){
            for (Orders orders : ordersList) {
                orders.setStatus(Orders.COMPLETED);
            }
            orderMapper.updateList(ordersList);
        }
    }
}
