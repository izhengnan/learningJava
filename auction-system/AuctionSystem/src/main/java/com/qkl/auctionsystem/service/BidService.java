package com.qkl.auctionsystem.service;


import com.qkl.auctionsystem.pojo.dto.BidDTO;

import java.util.Map;

public interface BidService {
    void bidPrice(BidDTO bidDTO);

    Map<String, Object> getBidRecords(Long itemId);
}
