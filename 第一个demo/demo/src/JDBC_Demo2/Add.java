package JDBC_Demo2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Add {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学号：");
        int id = sc.nextInt();
        System.out.println("请输入名字：");
        String name = sc.next();
        System.out.println("请输入分数：");
        int score = sc.nextInt();
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection coon = DriverManager.getConnection("jdbc:mysql://localhost:3306/stu", "root", "123456");
        PreparedStatement ps = coon.prepareStatement("insert into student values (?,?,?)");
        ps.setInt(1, id);
        ps.setString(2, name);
        ps.setInt(3, score);
        int i = ps.executeUpdate();
        if (i > 0) {
            System.out.println("插入成功");
        } else {
            System.out.println("插入失败");
        }
        ps.close();
        coon.close();
        sc.close();

    }
}
