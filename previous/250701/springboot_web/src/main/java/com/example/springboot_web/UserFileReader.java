//package com.example.springboot_web;
//
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.stereotype.Component;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//public class UserFileReader {
//
//    private final Map<String, String> userMap = new HashMap<>();
//
//    public UserFileReader() {
//        loadUsersFromTxt();
//    }
//
//    private void loadUsersFromTxt() {
//        try {
//            ClassPathResource resource = new ClassPathResource("config/users.txt");
//            InputStream is = resource.getInputStream();
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split("=", 2);
//                if (parts.length == 2) {
//                    String username = parts[0].trim();
//                    String password = parts[1].trim();
//                    userMap.put(username, password);
//                }
//            }
//        } catch (IOException e) {
//            throw new RuntimeException("无法读取 users.txt 文件", e);
//        }
//    }
//
//    public boolean validateUser(String username, String password) {
//        return userMap.containsKey(username) && userMap.get(username).equals(password);
//    }
//    public void reload() {
//        userMap.clear();
//        loadUsersFromTxt();
//    }
//}
