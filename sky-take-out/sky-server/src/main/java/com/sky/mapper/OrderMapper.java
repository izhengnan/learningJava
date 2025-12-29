package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.OrderDetail;
import com.sky.entity.Orders;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Mapper
public interface OrderMapper {
    Long submitOrder(Orders orders);

    /**
     * 根据订单号查询订单
     * @param orderNumber
     */
    @Select("select * from orders where number = #{orderNumber}")
    Orders getByNumber(String orderNumber);

    /**
     * 修改订单信息
     * @param orders
     */
    void update(Orders orders);

    List<Orders> selectHistoryOrders(OrdersPageQueryDTO ordersPageQueryDTO);

    Orders selectOrderDetail(Long id);

    Page<OrderVO> conditionSearchOrder(OrdersPageQueryDTO ordersPageQueryDTO);

    List<Integer> orderStatistics();

    List<Orders> getByStatusAndOrderTime(Integer status, LocalDateTime orderTime);

    void updateList(List<Orders> ordersList);
}
