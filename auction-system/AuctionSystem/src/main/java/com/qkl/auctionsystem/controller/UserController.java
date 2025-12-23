package com.qkl.auctionsystem.controller;

import com.qkl.auctionsystem.pojo.dto.UserDTO;
import com.qkl.auctionsystem.pojo.entity.User;
import com.qkl.auctionsystem.result.Result;
import com.qkl.auctionsystem.service.UserService;
import com.qkl.auctionsystem.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result userRegister(@RequestBody UserDTO userDTO) {
        log.info("注册:{}", userDTO);
        userService.userRegister(userDTO);
        return Result.success();
    }

    @PostMapping("/login")
    public Result userLogin(@RequestBody UserDTO userDTO) {
        log.info("登录:{}", userDTO);
        User user = userService.userLogin(userDTO);
            
        // 生成JWT令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("role", user.getRole()); // 添加角色信息到token中
        String token = JwtUtils.generateJwt(claims);
            
        // 创建返回数据对象，包含用户ID、角色和令牌
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("id", user.getId());
        responseData.put("role", user.getRole());
        responseData.put("token", token);
        log.info("登录成功:{}", responseData);
        return Result.success(responseData);
    }
}