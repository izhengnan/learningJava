package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.dto.UserPageLoginDTO;
import com.sky.dto.UserRegisterDTO;
import com.sky.entity.User;
import com.sky.entity.UserPage;

public interface UserService {
    User wxLogin(UserLoginDTO userLoginDTO);

    UserPage Login(UserPageLoginDTO userPageLoginDTO);

    void Register(UserRegisterDTO userRegisterDTO);

    UserPage getUserMessage(Long id);

    void setAvator(UserPage userPage);
}
