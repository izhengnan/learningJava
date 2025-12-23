package com.qkl.auctionsystem.mapper;

import com.qkl.auctionsystem.pojo.entity.BidRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BidRecordMapper {
    

    @Insert("INSERT INTO auction_record(item_id, user_id, bid_price, bid_time) VALUES(#{itemId}, #{userId}, #{bidPrice}, #{bidTime})")
    void insert(BidRecord bidRecord);

    @Select("SELECT id, item_id AS itemId, user_id AS userId, bid_price AS bidPrice, bid_time AS bidTime FROM auction_record WHERE item_id = #{itemId} ORDER BY bid_time DESC")
    List<BidRecord> selectByItemId(Long itemId);
}