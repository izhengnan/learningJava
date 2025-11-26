package com.itheima.shipment.service;

import com.itheima.shipment.dto.Admin;
import com.itheima.shipment.dto.LoginInfo;

import java.util.List;

public interface AdminService {
    //添加管理员
    boolean addAdmin(String account, String passwd);
    //验证管理员登录
    LoginInfo checkAdmin(String account, String passwd) throws Exception;
    //修改管理员密码
    boolean updateAdminPassword(String account, String newPasswd);

    List<Admin> listAdmin();
}