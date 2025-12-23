package com.qkl.auctionsystem.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    private Long id; // ID由数据库自增生成
    private Long itemId;
    private Long userId;
    private Long dealPrice;
    private Integer status; // 状态由合约生成
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    private String itemTitle; // 拍品名称，用于前端显示
}