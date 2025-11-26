package com.itheima.shipment.controller;


import com.itheima.shipment.dto.CommonResponse;
import com.itheima.shipment.dto.Shipment;
import com.itheima.shipment.dto.UpdateShipment;
import com.itheima.shipment.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/shipment")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    //添加运单
    @CrossOrigin
    @PostMapping("/add")
    public CommonResponse AddShipment(@RequestBody Shipment shipment) {
        boolean res = shipmentService.addShipment(shipment.getUserId(), shipment.getCarrier(), shipment.getFrom(), shipment.getTo());
        if( res) {
            return CommonResponse.ok(res);
        }
        return CommonResponse.fail("401", new Exception("添加运单失败"));
    }
    //根据id删除运单
    @CrossOrigin
    @DeleteMapping("/deleteById")
    public CommonResponse DeleteShipmentById(@RequestParam BigInteger id) {
        boolean res = shipmentService.deleteShipmentById(id);
        if(res) {
            return CommonResponse.ok(res);
        }
        return CommonResponse.fail("401", new Exception("删除运单失败"));
    }

    //更新运单
    @CrossOrigin
    @PutMapping("/update")
    public CommonResponse UpdateShipment(@RequestBody UpdateShipment updateShipment) {
        boolean res = shipmentService.updateShipment(updateShipment.getId(),updateShipment.getCarrierId(),updateShipment.getStatus());
        if(res) {
            return CommonResponse.ok(res);
        }
        return CommonResponse.fail("401", new Exception("更新运单失败"));
    }
    //查询所有运单
    @CrossOrigin
    @GetMapping("/list")
    public CommonResponse GetAllShipment() {
        List<Shipment> listShipment = shipmentService.getAllShipment();
        if (listShipment != null) {
            return CommonResponse.ok(listShipment);
        }
        return CommonResponse.fail("401", new Exception("查询用户列表失败"));

    }
    //根据物流id查询
    @CrossOrigin
    @GetMapping("/getById")
    public CommonResponse GetShipmentById(@RequestParam BigInteger id) {
        Shipment shipment = shipmentService.getShipmentById(id);
        return shipment == null ? CommonResponse.fail("404", new Exception("物流不存在")) : CommonResponse.ok(shipment);
    }

    //根据承运商id查询
    @CrossOrigin
    @GetMapping("/getByCarrierId")
    public CommonResponse GetShipmentByCarrierId(@RequestParam BigInteger carrierId) {
        List<Shipment> listShipment = shipmentService.getShipmentByCarrierId(carrierId);
        return listShipment == null ? CommonResponse.fail("404", new Exception("物流不存在")) : CommonResponse.ok(listShipment);
    }


    //根据物流id查询物流状态
    @CrossOrigin
    @GetMapping("/getStatusById")
    public CommonResponse GetShipmentStatusById(@RequestParam BigInteger id) {
        String status = shipmentService.getShipmentStatusById(id);
        return status == null ? CommonResponse.fail("404", new Exception("物流不存在")) : CommonResponse.ok(status);
    }
    //根据用户id查询物流
    @CrossOrigin
    @GetMapping("/getByUserId")
    public CommonResponse GetShipmentByUserId(@RequestParam BigInteger userId) {
        List<Shipment> listShipment = shipmentService.getShipmentByUserId(userId);
        return listShipment == null ? CommonResponse.fail("404", new Exception("物流不存在")) : CommonResponse.ok(listShipment);
    }
    //根据用户id与承运商id查询物流
    @CrossOrigin
    @GetMapping("/getByUserIdAndCarrierId")
    public CommonResponse GetShipmentByUserIdAndCarrierId(@RequestParam BigInteger userId, @RequestParam BigInteger carrierId) {
        List<Shipment> listShipment = shipmentService.getShipmentByUserIdAndCarrierId(userId, carrierId);
        return listShipment == null ? CommonResponse.fail("404", new Exception("物流不存在")) : CommonResponse.ok(listShipment);
    }

}
