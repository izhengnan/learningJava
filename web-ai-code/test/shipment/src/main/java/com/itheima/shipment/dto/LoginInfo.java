package com.itheima.shipment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * 登录信息类，用于存放登录成功后返回的令牌和用户信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfo {
    private String token;          // 令牌
    private String name;           // 用户名（用于货主和承运商）
    private String account;        // 账户名（用于管理员）
    private BigInteger userId;     // 用户ID
    private String userType;       // 用户类型（user/carrier/admin）
}