package com.qkl.auctionsystem.controller;

import com.qkl.auctionsystem.mapper.BidRecordMapper;
import com.qkl.auctionsystem.pojo.dto.BidDTO;
import com.qkl.auctionsystem.pojo.dto.BidRecordDTO;
import com.qkl.auctionsystem.pojo.entity.BidRecord;
import com.qkl.auctionsystem.result.Result;
import com.qkl.auctionsystem.service.BidService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/bid")
@CrossOrigin
public class BidController {
    @Autowired
    private BidService bidService;
    
    @Autowired
    private BidRecordMapper bidRecordMapper;

    @PostMapping
    public Result bidPrice(@RequestBody BidDTO bidDTO){
        log.info("用户出价:{}", bidDTO);
        bidService.bidPrice(bidDTO);
        return Result.success();
    }
    
    /**
     * 查询竞拍记录
     * @param itemId 拍品ID
     * @return 竞拍记录列表
     */
    @GetMapping("/records/{itemId}")
    public Result getBidRecords(@PathVariable Long itemId) {
        log.info("查询竞拍记录: itemId={}", itemId);
        Map<String, Object> result = bidService.getBidRecords(itemId);
        return Result.success(result);
    }
}