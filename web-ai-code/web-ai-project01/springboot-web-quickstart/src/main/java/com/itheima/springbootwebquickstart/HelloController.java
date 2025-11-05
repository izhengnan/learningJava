package com.itheima.springbootwebquickstart;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//当前类是一个请求处理类
public class HelloController {
    @RequestMapping("/hello")//标识请求路径
    public String Hello(String name){
        System.out.println("name:"+name);
        return "Hello "+name;
    }
}
