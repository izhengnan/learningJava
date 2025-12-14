package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
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

    void addDish(Dish dish);

    @Select("select * from dish where id = #{id}")
    DishVO selectById(Long id);

    @Select("select * from dish_flavor where dish_id = #{dishId}")
    List<DishFlavor> selectDishFlavorsById(Long dishId);

    @Select("select name from category where id = #{categoryId}")
    String selectDishCategoryNameById(Long categoryId);

    Page<DishVO> selectDishList(DishPageQueryDTO dishPageQueryDTO);

    void updateDish(Dish dish);

    void deleteDishList(ArrayList<Long> id);

    void startOrStopDish(Dish dish);

    List<DishVO> selectDishListByCategoryId(Long categoryId);

    ArrayList<Integer> selectStatusById(ArrayList<Long> dishId);
}
