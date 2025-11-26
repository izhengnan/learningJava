package com.itheima.shipment.service.impl;

import com.itheima.shipment.dto.LoginInfo;
import com.itheima.shipment.dto.User;
import com.itheima.shipment.mapper.UserMapper;
import com.itheima.shipment.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.itheima.shipment.utils.JwtUtils;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean addUser(String name, String passwd) {
        try {
            userMapper.addUser(name, passwd);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public LoginInfo checkUser(String name, String passwd) throws Exception {
        User user = userMapper.checkUser(name, passwd);
        if (user != null) {
            // 使用JwtUtils生成JWT令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", user.getId());
            claims.put("name", user.getName());
            claims.put("userType", "user");
            
            String token = JwtUtils.generateJwt(claims);
            return new LoginInfo(token, user.getName(), null, user.getId(), "user");
        }
        return null;
    }

    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUsers();
    }

    @Override
    public User getUserById(BigInteger id) {
        return userMapper.getUserById(id);
    }

    @Override
    public boolean deleteUser(BigInteger id) {
        try {
            int result = userMapper.deleteUser(id);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updatePassword(BigInteger id, String passwd) {
        try {
            int result = userMapper.updatePassword(id, passwd);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
