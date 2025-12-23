package com.qkl.auctionsystem.mapper;

import com.qkl.auctionsystem.pojo.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Insert("INSERT INTO auction_order (item_id, user_id, deal_price, status, update_time) VALUES (#{itemId}, #{userId}, #{dealPrice}, #{status}, #{updateTime})")
    void insertOrder(Order order);

    List<Order> selectOrdersByUserId(Long userId);

    void updateStatusByUserId(Long orderId, Long userId);
}