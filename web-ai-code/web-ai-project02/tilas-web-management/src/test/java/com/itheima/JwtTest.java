package com.itheima;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    @Test
    public void testGenerateJwt(){
        Map<String, Object> dataMap =new HashMap<>();
        dataMap.put("id",1);
        dataMap.put("username","admin");
        String compact = Jwts.builder().signWith(SignatureAlgorithm.HS256,"aXRoZWltYQ==")//指定加密算法、密钥
                .addClaims(dataMap)//添加自定义信息
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))//设置过期时间
                .compact();//生成令牌
        System.out.println(compact);
    }

    @Test
    public void testParseJwt(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc2Mjc2MDc4OH0.YGhU7PiEjhuJKXHKTzORZXoXQIEbgJHc0nfK7vF99CU";
        Claims claims = Jwts.parser().setSigningKey("aXRoZWltYQ==")//指定密钥
                .parseClaimsJws(token)//解析令牌
                .getBody();//获取自定义信息
        System.out.println( claims);
    }
}
