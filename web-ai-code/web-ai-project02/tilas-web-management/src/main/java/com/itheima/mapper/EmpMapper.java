package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpExpr;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmpMapper {

//    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id\n" +
//            "        where e.name like concat('%', #{name}, '%') and e.gender = #{gender}\n" +
//            "        and e.entry_date between #{begin} and #{end}\n" +
//            "        order by e.update_time desc")
    public List<Emp> list(EmpQueryParam empQueryParam);


    @Options(useGeneratedKeys = true,keyProperty = "id")//获取生成的主键--主键返回
    @Insert("insert into emp(username,password,name,gender,phone,image,job,salary,dept_id,entry_date,create_time,update_time) " +
            "values(#{username},#{password},#{name},#{gender},#{phone},#{image},#{job},#{salary},#{deptId},#{entryDate},#{createTime},#{updateTime})")
    void insert(Emp emp);
}
