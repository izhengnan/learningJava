package com.qkl.auctionsystem.mapper;

import com.qkl.auctionsystem.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void userRegister(User user);

    User userLogin(String username);
}
