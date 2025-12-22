package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("adminShopController")
@Slf4j
@RequestMapping("/admin/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;


    @PutMapping("/{status}")
    public Result setShopStatus(@PathVariable Integer status){
        log.info("设置店铺营业状态：{}",status);
        shopService.setShopStatus(status);
        return Result.success();
    }

    @GetMapping("/status")
    public Result<Integer> getShopStatus(){
        log.info("查询店铺营业状态");
        return Result.success(shopService.getShopStatus());
    }
}
