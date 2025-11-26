package com.itheima.shipment.service;


import com.itheima.shipment.dto.Shipment;

import java.math.BigInteger;
import java.util.List;

public interface ShipmentService {
    boolean addShipment(BigInteger userId, BigInteger carrier, String from, String to);

    boolean deleteShipmentById(BigInteger id);

    boolean updateShipment(BigInteger id, BigInteger carrierId, String status);

    List<Shipment> getAllShipment();

    Shipment getShipmentById(BigInteger id);

    List<Shipment> getShipmentByCarrierId(BigInteger carrierId);

    String getShipmentStatusById(BigInteger id);

    List<Shipment> getShipmentByUserId(BigInteger userId);

    List<Shipment> getShipmentByUserIdAndCarrierId(BigInteger userId, BigInteger carrierId);
}
