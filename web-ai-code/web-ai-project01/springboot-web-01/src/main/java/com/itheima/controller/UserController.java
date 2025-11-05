package com.itheima.controller;

import cn.hutool.core.io.IoUtil;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {
//    @RequestMapping("/list")
//    public List<User> list()throws Exception{
//        //加载并读取user.txt文件,获取用户数据
//        //InputStream in = new FileInputStream(new File("src/main/resources/user.txt"));
//        InputStream in =this.getClass().getClassLoader().getResourceAsStream("user.txt");
//        ArrayList<String> lines = IoUtil.readLines(in, StandardCharsets.UTF_8,new ArrayList<>());
//
//        //解析用户信息，封装User对象->list集合
//        List<User> userList = lines.stream().map(line ->{
//            String[] parts = line.split(",");
//            Integer id = Integer.parseInt(parts[0]);
//            String username = parts[1];
//            String password = parts[2];
//            String name = parts[3];
//            Integer age = Integer.parseInt(parts[4]);
//            LocalDateTime updateTime = LocalDateTime.parse(parts[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//            return new User(id,username,password,name,age,updateTime);
//        }).toList();
//
//        //返回数据（json）
//        return userList;
//    }
    @Autowired
    private UserService userService;
@RequestMapping("/list")
public List<User> list()throws Exception{
    List<User> userList = userService.findAll();
    //返回数据（json）
    return userList;
}
}
