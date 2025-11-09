package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface EmpService {
//    PageResult<Emp> page(Integer start, Integer pageSize);

    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void addEmp(Emp emp);

    void deleteById(Integer[] array);

    Emp SelectById(Integer id);

    void update(Emp emp);

    List<Emp> SelectAll();
}
