package com.itheima.springbootwebquickstart;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {
    @RequestMapping("/request")
    public String request(HttpServletRequest request){
        // 获取请求方式
        String method = request.getMethod();
        System.out.println("请求方式："+method);
        //获取请求url地址
        String url = request.getRequestURL().toString();//http://localhost:8080/request
        System.out.println("请求url地址为："+url);
        String uri = request.getRequestURI();
        System.out.println("请求uri地址为："+uri);
        //获取请求协议
        String protocol = request.getProtocol();
        System.out.println("请求协议为："+protocol);
        //获取请求参数-name，age
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        System.out.println("参数name为："+name+"，age为："+age);
        //获取请求头-Accept
        String accept = request.getHeader("Accept");
        System.out.println("请求头Accept为："+accept);
        return "ok";
    }

}
