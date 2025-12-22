package com.sky.controller.user;

import com.sky.result.Result;
import com.sky.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("userShopController")
@Slf4j
@RequestMapping("/user/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @GetMapping("/status")
    public Result<Integer> getShopStatus(){
        log.info("查询店铺营业状态");
        return Result.success(shopService.getShopStatus());
    }
}
