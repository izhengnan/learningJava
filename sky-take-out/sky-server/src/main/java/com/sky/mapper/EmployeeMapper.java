package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    @Insert("insert into employee (username, name, phone, sex, id_number, create_time, update_time, create_user, update_user, status, password) values (#{username}, #{name}, #{phone}, #{sex}, #{idNumber}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser}, #{status}, #{password})")
    void saveEmployee(Employee employee);

    Page<Employee> selectEmployeeList(EmployeePageQueryDTO employeePageQueryDTO);

    @Update("update employee set status = #{status} where id = #{id}")
    void startOrStopEmployee(Integer status, Long id);

    void updateEmployee(Employee employee);
}
