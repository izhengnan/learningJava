package com.qkl.auctionsystem.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BidRecord {
    private Long id;
    private Long itemId;
    private Long userId;
    private BigDecimal bidPrice;
    private LocalDateTime bidTime;
}