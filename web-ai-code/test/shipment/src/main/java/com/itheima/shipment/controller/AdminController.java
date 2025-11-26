package com.itheima.shipment.controller;


import com.itheima.shipment.dto.Admin;
import com.itheima.shipment.dto.CommonResponse;
import com.itheima.shipment.dto.LoginInfo;
import com.itheima.shipment.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @CrossOrigin
    @PostMapping("/add")
    public CommonResponse AddAdmin(@RequestBody Admin admin) {
        boolean res = adminService.addAdmin(admin.getAccount(), admin.getPasswd());
        if (res) {
            return CommonResponse.ok(res);
        }
        return CommonResponse.fail("401", new Exception("添加管理员失败"));
    }

    // 管理员登录验证
    @CrossOrigin
    @PostMapping("/check")
    public CommonResponse CheckAdmin(@RequestBody Admin admin) throws Exception {
        LoginInfo res = adminService.checkAdmin(admin.getAccount(), admin.getPasswd());
        if (res != null) {
            return CommonResponse.ok(res);
        }
        return CommonResponse.fail("401", new Exception("账户或密码错误"));
    }

    // 修改管理员密码
    @CrossOrigin
    @PostMapping("/updatePassword")
    public CommonResponse UpdateAdminPassword(@RequestBody Admin admin) {
        boolean res = adminService.updateAdminPassword(admin.getAccount(), admin.getPasswd());
        if (res) {
            return CommonResponse.ok(res);
        }
        return CommonResponse.fail("401", new Exception("修改管理员密码失败"));
    }

    //查询管理员列表
    @CrossOrigin
    @GetMapping("/list")
    public CommonResponse ListAdmin() {
        List<Admin> listAdmin = adminService.listAdmin();
        if (listAdmin != null) {
            return CommonResponse.ok(listAdmin);
        }
        return CommonResponse.fail("401", new Exception("查询管理员列表失败"));
    }

}