package com.itheima.shipment.mapper;

import com.itheima.shipment.dto.Carrier;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface CarrierMapper {

    @Insert("insert into carrier(name,info,passwd) values(#{name},#{info},#{passwd})")
    public void addCarrier(String name, String info, String passwd);

    @Select("select id, name, info, passwd from carrier where name = #{name} and passwd = #{passwd}")
    public Carrier checkCarrier(String name, String passwd);

    @Select("select id, name, info, passwd from carrier where id = #{id}")
    public Carrier getCarrierById(BigInteger id);

    @Select("select id, name, info, passwd from carrier")
    public List<Carrier> getAllCarrier();

    @Delete("delete from carrier where id = #{id}")
    public int deleteCarrier(BigInteger id);

    @Update("update carrier set passwd = #{passwd} where id = #{id}")
    public int updatePassword(BigInteger id, String passwd);
}
