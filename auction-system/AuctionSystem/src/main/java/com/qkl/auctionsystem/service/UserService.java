package com.qkl.auctionsystem.service;


import com.qkl.auctionsystem.pojo.dto.UserDTO;
import com.qkl.auctionsystem.pojo.entity.User;

public interface UserService {
    void userRegister(UserDTO userDTO);

    User userLogin(UserDTO userDTO);
    
    User adminLogin(UserDTO userDTO);
}
