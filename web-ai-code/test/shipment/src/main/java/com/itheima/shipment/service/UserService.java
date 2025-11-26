package com.itheima.shipment.service;


import com.itheima.shipment.dto.LoginInfo;
import com.itheima.shipment.dto.User;

import java.math.BigInteger;
import java.util.List;

public interface UserService {
    //注册
    boolean addUser(String name,String passwd);
    //验证并返回令牌信息
    LoginInfo checkUser(String name, String passwd) throws Exception;

    List<User> getAllUser();

    User getUserById(BigInteger id);

    boolean deleteUser(BigInteger id);

    boolean updatePassword(BigInteger id, String passwd);
}
