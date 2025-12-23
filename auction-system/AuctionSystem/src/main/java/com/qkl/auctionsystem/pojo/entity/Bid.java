package com.qkl.auctionsystem.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bid {
    private Long itemId;
    private Long bidPrice;
    private LocalDateTime bidTime;
    private Long userId;

}
