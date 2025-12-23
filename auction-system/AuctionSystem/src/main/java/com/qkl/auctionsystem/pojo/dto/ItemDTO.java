package com.qkl.auctionsystem.pojo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ItemDTO {
    Long id;
    String title;
    String image;
    Long initialPrice;
    String description;
    LocalDateTime startTime;
    LocalDateTime endTime;
}