package com.qkl.auctionsystem.mapper;

import com.github.pagehelper.Page;
import com.qkl.auctionsystem.pojo.dto.ItemPageQueryDTO;
import com.qkl.auctionsystem.pojo.entity.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ItemMapper {
    void addItem(Item item);

    Page<Item> selectItemList(ItemPageQueryDTO itemPageQueryDTO);
    
    Item selectItemById(Long id);

    void deleteItemByIds(@Param("ids") ArrayList<Long> ids);

    void updateItem(Item item);
    
    List<Item> selectAllItems();
    
    void updateItemStatus(Long id, Integer status);
        
    void updateCurrentMaxPrice(Long itemId, Long currentMaxPrice, Long currentMaxUserId);

    ArrayList<Integer> selectItemListingStatusByIds(ArrayList<Long> ids);

    void updateItemlistingStatusById(Long id, Integer listingStatus);

    Page<Item> selectItemListByAdmin(ItemPageQueryDTO itemPageQueryDTO);
}