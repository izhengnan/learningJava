package com.sky.mapper;

import com.sky.dto.UserPageLoginDTO;
import com.sky.entity.User;
import com.sky.entity.UserPage;
import com.sky.entity.UserRegister;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select * from user where openid = #{openid}")
    User getByOpenId(String openid);

    void insert(User user);

    @Select("select * from user where name = #{name} AND password = #{password} ")
    UserPage login(UserPageLoginDTO userPageLoginDTO);

    @Insert("insert into user(name,password,create_time) values(#{name},#{password},#{createTime})")
    void Register(UserRegister userRegister);

    @Select("select * from user where id = #{id}")
    UserPage getUserMessage(Long id);

    @Update("update user set avatar = #{avatar} where id = #{id}")
    void setAvator(UserPage userPage);
}
