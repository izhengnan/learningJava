package com.itheima.service;


import com.itheima.pojo.ClazzStudentNum;
import com.itheima.pojo.EmpGender;
import com.itheima.pojo.JobOption;
import com.itheima.pojo.StudentDegree;

import java.util.List;

public interface CountService {

    JobOption countEmpJob();

    List<EmpGender> CountEmpGender();

    List<StudentDegree> CountStudentDegree();

    ClazzStudentNum CountStudentNum();
}
