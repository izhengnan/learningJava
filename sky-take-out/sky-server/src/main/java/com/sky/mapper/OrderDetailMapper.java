package com.sky.mapper;

import com.sky.entity.OrderDetail;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface OrderDetailMapper {
    void addOrderDetail(ArrayList<OrderDetail> orderDetailArrayList);

    List<OrderDetail> selectByOrderId(Long id);
}
