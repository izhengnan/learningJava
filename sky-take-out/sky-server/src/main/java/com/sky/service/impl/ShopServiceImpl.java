package com.sky.service.impl;

import com.sky.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {
    public static final String KEY ="shop_status";
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void setShopStatus(Integer status) {
        redisTemplate.opsForValue().set(KEY, status);
    }

    @Override
    public Integer getShopStatus() {
        return (Integer)redisTemplate.opsForValue().get(KEY);
    }
}
