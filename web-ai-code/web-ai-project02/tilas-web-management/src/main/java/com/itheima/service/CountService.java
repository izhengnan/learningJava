package com.itheima.service;


import com.itheima.pojo.EmpGender;
import com.itheima.pojo.JobOption;

import java.util.List;

public interface CountService {

    JobOption countEmpJob();

    List<EmpGender> CountEmpGender();
}
