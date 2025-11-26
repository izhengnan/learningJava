package com.itheima.shipment.mapper;

import com.itheima.shipment.dto.Shipment;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface ShipmentMapper {

    @Insert("insert into shipment(user_id, carrier, `from`, `to`, status, create_time, update_time) values(#{userId}, #{carrier}, #{from}, #{to}, #{status}, NOW(), NOW())")
    public void addShipment(BigInteger userId, BigInteger carrier, String from, String to, String status);

    @Delete("delete from shipment where id = #{id}")
    public int deleteShipmentById(BigInteger id);

    @Update("update shipment set carrier = #{carrierId}, status = #{status}, update_time = NOW() where id = #{id}")
    public int updateShipment(BigInteger id, BigInteger carrierId, String status);

    @Select("select id, user_id, carrier, `from`, `to`, status, create_time, update_time from shipment")
    public List<Shipment> getAllShipment();

    @Select("select id, user_id, carrier, `from`, `to`, status, create_time, update_time from shipment where id = #{id}")
    public Shipment getShipmentById(BigInteger id);

    @Select("select id, user_id, carrier, `from`, `to`, status, create_time, update_time from shipment where carrier = #{carrierId}")
    public List<Shipment> getShipmentByCarrierId(BigInteger carrierId);

    @Select("select status from shipment where id = #{id}")
    public String getShipmentStatusById(BigInteger id);

    @Select("select id, user_id, carrier, `from`, `to`, status, create_time, update_time from shipment where user_id = #{userId}")
    public List<Shipment> getShipmentByUserId(BigInteger userId);

    @Select("select id, user_id, carrier, `from`, `to`, status, create_time, update_time from shipment where user_id = #{userId} and carrier = #{carrierId}")
    public List<Shipment> getShipmentByUserIdAndCarrierId(BigInteger userId, BigInteger carrierId);
}
