package com.itheima.springbootwebquickstart;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.PrintWriter;

@RestController
public class ResponseController {
    @RequestMapping("/response")
    public void response(HttpServletResponse response){
        //设置响应状态码
        response.setStatus(401);
        //设置响应头
        response.setHeader("name","itheima");
        //设置响应体
        try {
            PrintWriter writer = response.getWriter();
            writer.write("hello response");
            writer.flush();
        } catch (IOException e) {
            // 记录日志或进行适当的错误处理
            e.printStackTrace();
        }
    }

    @RequestMapping("/response2")
    public ResponseEntity<String> response2(){
        return ResponseEntity
                .status(401)
                .header("name","itheima")
                .body("hello response");
    }
}
