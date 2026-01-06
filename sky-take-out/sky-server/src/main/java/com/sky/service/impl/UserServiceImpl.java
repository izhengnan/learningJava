package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sky.constant.MessageConstant;
import com.sky.context.BaseContext;
import com.sky.dto.UserLoginDTO;
import com.sky.dto.UserPageLoginDTO;
import com.sky.dto.UserRegisterDTO;
import com.sky.entity.User;
import com.sky.entity.UserPage;
import com.sky.entity.UserRegister;
import com.sky.exception.LoginFailedException;
import com.sky.mapper.UserMapper;
import com.sky.properties.WeChatProperties;
import com.sky.service.UserService;
import com.sky.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    public static final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";
    @Autowired
    private WeChatProperties weChatProperties;
    @Autowired
    private UserMapper userMapper;
    @Override
    public User wxLogin(UserLoginDTO userLoginDTO) {
        String openid = getOpenid(userLoginDTO);

        if (openid == null){
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }
        User user = userMapper.getByOpenId(openid);

        if (user == null){
            user = User.builder()
                    .openid(openid)
                    .createTime(LocalDateTime.now())
                    .build();
            userMapper.insert(user);
        }

        return user;
    }

    @Override
    public UserPage Login(UserPageLoginDTO userPageLoginDTO) {
        UserPage userPage = userMapper.login(userPageLoginDTO);
        if(userPage == null){
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }
        return userPage;
    }

    @Override
    public void Register(UserRegisterDTO userRegisterDTO) {
        UserRegister userRegister = UserRegister.builder()
                .name(userRegisterDTO.getName())
                .password(userRegisterDTO.getPassword())
                .createTime(LocalDateTime.now())
                .build();
        userMapper.Register(userRegister);
    }

    @Override
    public UserPage getUserMessage(Long id) {
        return userMapper.getUserMessage(id);
    }

    @Override
    public void setAvator(UserPage userPage) {
        userPage.setId(BaseContext.getCurrentId());
        userMapper.setAvator(userPage);
    }

    private String getOpenid(UserLoginDTO userLoginDTO) {
        Map<String, String> map=new HashMap<>();
        map.put("appid", weChatProperties.getAppid());
        map.put("secret", weChatProperties.getSecret());
        map.put("js_code", userLoginDTO.getCode());
        map.put("grant_type", "authorization_code");
        String json = HttpClientUtil.doGet(WX_LOGIN_URL, map);
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = jsonObject.getString("openid");
        return openid;
    }
}
