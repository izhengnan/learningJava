package com.qkl.auctionsystem.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qkl.auctionsystem.mapper.ItemMapper;
import com.qkl.auctionsystem.mapper.OrderMapper;
import com.qkl.auctionsystem.pojo.dto.ItemDTO;
import com.qkl.auctionsystem.pojo.dto.ItemPageQueryDTO;
import com.qkl.auctionsystem.pojo.entity.Item;
import com.qkl.auctionsystem.pojo.entity.Order;
import com.qkl.auctionsystem.result.PageResult;
import com.qkl.auctionsystem.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {
    
    @Autowired
    private ItemMapper itemMapper;
    
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void addItem(ItemDTO itemDTO) {
        Item item = new Item();
        BeanUtils.copyProperties(itemDTO, item);
        item.setCreateTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());
        item.setCurrentMaxPrice(item.getInitialPrice());
        item.setStatus(0); // 默认状态为0（未开始）
        item.setListingStatus(0);//默认上架状态为0（下架中）
        itemMapper.addItem(item);
    }

    @Override
    public PageResult selectItemList(ItemPageQueryDTO itemPageQueryDTO) {
        PageHelper.startPage(itemPageQueryDTO.getPage(), itemPageQueryDTO.getPageSize());
        Page<Item> page = itemMapper.selectItemList(itemPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public Item selectItemById(Long id) {
        return itemMapper.selectItemById(id);
    }

    @Override
    public void deleteItemByIds(ArrayList<Long> ids) {
        ArrayList<Integer> listingStatusList =  itemMapper.selectItemListingStatusByIds(ids);
        for (Integer listingStatus : listingStatusList){
            if (listingStatus == 1) {
                throw new RuntimeException("该拍品已上架，不可删除");
            }
        }
        itemMapper.deleteItemByIds(ids);
    }

    @Override
    public void updateItem(ItemDTO itemDTO) {
        Item item = itemMapper.selectItemById(itemDTO.getId());
        if(item.getListingStatus() == 0) {
            BeanUtils.copyProperties(itemDTO, item);
            item.setUpdateTime(LocalDateTime.now());
            itemMapper.updateItem(item);
        }else{
            throw new RuntimeException("该拍品已上架，不可修改");
        }
    }
    
    @Override
    public List<Item> getAllItems() {
        return itemMapper.selectAllItems();
    }
    
    @Override
    public void updateItemStatus(Long id, Integer status) {
        itemMapper.updateItemStatus(id, status);
    }
    
    @Override
    public void updateCurrentMaxPrice(Long itemId, Long currentMaxPrice, Long currentMaxUserId) {
        itemMapper.updateCurrentMaxPrice(itemId, currentMaxPrice, currentMaxUserId);
    }

    @Override
    public void updateItemlistingStatusById(Long id, Integer listingStatus) {
        itemMapper.updateItemlistingStatusById(id,listingStatus);
    }

    @Override
    public PageResult selectItemListByAdmin(ItemPageQueryDTO itemPageQueryDTO) {
        PageHelper.startPage(itemPageQueryDTO.getPage(), itemPageQueryDTO.getPageSize());
        Page<Item> page = itemMapper.selectItemListByAdmin(itemPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 定时任务：每分钟检查拍品状态并更新
     * 状态规则：
     * 0 - 未开始
     * 1 - 竞拍中
     * 2 - 已结束
     */
    @org.springframework.scheduling.annotation.Scheduled(cron = "0 * * * * ?")
    public void updateItemStatusScheduled() {
        log.info("开始执行拍品状态更新定时任务");
        
        // 获取所有拍品
        List<Item> items = getAllItems();
        LocalDateTime now = LocalDateTime.now();
        
        for (Item item : items) {
            // 只处理状态为0（未开始）或1（竞拍中）的拍品
            if (item.getStatus() == 0 || item.getStatus() == 1) {
                // 如果当前时间在拍品开始时间与结束时间之间，则将拍品状态改为1（竞拍中）
                if (now.isAfter(item.getStartTime()) && now.isBefore(item.getEndTime())) {
                    if (item.getStatus() != 1) {
                        log.info("更新拍品ID {} 状态为竞拍中", item.getId());
                        updateItemStatus(item.getId(), 1);
                    }
                }
                // 如果当前时间大于结束时间，则将拍品状态改为2（已结束）
                else if (now.isAfter(item.getEndTime())) {
                    if (item.getStatus() != 2) {
                        log.info("更新拍品ID {} 状态为已结束", item.getId());
                        updateItemStatus(item.getId(), 2);
                        
                        // 生成订单
                        generateOrderForItem(item);
                    }
                }
                // 如果当前时间小于开始时间，保持状态为0（未开始）
            }
        }
        
        log.info("拍品状态更新定时任务执行完成");
    }
    
    /**
     * 为拍品生成订单
     * @param item 拍品信息
     */
    private void generateOrderForItem(Item item) {
        try {
            // 检查是否有最高出价用户
            if (item.getCurrentMaxUserId() != null && item.getCurrentMaxPrice() != null) {
                // 有最高出价用户，创建待付款订单
                
                // 创建订单对象（ID由数据库自增生成）
                Order order = Order.builder()
                        .itemId(item.getId())
                        .userId(item.getCurrentMaxUserId())
                        .dealPrice(item.getCurrentMaxPrice())
                        .status(0) // 新订单状态为0-待付款
                        .updateTime(LocalDateTime.now())
                        .build();
                
                try {
                    // 保存到数据库
                    orderMapper.insertOrder(order);
                    
                    log.info("为拍品ID {} 生成订单并保存到数据库成功", item.getId());
                } catch (Exception e) {
                    log.error("为拍品ID {} 生成订单失败", item.getId(), e);
                    throw new RuntimeException("订单生成失败: " + e.getMessage(), e);
                }
            }
        } catch (Exception e) {
            log.error("为拍品ID {} 生成订单失败", item.getId(), e);
        }
    }
}