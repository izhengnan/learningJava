package com.itheima.shipment.mapper;

import com.itheima.shipment.dto.User;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("insert into user(name,passwd) values(#{name},#{passwd})")
    public void addUser(String name, String passwd);

    @Select("select id, name, passwd from user where id = #{id}")
    public User getUserById(BigInteger id);

    @Select("select id, name, passwd from user where name = #{name} and passwd = #{passwd}")
    public User checkUser(String name, String passwd);

    @Select("select id, name, passwd from user")
    public List<User> getAllUsers();

    @Delete("delete from user where id = #{id}")
    public int deleteUser(BigInteger id);

    @Update("update user set passwd = #{passwd} where id = #{id}")
    public int updatePassword(BigInteger id, String passwd);
}
