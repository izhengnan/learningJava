package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.context.BaseContext;
import com.sky.dto.*;
import com.sky.entity.*;
import com.sky.exception.AddressBookBusinessException;
import com.sky.exception.OrderBusinessException;
import com.sky.exception.ShoppingCartBusinessException;
import com.sky.mapper.*;
import com.sky.result.PageResult;
import com.sky.service.OrderService;
import com.sky.utils.WeChatPayUtil;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;
import com.sky.webSocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private AddressBookMapper addressBookMapper;
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private ShoppingCartServiceImpl shoppingCartService;
    @Autowired
    private WeChatPayUtil weChatPayUtil;
    @Autowired
    private WebSocketServer webSocketServer;


    @Transactional
    @Override
    public OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO) {
        AddressBook addressBook = addressBookMapper.getById(ordersSubmitDTO.getAddressBookId());
        if(addressBook==null){
            throw new AddressBookBusinessException(MessageConstant.ADDRESS_BOOK_IS_NULL);
        }
        ArrayList<ShoppingCart> shoppingCartArrayList = shoppingCartMapper.getShoppingCart(BaseContext.getCurrentId());
        if(shoppingCartArrayList==null|| shoppingCartArrayList.isEmpty()){
            throw new ShoppingCartBusinessException(MessageConstant.SHOPPING_CART_IS_NULL);
        }

        Orders orders = new Orders();
        BeanUtils.copyProperties(ordersSubmitDTO,orders);
        orders.setNumber(String.valueOf(System.currentTimeMillis()));
        orders.setStatus(Orders.PENDING_PAYMENT);
        orders.setUserId(BaseContext.getCurrentId());
        orders.setOrderTime(LocalDateTime.now());
        orders.setPayStatus(Orders.UN_PAID);
        orders.setPhone(addressBook.getPhone());
        orders.setConsignee(addressBook.getConsignee());
        orders.setAddress(addressBook.getProvinceName()+addressBook.getCityName()+addressBook.getDistrictName()+addressBook.getDetail());
        Long id = orderMapper.submitOrder(orders);

        ArrayList<OrderDetail> orderDetailArrayList = new ArrayList<>();
        for (ShoppingCart shoppingCart : shoppingCartArrayList) {
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(shoppingCart,orderDetail);
            orderDetail.setOrderId(orders.getId());
            orderDetailArrayList.add(orderDetail);
        }
        orderDetailMapper.addOrderDetail(orderDetailArrayList);

        OrderSubmitVO orderSubmitVO = OrderSubmitVO.builder()
                .id(id)
                .orderNumber(orders.getNumber())
                .orderAmount(orders.getAmount())
                .orderTime(orders.getOrderTime())
                .build();
        shoppingCartService.cleanShoppingCart();

        return orderSubmitVO;
    }

    /**
     * 订单支付
     *
     * @param ordersPaymentDTO
     * @return
     */
    public OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception {
//        // 当前登录用户id
//        Long userId = BaseContext.getCurrentId();
//        User user = userMapper.getById(userId);
//
//        //调用微信支付接口，生成预支付交易单
//        JSONObject jsonObject = weChatPayUtil.pay(
//                ordersPaymentDTO.getOrderNumber(), //商户订单号
//                new BigDecimal(0.01), //支付金额，单位 元
//                "苍穹外卖订单", //商品描述
//                user.getOpenid() //微信用户的openid
//        );
//
//        if (jsonObject.getString("code") != null && jsonObject.getString("code").equals("ORDERPAID")) {
//            throw new OrderBusinessException("该订单已支付");
//        }
//
//        OrderPaymentVO vo = jsonObject.toJavaObject(OrderPaymentVO.class);
//        vo.setPackageStr(jsonObject.getString("package"));
//
//        return vo;
        log.info("跳过支付");
        paySuccess(ordersPaymentDTO.getOrderNumber());
        return new OrderPaymentVO();
    }

    /**
     * 支付成功，修改订单状态
     *
     * @param outTradeNo
     */
    public void paySuccess(String outTradeNo) {

        // 根据订单号查询订单
        Orders ordersDB = orderMapper.getByNumber(outTradeNo);

        // 根据订单id更新订单的状态、支付方式、支付状态、结账时间
        Orders orders = Orders.builder()
                .id(ordersDB.getId())
                .status(Orders.TO_BE_CONFIRMED)
                .payStatus(Orders.PAID)
                .checkoutTime(LocalDateTime.now())
                .build();

        orderMapper.update(orders);
        //通过Websocket推送消息 type orderId content
        Map map=new HashMap();
        map.put("type",1);
        map.put("orderId",ordersDB.getId());
        map.put("content","订单号:"+outTradeNo);

        String json = JSONObject.toJSONString(map);
        webSocketServer.sendToAllClient(json);
    }

    @Override
    public PageResult selectHistoryOrders(OrdersPageQueryDTO ordersPageQueryDTO) {
        //TODO 1+N次查询问题待解决
        PageHelper.startPage(ordersPageQueryDTO.getPage(),ordersPageQueryDTO.getPageSize());
        ordersPageQueryDTO.setUserId(BaseContext.getCurrentId());
        Page<Orders> ordersPage = (Page<Orders>) orderMapper.selectHistoryOrders(ordersPageQueryDTO);

        List<OrderVO> orderVOList = new ArrayList<>();
        for (Orders orders : ordersPage) {
            OrderVO orderVO = new OrderVO();
            List<OrderDetail> orderDetailList = orderDetailMapper.selectByOrderId(orders.getId());
            BeanUtils.copyProperties(orders, orderVO);
            orderVO.setOrderDetailList(orderDetailList);
            orderVOList.add(orderVO);
        }

        PageResult pageResult = new PageResult();
        pageResult.setTotal(ordersPage.getTotal());
        pageResult.setRecords(orderVOList);

        return pageResult;
    }

    @Override
    public OrderVO selectOrderDetail(Long id) {
//        Long userId = BaseContext.getCurrentId();
        Orders orders = orderMapper.selectOrderDetail(id);
        
        if (orders == null) {
            return null;
        }
        
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(orders, orderVO);

        List<OrderDetail> orderDetailList = orderDetailMapper.selectByOrderId(id);
        orderVO.setOrderDetailList(orderDetailList);
        
        return orderVO;
    }

    @Override
    public void repetitionOrder(Long id) {
        List<OrderDetail> orderDetailList = orderDetailMapper.selectByOrderId(id);
        List<ShoppingCart> shoppingCartList = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetailList) {
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setName(orderDetail.getName());
            shoppingCart.setImage(orderDetail.getImage());
            shoppingCart.setDishId(orderDetail.getDishId());
            shoppingCart.setSetmealId(orderDetail.getSetmealId());
            shoppingCart.setDishFlavor(orderDetail.getDishFlavor());
            shoppingCart.setNumber(orderDetail.getNumber());
            shoppingCart.setAmount(orderDetail.getAmount());
            shoppingCart.setUserId(BaseContext.getCurrentId());
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCartList.add(shoppingCart);
        }
        shoppingCartMapper.addShoppingCartList(shoppingCartList);
    }

    @Override
    public void cancelOrder(Long id) {
//        Long userId = BaseContext.getCurrentId();
        Orders orders = orderMapper.selectOrderDetail(id);
        if(orders.getStatus()>Orders.TO_BE_CONFIRMED){
            throw new OrderBusinessException(MessageConstant.ORDER_STATUS_ERROR);
        }
        if(orders.getStatus().equals(Orders.TO_BE_CONFIRMED)){
            orders.setPayStatus(Orders.REFUND);
        }
        orders.setStatus(Orders.CANCELLED);
        orders.setCancelReason("用户取消");
        orders.setCancelTime(LocalDateTime.now());
        orderMapper.update(orders);
    }

    @Override
    public PageResult conditionSearchOrder(OrdersPageQueryDTO ordersPageQueryDTO) {
        PageHelper.startPage(ordersPageQueryDTO.getPage(),ordersPageQueryDTO.getPageSize());
        PageResult pageResult = new PageResult();
        Page<OrderVO> page = orderMapper.conditionSearchOrder(ordersPageQueryDTO);
        if(page == null){
            throw new OrderBusinessException("未找到相关订单");
        }
        List<OrderVO> orderVOList = page.getResult();
        for (OrderVO orderVO : orderVOList) {
            List<OrderDetail> orderDetailList = orderDetailMapper.selectByOrderId(orderVO.getId());
            StringBuilder orderDishes = new StringBuilder();
            if (orderDetailList != null && !orderDetailList.isEmpty()) {
                for (OrderDetail orderDetail : orderDetailList) {
                    orderDishes.append(orderDetail.getName()).append("*").append(orderDetail.getNumber()).append(" ");
                }
            }
            orderVO.setOrderDishes(orderDishes.toString());
        }
        pageResult.setTotal(page.getTotal());
        pageResult.setRecords(orderVOList);
        return pageResult;
    }

    @Override
    public OrderStatisticsVO orderStatistics() {
        List<Integer> status = orderMapper.orderStatistics();
        OrderStatisticsVO orderStatisticsVO = new OrderStatisticsVO();
        orderStatisticsVO.setToBeConfirmed(0);
        orderStatisticsVO.setConfirmed(0);
        orderStatisticsVO.setDeliveryInProgress(0);
        for (Integer i : status) {
            if(Objects.equals(i, Orders.TO_BE_CONFIRMED)){
                orderStatisticsVO.setToBeConfirmed(orderStatisticsVO.getToBeConfirmed()+1);
            }
            if(Objects.equals(i, Orders.CONFIRMED)){
                orderStatisticsVO.setConfirmed(orderStatisticsVO.getConfirmed()+1);
            }
            if(Objects.equals(i, Orders.DELIVERY_IN_PROGRESS)){
                orderStatisticsVO.setDeliveryInProgress(orderStatisticsVO.getDeliveryInProgress()+1);
            }
        }
        return orderStatisticsVO;
    }

    @Override
    public void confirmOrder(OrdersConfirmDTO ordersConfirmDTO) {
        Orders orders = orderMapper.selectOrderDetail(ordersConfirmDTO.getId());
        if(orders == null){
            throw new OrderBusinessException("未找到该订单");
        }
        if(!Objects.equals(orders.getStatus(), Orders.TO_BE_CONFIRMED)){
            throw new OrderBusinessException("订单状态不为待接单，无法接单");
        }
        Orders updateOrder = new Orders();
        updateOrder.setId(ordersConfirmDTO.getId());
        updateOrder.setStatus(Orders.CONFIRMED);
        orderMapper.update(updateOrder);
    }

    @Override
    public void rejectionOrder(OrdersRejectionDTO ordersRejectionDTO) {
        Orders orders = orderMapper.selectOrderDetail(ordersRejectionDTO.getId());
        if(orders == null){
            throw new OrderBusinessException("未找到该订单");
        }
        if(!Objects.equals(orders.getStatus(), Orders.TO_BE_CONFIRMED)){
            throw new OrderBusinessException("订单状态不为待接单，无法拒单");
        }
        Orders updateOrder = new Orders();
        updateOrder.setId(ordersRejectionDTO.getId());
        updateOrder.setStatus(Orders.CANCELLED);
        updateOrder.setRejectionReason(ordersRejectionDTO.getRejectionReason());
        updateOrder.setCancelTime(LocalDateTime.now());
        orderMapper.update(updateOrder);
    }

    @Override
    public void adminCancelOrder(OrdersCancelDTO ordersCancelDTO) {
        Orders orders = orderMapper.selectOrderDetail(ordersCancelDTO.getId());
        if(orders == null){
            throw new OrderBusinessException("未找到该订单");
        }
        if(Objects.equals(orders.getStatus(), Orders.CANCELLED)
        ){throw new OrderBusinessException("订单已取消");}

        Orders updateOrder = new Orders();
        updateOrder.setId(ordersCancelDTO.getId());
        updateOrder.setStatus(Orders.CANCELLED);
        updateOrder.setCancelReason(ordersCancelDTO.getCancelReason());
        updateOrder.setCancelTime(LocalDateTime.now());
        orderMapper.update(updateOrder);
    }

    @Override
    public void deliveryOrder(Long id) {
        Orders orders = orderMapper.selectOrderDetail(id);
        if(orders == null){
            throw new OrderBusinessException("未找到该订单");
        }
        if(Objects.equals(orders.getStatus(), Orders.CANCELLED)){
            throw new OrderBusinessException("订单状态不为待派送，无法派送");
        }
        Orders updateOrder = new Orders();
        updateOrder.setId(id);
        updateOrder.setStatus(Orders.DELIVERY_IN_PROGRESS);
        orderMapper.update(updateOrder);
    }

    @Override
    public void completeOrder(Long id) {
        Orders orders = orderMapper.selectOrderDetail(id);
        if(orders == null){
            throw new OrderBusinessException("未找到该订单");
        }
        if(Objects.equals(orders.getStatus(), Orders.CANCELLED)){
            throw new OrderBusinessException("订单状态不为派送中，无法完成");
        }
        Orders updateOrder = new Orders();
        updateOrder.setId(id);
        updateOrder.setStatus(Orders.COMPLETED);
        orderMapper.update(updateOrder);
    }
    public void reminder(Long id) {
        // 查询订单是否存在
        Orders orders = orderMapper.getById(id);
        if (orders == null) {
            throw new OrderBusinessException(MessageConstant.ORDER_NOT_FOUND);
        }

        //基于WebSocket实现催单
        Map map = new HashMap();
        map.put("type", 2);//2代表用户催单
        map.put("orderId", id);
        map.put("content", "订单号：" + orders.getNumber());
        webSocketServer.sendToAllClient(JSON.toJSONString(map));
    }
}
