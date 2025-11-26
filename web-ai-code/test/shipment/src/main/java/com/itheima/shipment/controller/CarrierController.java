package com.itheima.shipment.controller;


import com.itheima.shipment.dto.Carrier;
import com.itheima.shipment.dto.CommonResponse;
import com.itheima.shipment.dto.LoginInfo;
import com.itheima.shipment.service.CarrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/carrier")
public class CarrierController {
    @Autowired
    private CarrierService carrierService;

    //注册
    @CrossOrigin
    @PostMapping("/add")
    public CommonResponse AddCarrier(@RequestBody Carrier carrier){
        boolean res = carrierService.addCarrier(carrier.getName(), carrier.getInfo(), carrier.getPasswd());
        if(res) {
            return CommonResponse.ok(res);
        }
        return CommonResponse.fail("401", new Exception("添加用户失败"));
    }
    //登录
    @CrossOrigin
    @PostMapping("/check")
    public CommonResponse Check(@RequestBody Carrier carrier) throws Exception {
        LoginInfo loginInfo = carrierService.checkCarrier(carrier.getName(), carrier.getPasswd());
        if(loginInfo != null) {
            return CommonResponse.ok(loginInfo);
        }
        return CommonResponse.fail("401", new Exception("用户名或密码错误"));
    }
    //查询承运商列表
    @CrossOrigin
    @GetMapping("/list")
    public CommonResponse GetAllCarrier() {
        List<Carrier> listCarrier = carrierService.getAllCarrier();
        if (listCarrier != null) {
            return CommonResponse.ok(listCarrier);
        }
        return CommonResponse.fail("401", new Exception("查询用户列表失败"));
    }

    //根据id查询承运商
    @CrossOrigin
    @GetMapping("/getById")
    public CommonResponse GetCarrierById(@RequestParam BigInteger id) {
        Carrier carrier = carrierService.getCarrierById(id);
        return carrier == null ? CommonResponse.fail("404", new Exception("承运商不存在")) : CommonResponse.ok(carrier);
    }

    //根据id删除承运商
    @CrossOrigin
    @DeleteMapping("/delete")
    public CommonResponse DeleteCarrier(@RequestParam BigInteger id) {
        boolean res = carrierService.deleteCarrier(id);
        return res ? CommonResponse.ok(res) : CommonResponse.fail("404", new Exception("承运商不存在"));
    }
    //修改承运商密码
    @CrossOrigin
    @PostMapping("/updatePassword")
    public CommonResponse UpdatePassword(@RequestBody Carrier carrier) {
        boolean res = carrierService.updatePassword(carrier.getId(), carrier.getPasswd());
        return res ? CommonResponse.ok(res) : CommonResponse.fail("404", new Exception("承运商不存在"));
    }
}
