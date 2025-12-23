package com.qkl.auctionsystem.controller;

import com.qkl.auctionsystem.pojo.dto.ItemDTO;
import com.qkl.auctionsystem.pojo.dto.ItemPageQueryDTO;
import com.qkl.auctionsystem.pojo.entity.Item;
import com.qkl.auctionsystem.result.PageResult;
import com.qkl.auctionsystem.result.Result;
import com.qkl.auctionsystem.service.ItemService;
import com.qkl.auctionsystem.utils.PermissionChecker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/item")
@Slf4j
@CrossOrigin
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PutMapping("/add")
    public Result addItem(@RequestBody ItemDTO itemDTO){
        // 检查是否为管理员
        if (!PermissionChecker.isAdmin()) {
            return Result.error("权限不足，只有管理员才能添加拍品");
        }
        
        log.info("添加拍品:{}", itemDTO);
        itemService.addItem(itemDTO);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<PageResult> selectItemList(ItemPageQueryDTO itemPageQueryDTO){
        log.info("查询拍品列表:{}", itemPageQueryDTO);
        PageResult pageResult = itemService.selectItemList(itemPageQueryDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/list/admin")
    public Result<PageResult> selectItemListByAdmin(ItemPageQueryDTO itemPageQueryDTO){
        log.info("管理员查询拍品列表:{}", itemPageQueryDTO);
        PageResult pageResult = itemService.selectItemListByAdmin(itemPageQueryDTO);
        return Result.success(pageResult);
    }
    
    @GetMapping("/{itemId}")
    public Result<Item> selectItemById(@PathVariable Long itemId){
        log.info("查询拍品详情:id={}", itemId);
        Item item = itemService.selectItemById(itemId);
        return Result.success(item);
    }
    @DeleteMapping()
    public Result deleteItemByIds(@RequestParam("ids") ArrayList<Long> ids){
        // 检查是否为管理员
        if (!PermissionChecker.isAdmin()) {
            return Result.error("权限不足，只有管理员才能删除拍品");
        }
        
        log.info("删除拍品:{}", ids);
        itemService.deleteItemByIds(ids);
        return Result.success();
    }

    @PutMapping("update")
    public Result updateItem(@RequestBody ItemDTO itemDTO){
        // 检查是否为管理员
        if (!PermissionChecker.isAdmin()) {
            return Result.error("权限不足，只有管理员才能修改拍品");
        }
        
        log.info("更新拍品:{}", itemDTO);
        itemService.updateItem(itemDTO);
        return Result.success();
    }

    @PutMapping("/{listingStatus}")
    public Result updateItemlistingStatusById(@PathVariable Integer listingStatus,Long itemId){
        log.info("更新拍品id为{}的拍品的上架状态为:{}", itemId, listingStatus);
        itemService.updateItemlistingStatusById(itemId, listingStatus);
        return Result.success();
    }
}