package com.itheima.shipment.service.impl;

import com.itheima.shipment.dto.Carrier;
import com.itheima.shipment.dto.LoginInfo;
import com.itheima.shipment.dto.Shipment;
import com.itheima.shipment.mapper.CarrierMapper;
import com.itheima.shipment.mapper.ShipmentMapper;
import com.itheima.shipment.service.CarrierService;
import com.itheima.shipment.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CarrierServiceImpl implements CarrierService {
    
    @Autowired
    private CarrierMapper carrierMapper;
    
    @Autowired
    private ShipmentMapper shipmentMapper;

    @Override
    public boolean addCarrier(String name, String info, String passwd) {
        try {
            carrierMapper.addCarrier(name, info, passwd);
            return true;
        } catch (Exception e) {
            log.error("添加承运商失败", e);
            return false;
        }
    }

    @Override
    public LoginInfo checkCarrier(String name, String passwd) throws Exception {
        Carrier carrier = carrierMapper.checkCarrier(name, passwd);
        if (carrier != null) {
            // 使用JwtUtils生成JWT令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", carrier.getId());
            claims.put("name", carrier.getName());
            claims.put("userType", "carrier");
            
            String token = JwtUtils.generateJwt(claims);
            return new LoginInfo(token, carrier.getName(), null, carrier.getId(), "carrier");
        }
        return null;
    }

    @Override
    public List<Carrier> getAllCarrier() {
        try {
            return carrierMapper.getAllCarrier();
        } catch (Exception e) {
            log.error("获取所有承运商失败", e);
            return List.of();
        }
    }

    @Override
    public Carrier getCarrierById(BigInteger id) {
        try {
            return carrierMapper.getCarrierById(id);
        } catch (Exception e) {
            log.error("根据ID获取承运商失败", e);
            return null;
        }
    }

    @Override
    public boolean deleteCarrier(BigInteger id) {
        try {
            int result = carrierMapper.deleteCarrier(id);
            return result > 0;
        } catch (Exception e) {
            log.error("删除承运商失败", e);
            return false;
        }
    }

    @Override
    public boolean updatePassword(BigInteger id, String passwd) {
        try {
            int result = carrierMapper.updatePassword(id, passwd);
            return result > 0;
        } catch (Exception e) {
            log.error("更新承运商密码失败", e);
            return false;
        }
    }
}