package com.qkl.auctionsystem.utils;


import com.qkl.auctionsystem.filter.TokenFilter;

/**
 * 权限检查工具类
 */
public class PermissionChecker {

    /**
     * 检查当前用户是否为管理员
     * @return 是否为管理员
     */
    public static boolean isAdmin() {
        Integer role = TokenFilter.getCurrentUserRole();
        return role != null && role == 0;
    }

    /**
     * 检查当前用户是否为普通用户
     * @return 是否为普通用户
     */
    public static boolean isUser() {
        Integer role = TokenFilter.getCurrentUserRole();
        return role != null && role == 1;
    }

    /**
     * 检查用户是否已登录
     * @return 是否已登录
     */
    public static boolean isLoggedIn() {
        return TokenFilter.getCurrentUserId() != null;
    }
}