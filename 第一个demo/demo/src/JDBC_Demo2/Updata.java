package JDBC_Demo2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Updata {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要修改的学号：");
        int id = sc.nextInt();
        System.out.println("请输入要修改的名字：");
        String name = sc.next();
        System.out.println("请输入要修改的分数：");
        int score = sc.nextInt();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection coon = DriverManager.getConnection("jdbc:mysql://localhost:3306/stu", "root", "123456");
        System.out.println("数据库链接成功");
        PreparedStatement pt = coon.prepareStatement("update student set sname=?,score=? where son=?");
        pt.setString(1,name);
        pt.setInt(2,score);
        pt.setInt(3,id);
        int i = pt.executeUpdate();
        if (i > 0) {
            System.out.println("插入成功");
        } else {
            System.out.println("插入失败");
        }


    }
}
