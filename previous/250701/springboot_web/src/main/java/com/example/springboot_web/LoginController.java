//package com.example.springboot_web;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//
//@RestController
//public class LoginController {
//
//    @Autowired
//    private UserFileReader userFileReader;
//
//    @PostMapping("/login")
//    public String login(@RequestParam String username, @RequestParam String password) {
//        if (userFileReader.validateUser(username, password)) {
//            return "success"; // 登录成功
//        } else {
//            return "用户名或密码错误";
//        }
//    }
//
//    @PostMapping("/register")
//    public String register(@RequestParam String username, @RequestParam String password) {
//        if (userFileReader.validateUser(username, password)) {
//            return "该用户名已被占用";
//        }
//        try {
//            ClassPathResource resource = new ClassPathResource("config/users.txt");
//            File file = resource.getFile();
//            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
//            writer.write("\n" + username + "=" + password);
//            writer.close();
//            userFileReader.reload();
//            return "success";
//        } catch (IOException e) {
//            return "注册失败，请稍后再试";
//        }
//    }
//}




