package com.sky.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")//告诉SpringBoot，这个类是配置类
public class AliyunOSSProperties {
    public String endpoint;
    public String bucketName;
    public String region;
}
