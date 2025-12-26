package com.sky.controller.user;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import com.sky.result.Result;
import com.sky.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user/shoppingCart")
@Slf4j
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public Result addShoppingCart(@RequestBody ShoppingCartDTO shoppingCartDTO){
        log.info("添加购物车：{}",shoppingCartDTO);
        shoppingCartService.addShoppingCart(shoppingCartDTO);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<ArrayList<ShoppingCart>> getShoppingCart(){
        log.info("查询购物车");
        ArrayList<ShoppingCart> shoppingCartArrayList = shoppingCartService.getShoppingCart();
        return Result.success(shoppingCartArrayList);
    }

    @DeleteMapping("/clean")
    public Result cleanShoppingCart(){
        log.info("清空购物车");
        shoppingCartService.cleanShoppingCart();
        return Result.success();
    }

    @PostMapping("/sub")
    public Result deleteShoppingCartDishOrSetmeal(@RequestBody ShoppingCartDTO shoppingCartDTO){
        log.info("删除购物车中一个商品：{}",shoppingCartDTO);
        shoppingCartService.deleteShoppingCartDishOrSetmeal(shoppingCartDTO);
        return Result.success();
    }
}
