package com.qkl.auctionsystem.pojo.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private Integer role;
}
