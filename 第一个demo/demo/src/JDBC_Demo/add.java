package JDBC_Demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class add {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学号：");
        int id = sc.nextInt();
        System.out.println("请输入姓名：");
        String name = sc.next();
        System.out.println("请输入成绩：");
        int score = sc.nextInt();

        try (

                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stu", "root", "123456");
             PreparedStatement pstmt = conn.prepareStatement("insert into student values ("+id+",'"+name+"',"+score+")")) {


            // 执行 SQL
            int i = pstmt.executeUpdate();
            if (i > 0) {
                System.out.println("插入成功");
            } else {
                System.out.println("插入失败");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库操作失败: " + e.getMessage());
        }
    }
}
