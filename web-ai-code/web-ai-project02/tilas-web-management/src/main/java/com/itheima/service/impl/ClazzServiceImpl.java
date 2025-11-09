package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ClazzMapper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.*;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    public ClazzMapper clazzMapper;
    @Autowired
    public StudentMapper studentMapper;
    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        PageHelper.startPage(clazzQueryParam.getPage(),clazzQueryParam.getPageSize());
        List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);
        clazzList.forEach(clazz -> {
                if (clazz.getEndDate().isBefore(LocalDate.now())){
                    clazz.setStatus("已结课");
                }else if (clazz.getBeginDate().isAfter(LocalDate.now())){
                    clazz.setStatus("未开班");
                }else {
                    clazz.setStatus("在读");
                }
            }
        );
        Page<Clazz> p = (Page<Clazz>) clazzList;
        return new PageResult<Clazz>(p.getTotal(),p.getResult());
    }

    @Override
    public void addClazz(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }

    @Override
    public Clazz selectById(Integer id) {
        return clazzMapper.selectById(id);
    }

    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

    @Override
    public void deleteById(Integer id) {
        StudentQueryParam studentQueryParam = new StudentQueryParam();
        studentQueryParam.setClazzId(id);
        List<Student> studentList = studentMapper.list(studentQueryParam);
        if (studentList.isEmpty()){
            clazzMapper.deleteById(id);
        }else{
            throw new RuntimeException("该班级下有学生，不能删除");
        }

    }

    @Override
    public List<Clazz> selectAll() {
        return clazzMapper.selectAll();

    }
}
