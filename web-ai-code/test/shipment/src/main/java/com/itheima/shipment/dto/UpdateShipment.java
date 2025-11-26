package com.itheima.shipment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateShipment {
    private BigInteger id;
    private BigInteger carrierId;
    private String status;
}
