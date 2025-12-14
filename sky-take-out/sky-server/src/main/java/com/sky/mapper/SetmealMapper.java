package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

@Mapper
public interface SetmealMapper {

    /**
     * 根据分类id查询套餐的数量
     * @param id
     * @return
     */
    @Select("select count(id) from setmeal where category_id = #{categoryId}")
    Integer countByCategoryId(Long id);

    void addSetmeal(Setmeal setmeal);

    Page<SetmealVO> selectSetmealList(SetmealPageQueryDTO setmealPageQueryDTO);

    void deleteSetmealList(ArrayList<Long> ids);

    SetmealVO selectSetmealById(Long id);

    void updateSetmeal(Setmeal setmeal);

    @Update("update setmeal set status=#{status} where id = #{id}")
    void startOrStopSetmeal(Integer status, Long id);
}
