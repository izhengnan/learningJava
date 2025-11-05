package com.example.springboot_web.mapper;

import com.example.springboot_web.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users")
    List<User> findAll();

    @Select("SELECT * FROM users WHERE username = #{username}")
    User findByUsername(String username);

    @Insert("INSERT INTO users(username, password, user_name, signature) " +
            "VALUES(#{username}, #{password}, #{user_name}, #{signature})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    @Update("UPDATE users SET user_name = #{user_name}, signature = #{signature} WHERE username = #{username}")
    void update(User user);
}
