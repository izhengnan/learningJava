package com.itheima.shipment.service;



import com.itheima.shipment.dto.Carrier;
import com.itheima.shipment.dto.LoginInfo;

import java.math.BigInteger;
import java.util.List;

public interface CarrierService {
    boolean addCarrier(String name,String info,String passwd);

    LoginInfo checkCarrier(String name, String passwd) throws Exception;

    List<Carrier> getAllCarrier();

    Carrier getCarrierById(BigInteger id);

    boolean deleteCarrier(BigInteger id);

    boolean updatePassword(BigInteger id, String passwd);
}
