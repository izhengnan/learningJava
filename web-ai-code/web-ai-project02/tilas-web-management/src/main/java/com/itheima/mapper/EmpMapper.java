package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmpMapper {

    @Select("select e.*,d.name from emp e left join dept d on e.dept_id = d.id order by e.update_time desc")
    public List<Emp> list();
}
