package com.itheima.shipment.service.impl;

import com.itheima.shipment.dto.Shipment;
import com.itheima.shipment.mapper.ShipmentMapper;
import com.itheima.shipment.service.ShipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@Slf4j
public class ShipmentServiceImpl implements ShipmentService {
    
    @Autowired
    private ShipmentMapper shipmentMapper;

    @Override
    public boolean addShipment(BigInteger userId, BigInteger carrier, String from, String to) {
        try {
            String status = "pending";
            shipmentMapper.addShipment(userId, carrier, from, to, status);
            return true;
        } catch (Exception e) {
            log.error("添加运输单失败", e);
            return false;
        }
    }

    @Override
    public boolean deleteShipmentById(BigInteger id) {
        try {
            int result = shipmentMapper.deleteShipmentById(id);
            return result > 0;
        } catch (Exception e) {
            log.error("删除运输单失败", e);
            return false;
        }
    }

    @Override
    public boolean updateShipment(BigInteger id, BigInteger carrierId, String status) {
        try {
            int result = shipmentMapper.updateShipment(id, carrierId, status);
            return result > 0;
        } catch (Exception e) {
            log.error("更新运输单失败", e);
            return false;
        }
    }

    @Override
    public List<Shipment> getAllShipment() {
        try {
            return shipmentMapper.getAllShipment();
        } catch (Exception e) {
            log.error("获取所有运输单失败", e);
            return List.of();
        }
    }

    @Override
    public Shipment getShipmentById(BigInteger id) {
        try {
            return shipmentMapper.getShipmentById(id);
        } catch (Exception e) {
            log.error("根据ID获取运输单失败", e);
            return null;
        }
    }

    @Override
    public List<Shipment> getShipmentByCarrierId(BigInteger carrierId) {
        try {
            return shipmentMapper.getShipmentByCarrierId(carrierId);
        } catch (Exception e) {
            log.error("根据承运商ID获取运输单失败", e);
            return List.of();
        }
    }

    @Override
    public String getShipmentStatusById(BigInteger id) {
        try {
            return shipmentMapper.getShipmentStatusById(id);
        } catch (Exception e) {
            log.error("获取运输单状态失败", e);
            return "";
        }
    }

    @Override
    public List<Shipment> getShipmentByUserId(BigInteger userId) {
        try {
            return shipmentMapper.getShipmentByUserId(userId);
        } catch (Exception e) {
            log.error("根据用户ID获取运输单失败", e);
            return List.of();
        }
    }

    @Override
    public List<Shipment> getShipmentByUserIdAndCarrierId(BigInteger userId, BigInteger carrierId) {
        try {
            return shipmentMapper.getShipmentByUserIdAndCarrierId(userId, carrierId);
        } catch (Exception e) {
            log.error("根据用户ID和承运商ID获取运输单失败", e);
            return List.of();
        }
    }
}
