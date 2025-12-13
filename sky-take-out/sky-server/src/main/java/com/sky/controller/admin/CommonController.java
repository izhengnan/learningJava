package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/admin/common")
public class CommonController {
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws Exception {
        log.info("上传文件：{}",file.getOriginalFilename());
        String url=aliyunOSSOperator.upload(file.getBytes(), Objects.requireNonNull(file.getOriginalFilename()));
        //将文件给oss存储管理
        log.info("文件上传成功，返回访问路径：{}",url);
        return Result.success(url);
    }
}
