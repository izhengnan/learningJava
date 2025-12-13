package com.sky.mapper;

import com.sky.entity.Dish;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    @Insert("insert into dish (id,name,category_id,price,image,description,status,create_time,update_time,create_user,update_user) value (#{id},#{name},#{categoryId},#{prcie},#{image},#{description},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    void addDish(Dish dish);
}
