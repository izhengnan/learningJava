package zn;

import java.sql.*;
import java.util.Scanner;

public class Demo3 {
    public static void main(String[] args) {
        String sql2 = "delete from students where name=?";
        //try-with-resource
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/zn",
                "root", "zn010924");
             PreparedStatement psmt = conn.prepareStatement(sql2);) {
            //1.注册并加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获取数据库连接
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入要删除的姓名：");
            String name = sc.next();
            psmt.setString(1, name);
            int rows = psmt.executeUpdate();
            System.out.println("删除了" + rows + "行数据");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
