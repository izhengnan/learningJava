package com.sky.mapper;

import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    @Select("select * from dish where id = #{id}")
    DishVO selectById(Long id);

    @Select("select * from dish_flavor where dish_id = #{dishId}")
    List<DishFlavor> selectDishFlavorsById(Long dishId);

    @Select("select name from category where id = #{categoryId}")
    String selectDishCategoryNameById(Long categoryId);
}
