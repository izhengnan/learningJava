package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpLog;
import com.itheima.pojo.EmpExpr;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.EmpLogService;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 员工服务实现类
 */
@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;

    /**
     * 分页查询员工列表
     * @return 分页结果对象，包含总记录数和当前页数据列表
     */
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        // 设置分页参数
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        // 查询员工列表
        List<Emp> empList = empMapper.list(empQueryParam);
        // 转换为Page对象获取分页信息
        Page<Emp> p = (Page<Emp>) empList;
        // 封装分页结果
        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void addEmp(Emp emp) {
        try {
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);
            List<EmpExpr> exprList =emp.getExprList();
            if(!CollectionUtils.isEmpty(exprList)){
                exprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            EmpLog empLog = new EmpLog(null,LocalDateTime.now(),"新增员工:"+emp);
            empLogService.insertLog(empLog);
        }

    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void deleteById(Integer[] array) {
        try {
            if (array == null || array.length == 0) {
                return;
            }
            for (Integer id : array) {
                empMapper.deleteById(id);
                empExprMapper.deleteByEmpId(id);
            }
        } finally {
            EmpLog empLog = new EmpLog(null,LocalDateTime.now(),"删除员工:"+ Arrays.toString(array));
            empLogService.insertLog(empLog);
        }
    }

    @Override
    public Emp SelectById(Integer id) {
        Emp emp = empMapper.SelectById(id);
        emp.setExprList(empExprMapper.selectByEmpId(id));
        return emp;
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);

        empExprMapper.deleteByEmpId(emp.getId());
        if(!CollectionUtils.isEmpty(emp.getExprList())){
            emp.getExprList().forEach(empExpr ->empExpr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(emp.getExprList());
        }

    }

    @Override
    public List<Emp> SelectAll() {
        return empMapper.selectAll();
    }
}
