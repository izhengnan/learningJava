package zn;

import java.sql.*;
import java.util.Scanner;

public class Demo1 {
    public static void main(String[] args) {
        String sql = "INSERT INTO students(name, gender, age, class_name, enrollment_date, phone, email)values(?,?,?,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/zn",
                "root", "zn010924");
             PreparedStatement psmt = conn.prepareStatement(sql);){
            //1.注册并加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获取数据库连接
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入学生姓名：");
            String name = sc.next();
            psmt.setString(1, name);
            System.out.println("请输入性别：");
            String gender = sc.next();
            psmt.setString(2, gender);
            System.out.println("请输入年龄：");
            int age = sc.nextInt();
            psmt.setInt(3, age);
            System.out.println("请输入班级名称：");
            String class_name = sc.next();
            psmt.setString(4, class_name);
            System.out.println("请输入入学时间：");
            String enrollment_date = sc.next();
            psmt.setString(5, enrollment_date);
            System.out.println("请输入手机号码：");
            String phone = sc.next();
            psmt.setString(6, phone);
            System.out.println("请输入邮箱：");
            String email = sc.next();
            psmt.setString(7, email);
            int rows = psmt.executeUpdate();
            System.out.println("受到影响的行数有" + rows);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
