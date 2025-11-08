package com.itheima.controller;


import com.itheima.pojo.Result;
import com.itheima.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RequestMapping("/upload")
@RestController
public class UploadController {

//    @PostMapping 本地磁盘存储
//    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
//        log.info("上传文件：{}，{}，{}",name,age,file);
//        String originalFilename = file.getOriginalFilename();//获取原始文件名
//        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));//获取文件后缀
//        String newFileName = UUID.randomUUID().toString() + extension;//生成新的文件名
//
//        file.transferTo(new File("D:/images/"+newFileName));//保存文件
//        return Result.success();
//
//    }
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;
    @PostMapping
    public Result upload(MultipartFile file) throws Exception {
        log.info("上传文件：{}",file.getOriginalFilename());
        String url=aliyunOSSOperator.upload(file.getBytes(),Objects.requireNonNull(file.getOriginalFilename()));
        //将文件给oss存储管理
        log.info("文件上传成功，返回访问路径：{}",url);
        return Result.success(url);

    }


}
