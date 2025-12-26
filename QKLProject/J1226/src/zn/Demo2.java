package zn;

import java.sql.*;
import java.util.Scanner;

public class Demo2 {
    public static void main(String[] args) {
        String sql2 = "select * from students";
        //try-with-resource
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/zn",
                "root", "zn010924"); PreparedStatement psmt = conn.prepareStatement(sql2);) {
            //1.注册并加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获取数据库连接
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("name") + " " + rs.getString("gender") + " " + rs.getInt("age") + " " + rs.getString("class_name") + " " + rs.getDate("enrollment_date") + " " +
                        rs.getString("phone") + " " + rs.getString("email"));

            }
            rs.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
