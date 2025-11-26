package com.itheima.shipment.service.impl;

import com.itheima.shipment.dto.Admin;
import com.itheima.shipment.dto.LoginInfo;
import com.itheima.shipment.mapper.AdminMapper;
import com.itheima.shipment.service.AdminService;
import com.itheima.shipment.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public boolean addAdmin(String account, String passwd) {
        try {
            adminMapper.addAdmin(account, passwd);
            return true;
        } catch (Exception e) {
            log.error("添加管理员失败", e);
            return false;
        }
    }

    @Override
    public LoginInfo checkAdmin(String account, String passwd) throws Exception {
        Admin admin = adminMapper.checkAdmin(account, passwd);
        if (admin != null) {
            // 使用JwtUtils生成JWT令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("account", admin.getAccount());
            claims.put("userType", "admin");
            
            String token = JwtUtils.generateJwt(claims);
            return new LoginInfo(token, null, admin.getAccount(), BigInteger.valueOf(admin.getId()), "admin");
        }
        return null;
    }

    @Override
    public boolean updateAdminPassword(String account, String newPasswd) {
        try {
            int result = adminMapper.updateAdminPassword(account, newPasswd);
            return result > 0;
        } catch (Exception e) {
            log.error("更新管理员密码失败", e);
            return false;
        }
    }

    @Override
    public List<Admin> listAdmin() {
        try {
            return adminMapper.listAdmin();
        } catch (Exception e) {
            log.error("获取管理员列表失败", e);
            return List.of();
        }
    }
}
