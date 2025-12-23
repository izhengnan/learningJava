package com.qkl.auctionsystem.pojo.dto;

import lombok.Data;

@Data
public class ItemPageQueryDTO {
    private int page;

    private int pageSize;

    private String title;

    private Long MaxPrice;

    private Long MinPrice;

    private Integer status;
}
