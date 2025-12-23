package com.qkl.auctionsystem.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 竞拍记录DTO - 用于接收和返回区块链中的竞拍记录
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BidRecordDTO {
    /**
     * 出价时间（毫秒时间戳）
     */
    private Long bidTime;

    /**
     * 出价用户ID
     */
    private Long userId;

    /**
     * 出价金额
     */
    private Long bidPrice;
}
