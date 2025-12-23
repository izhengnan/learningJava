package com.qkl.auctionsystem.service;



import com.qkl.auctionsystem.pojo.dto.ItemDTO;
import com.qkl.auctionsystem.pojo.dto.ItemPageQueryDTO;
import com.qkl.auctionsystem.pojo.entity.Item;
import com.qkl.auctionsystem.result.PageResult;

import java.util.ArrayList;
import java.util.List;

public interface ItemService {
    void addItem(ItemDTO itemDTO);

    PageResult selectItemList(ItemPageQueryDTO itemPageQueryDTO);
    
    Item selectItemById(Long id);

    void deleteItemByIds(ArrayList<Long> ids);

    void updateItem(ItemDTO itemDTO);
    
    List<Item> getAllItems();
    
    void updateItemStatus(Long id, Integer status);
        
    void updateCurrentMaxPrice(Long itemId, Long currentMaxPrice, Long currentMaxUserId);

    void updateItemlistingStatusById(Long id, Integer listingStatus);

    PageResult selectItemListByAdmin(ItemPageQueryDTO itemPageQueryDTO);
}