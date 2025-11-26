package com.itheima.shipment.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static String signKey = "5Yy65Z2X6ZO+5oqA5pyv5bqU55So6Iiq6L+Q54mp5rWB57O757uf";//区块链技术应用航运物流系统 base64编码
    private static Long expire = 43200000L;

    /**
     * 生成JWT令牌
     */
    public static String generateJwt(Map<String,Object> claims){
        return Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, signKey)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
    }

    /**
     * 解析JWT令牌
     * @param jwt JWT令牌
     */
    public static Claims parseToken(String jwt){
        return Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
