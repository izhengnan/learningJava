package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageResult;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工服务实现类
 */
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    /**
     * 分页查询员工列表
     * @param page 当前页码
     * @param pageSize 每页显示条数
     * @return 分页结果对象，包含总记录数和当前页数据列表
     */
    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        // 设置分页参数
        PageHelper.startPage(page,pageSize);
        // 查询员工列表
        List<Emp> empList = empMapper.list();
        // 转换为Page对象获取分页信息
        Page<Emp> p = (Page<Emp>) empList;
        // 封装分页结果
        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }
}
