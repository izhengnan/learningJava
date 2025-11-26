package com.itheima.shipment.mapper;

import com.itheima.shipment.dto.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper {

    @Insert("insert into admin(account,passwd) values(#{account},#{passwd})")
    public void addAdmin(String account, String passwd);

    @Select("select id, account, passwd from admin where account = #{account}")
    public Admin getAdminByAccount(String account);

    @Select("select id, account, passwd from admin where account = #{account} and passwd = #{passwd}")
    public Admin checkAdmin(String account, String passwd);

    @Select("select account, passwd from admin")
    public List<Admin> listAdmin();

    @Update("update admin set passwd = #{newPasswd} where account = #{account}")
    public int updateAdminPassword(String account, String newPasswd);
}
