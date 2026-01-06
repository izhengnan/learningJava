package com.sky.controller.user;

import com.sky.constant.JwtClaimsConstant;
import com.sky.context.BaseContext;
import com.sky.dto.UserLoginDTO;
import com.sky.dto.UserPageLoginDTO;
import com.sky.dto.UserRegisterDTO;
import com.sky.entity.User;
import com.sky.entity.UserPage;
import com.sky.properties.JwtProperties;
import com.sky.result.Result;
import com.sky.service.UserService;
import com.sky.utils.JwtUtil;
import com.sky.vo.UserLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO){
        log.info("用户微信登录：{}",userLoginDTO);
        User user = userService.wxLogin(userLoginDTO);

        Map<String,Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID,user.getId());
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .openid(user.getOpenid())
                .token(token)
                .build();

        return Result.success(userLoginVO);
    }
    @PostMapping("/loginpage")
    public Result<UserLoginVO> loginpage(@RequestBody UserPageLoginDTO userPageLoginDTO){
        log.info("用户网页登录：{}",userPageLoginDTO);
        UserPage userPage = userService.Login(userPageLoginDTO);
        Map<String,Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID,userPage.getId());
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(userPage.getId())
                .openid(userPage.getName())
                .token(token)
                .build();

        return Result.success(userLoginVO);
    }

    //注册
    @PostMapping("/register")
    public Result register(@RequestBody UserRegisterDTO userRegisterDTO){
        log.info("用户网页注册：{}",userRegisterDTO);
        userService.Register(userRegisterDTO);
        return Result.success();
    }
    //根据用户id查询用户信息
    @PostMapping("/getUserMessage")
    public Result<UserPage> getUserMessage(){
        Long id = BaseContext.getCurrentId();
        log.info("查询用户信息：{}",id);
        UserPage userPage = userService.getUserMessage(id);
        return Result.success(userPage);
    }

    //设置用户头像
    @PostMapping("/setAvator")
    public Result setAvator(@RequestBody UserPage userPage){
        log.info("设置用户头像：{}",userPage);
        userService.setAvator(userPage);
        return Result.success();
    }
}
