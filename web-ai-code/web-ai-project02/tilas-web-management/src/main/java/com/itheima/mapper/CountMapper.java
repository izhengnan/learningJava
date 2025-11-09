package com.itheima.mapper;

import com.itheima.pojo.EmpGender;
import com.itheima.pojo.JobOption;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface CountMapper {
    @Select("select (case job when 1 then '班主任' " +
            "when 2 then '讲师' " +
            "when 3 then '学工主管' " +
            "when 4 then '教研主管' " +
            "when 5 then '咨询师' " +
            "else '其他' end) pos, " +
            "count(*) num from emp group by job order by num")
    List<Map<String, Object>> countEmpJob();

    List<EmpGender> CountEmpGender();
}
