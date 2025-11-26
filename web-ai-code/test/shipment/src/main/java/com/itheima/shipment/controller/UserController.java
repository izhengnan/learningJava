package com.itheima.shipment.controller;


import com.itheima.shipment.dto.CommonResponse;
import com.itheima.shipment.dto.LoginInfo;
import com.itheima.shipment.dto.User;
import com.itheima.shipment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @CrossOrigin
    @PostMapping("/add")
    public CommonResponse AddUser(@RequestBody User user) {
        boolean res = userService.addUser(user.getName(), user.getPasswd());
        if (res) {
            return CommonResponse.ok(res);
        }
        return CommonResponse.fail("401", new Exception("添加用户失败"));
    }

    // 登录
    @CrossOrigin
    @PostMapping("/check")
    public CommonResponse Check(@RequestBody User user) throws Exception {
        LoginInfo loginInfo = userService.checkUser(user.getName(), user.getPasswd());
        if (loginInfo != null) {
            return CommonResponse.ok(loginInfo);
        }
        return CommonResponse.fail("401", new Exception("用户名或密码错误"));
    }
    //查询用户列表
    @CrossOrigin
    @GetMapping("/list")
    public CommonResponse GetAllUser() {
        List<User> listUser = userService.getAllUser();
        if (listUser != null) {
            return CommonResponse.ok(listUser);
        }
        return CommonResponse.fail("401", new Exception("查询用户列表失败"));
    }

    //根据id查询用户列表
    @CrossOrigin
    @GetMapping("/getById")
    public CommonResponse GetUserById(@RequestParam BigInteger id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return CommonResponse.ok(user);
        }
        return CommonResponse.fail("401", new Exception("查询用户列表失败"));
    }

    //根据id删除用户
    @CrossOrigin
    @DeleteMapping("/delete")
    public CommonResponse DeleteUser(@RequestParam BigInteger id) {
        boolean res = userService.deleteUser(id);
        if (res) {
            return CommonResponse.ok(res);
        }
        return CommonResponse.fail("401", new Exception("删除用户失败"));
    }

    //修改用户密码
    @CrossOrigin
    @PostMapping("/updatePassword")
    public CommonResponse UpdatePassword(@RequestBody User user) {
        boolean res = userService.updatePassword(user.getId(), user.getPasswd());
        if (res) {
            return CommonResponse.ok(res);
        }
        return CommonResponse.fail("401", new Exception("修改用户密码失败"));
    }

}
    