package com.qkl.auctionsystem.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.qkl.auctionsystem.properties.AliyunOSSProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@Component
public class AliyunOSSOperator {

    @Autowired
    private AliyunOSSProperties aliyunOSSOperator;

    public String upload(byte[] content, String originalFilename) throws Exception {
        String endpoint = aliyunOSSOperator.getEndpoint();
        String region = aliyunOSSOperator.getRegion();
        String bucketName = aliyunOSSOperator.getBucketName();
        String accessKeyId = aliyunOSSOperator.getAccessKeyId();
        String accessKeySecret = aliyunOSSOperator.getAccessKeySecret();

        // 尝试从环境变量或配置文件获取访问凭证
        EnvironmentVariableCredentialsProvider credentialsProvider = null;
        OSS ossClient = null;

        try {
            // 首先优先使用配置文件中的凭证
            if (accessKeyId != null && !accessKeyId.isEmpty() && accessKeySecret != null && !accessKeySecret.isEmpty()) {
                ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
                log.info("使用配置文件中的凭证进行OSS认证");
            } else {
                // 如果配置文件中没有凭证，则使用环境变量
                credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
                ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);
                log.info("使用环境变量进行OSS认证");
            }
        } catch (Exception e) {
            log.error("OSS认证失败: {}", e.getMessage(), e);
            throw new Exception("OSS认证失败：" + e.getMessage(), e);
        }

        // 填写Object完整路径，例如202406/1.png。Object完整路径中不能包含Bucket名称。
        //获取当前系统日期的字符串,格式为 yyyy/MM
        String dir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));
        //生成一个新的不重复的文件名
        String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
        String objectName = dir + "/" + newFileName;

        try {
            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content));
            log.info("文件成功上传到OSS: {}", objectName);
        } finally {
            ossClient.shutdown();
        }

        return endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + objectName;
    }
}
