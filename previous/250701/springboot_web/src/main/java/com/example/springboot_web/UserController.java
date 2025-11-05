// UserController.java
package com.example.springboot_web;

import com.example.springboot_web.mapper.UserMapper;
import com.example.springboot_web.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public Map<String, Object> login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session) {

        Map<String, Object> result = new HashMap<>();

        User user = userMapper.findByUsername(username);

        if (user != null && user.getPassword() != null && user.getPassword().equals(password)) {
            session.setAttribute("user", user);
            result.put("status", "success");
            result.put("message", "登录成功");
            result.put("user_name", user.getUser_name());
            result.put("signature", user.getSignature());
        } else {
            result.put("status", "error");
            result.put("message", "用户名或密码错误");
        }

        return result;
    }

    @PostMapping("/register")
    public Map<String, Object> register(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("userName") String userName) {

        Map<String, Object> result = new HashMap<>();

        try {

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setUser_name(userName);
            user.setSignature("这个人很懒，还没有个性签名");

            if (userMapper.findByUsername(user.getUsername()) != null) {
                result.put("status", "error");
                result.put("message", "用户名已存在");
                return result;
            }

            userMapper.insert(user);//插入数据库
            result.put("status", "success");
            result.put("message", "注册成功");

        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "注册失败：" + e.getMessage());
        }

        return result;
    }

//    @PostMapping("/update-profile")
//    public Map<String, Object> updateProfile(
//            @RequestParam("user_name") String user_name,
//            @RequestParam("signature") String signature) {
//
//        Map<String, Object> result = new HashMap<>();
//
//        try {
//            User user = userMapper.findByUsername(user_name);
//            if (user == null) {
//                result.put("status", "error");
//                result.put("message", "用户不存在");
//                return result;
//            }
//
//            user.setUser_name(user_name);
//            user.setSignature(signature);
//
//            userMapper.update(user);
//
//            result.put("status", "success");
//            result.put("message", "资料更新成功");
//
//        } catch (Exception e) {
//            result.put("status", "error");
//            result.put("message", "更新失败：" + e.getMessage());
//        }
//
//        return result;
//    }
//
}


