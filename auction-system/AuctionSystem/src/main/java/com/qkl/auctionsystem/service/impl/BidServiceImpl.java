package com.qkl.auctionsystem.service.impl;

import com.qkl.auctionsystem.filter.TokenFilter;
import com.qkl.auctionsystem.mapper.BidRecordMapper;
import com.qkl.auctionsystem.pojo.dto.BidDTO;
import com.qkl.auctionsystem.pojo.dto.BidRecordDTO;
import com.qkl.auctionsystem.pojo.entity.BidRecord;
import com.qkl.auctionsystem.pojo.entity.Item;
import com.qkl.auctionsystem.service.BidService;
import com.qkl.auctionsystem.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BidServiceImpl implements BidService {
    @Autowired
    private ItemService itemService;
    
    @Autowired
    private BidRecordMapper bidRecordMapper;

    @Override
    public void bidPrice(BidDTO bidDTO) {
        // 获取当前用户ID
        Long userId = TokenFilter.getCurrentUserId();
        
        // 获取拍品信息
        Item item = itemService.selectItemById(bidDTO.getItemId());
        
        // 创建竞拍记录对象
        BidRecord bidRecord = new BidRecord();
        bidRecord.setItemId(bidDTO.getItemId());
        bidRecord.setUserId(userId);
        bidRecord.setBidPrice(new BigDecimal(bidDTO.getBidPrice()));
        bidRecord.setBidTime(LocalDateTime.now());
        
        try {
            // 将竞拍记录保存到数据库
            bidRecordMapper.insert(bidRecord);
            
            // 更新数据库中的最高出价
            itemService.updateCurrentMaxPrice(bidDTO.getItemId(), bidDTO.getBidPrice(), userId);
        } catch (Exception e) {
            throw new RuntimeException("数据库出价记录失败: " + e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> getBidRecords(Long itemId) {
            // 从数据库查询竞拍记录
            List<BidRecord> bidRecords = bidRecordMapper.selectByItemId(itemId);

            // 将BidRecord转换为BidRecordDTO
            List<BidRecordDTO> records = new ArrayList<>();
            for (BidRecord bidRecord : bidRecords) {
                BidRecordDTO record = new BidRecordDTO();
                record.setBidTime(bidRecord.getBidTime().atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli());
                record.setUserId(bidRecord.getUserId());
                record.setBidPrice(bidRecord.getBidPrice().longValue());
                records.add(record);
            }

            // 将结果封装成Map返回
            Map<String, Object> result = new HashMap<>();
            result.put("data", records);
            return result;
    }
}