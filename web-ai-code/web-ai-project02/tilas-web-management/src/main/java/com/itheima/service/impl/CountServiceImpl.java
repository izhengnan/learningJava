package com.itheima.service.impl;

import com.itheima.mapper.CountMapper;
import com.itheima.pojo.ClazzStudentNum;
import com.itheima.pojo.EmpGender;
import com.itheima.pojo.JobOption;
import com.itheima.pojo.StudentDegree;
import com.itheima.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CountServiceImpl implements CountService {
    @Autowired
    private CountMapper countMapper;

    @Override
    public JobOption countEmpJob() {
        // 查询员工职位统计结果
        List<Map<String, Object>> list= countMapper.countEmpJob();
        // 提取职位名称列表
        List<Object> jobList =list.stream().map(dataMap->dataMap.get("pos")).toList();
        // 提取人数列表
        List<Object> dataList =list.stream().map(dataMap->dataMap.get("num")).toList();
        // 构造并返回职位选项对象
        return new JobOption(jobList,dataList);
    }

    @Override
    public List<EmpGender> CountEmpGender() {
        return countMapper.CountEmpGender();
    }

    @Override
    public List<StudentDegree> CountStudentDegree() {
        return countMapper.CountStudentDegree();

    }

    @Override
    public ClazzStudentNum CountStudentNum() {
        List<Map<String, Object>> list = countMapper.CountStudentNum();
        List<Object> clazzList = list.stream().map(dataMap -> dataMap.get("clazz")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();
        return new ClazzStudentNum(clazzList, dataList);
    }
}
