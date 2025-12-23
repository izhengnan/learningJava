package com.qkl.auctionsystem.controller;


import com.qkl.auctionsystem.result.Result;
import com.qkl.auctionsystem.utils.AliyunOSSOperator;
import com.qkl.auctionsystem.utils.PermissionChecker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/admin/common")
@CrossOrigin
public class CommonController {
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) {
        // 检查文件是否为空
        if (file == null || file.isEmpty()) {
            log.warn("上传文件为空");
            return Result.error("上传文件不能为空");
        }
        
        // 检查是否为管理员
        if (!PermissionChecker.isAdmin()) {
            log.warn("非管理员用户尝试上传文件");
            return Result.error("权限不足，只有管理员才能上传文件");
        }
        
        try {
            log.info("上传文件：{}", file.getOriginalFilename());
            String url = aliyunOSSOperator.upload(file.getBytes(), Objects.requireNonNull(file.getOriginalFilename()));
            //将文件给oss存储管理
            log.info("文件上传成功，返回访问路径：{}", url);
            return Result.success(url);
        } catch (Exception e) {
            log.error("文件上传异常", e);
            return Result.error("文件上传失败: " + e.getMessage());
        }
    }
}
