package zn;

import java.sql.*;
import java.util.Scanner;

public class Demo4 {
    public static void main(String[] args) {
        String sql = "update students set gender=?,age=?,class_name=?,enrollment_date=?,phone=?,email=? where name = ?";
        Connection conn = null;
        PreparedStatement psmt = null;
        PreparedStatement psmt2 = null;
        try {
            //1.注册并加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获取数据库连接
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/zn",
                    "root", "zn010924");
            psmt = conn.prepareStatement(sql);
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入你想修改的学生姓名：");
            String name = sc.next();
            //先查询数据表,单独查询
            String sql2 = "select * from students where name = ?";
            psmt2 = conn.prepareStatement(sql2);
            psmt2.setString(1, name);
            ResultSet rs = psmt2.executeQuery();
            if (rs.next()){
                psmt.setString(7, name);
                System.out.println("请输入性别：");
                String gender = sc.next();
                psmt.setString(1, gender);
                System.out.println("请输入年龄：");
                int age = sc.nextInt();
                psmt.setInt(2, age);
                System.out.println("请输入班级名称：");
                String class_name = sc.next();
                psmt.setString(3, class_name);
                System.out.println("请输入入学时间：");
                String enrollment_date = sc.next();
                psmt.setString(4, enrollment_date);
                System.out.println("请输入手机号码：");
                String phone = sc.next();
                psmt.setString(5, phone);
                System.out.println("请输入邮箱：");
                String email = sc.next();
                psmt.setString(6, email);
                int rows = psmt.executeUpdate();
                System.out.println("受到影响的行数有" + rows);
            }else {
                System.out.println("没有该学生");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (psmt != null) {
                    psmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("连接关闭失败");
            }

        }
    }
}
