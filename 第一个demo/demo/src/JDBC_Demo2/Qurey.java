package JDBC_Demo2;

import java.sql.*;
import java.util.Scanner;

public class Qurey {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要查询的学号：");
        int no = sc.nextInt();
        //1.加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.建立连接
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stu","root","123456");
        System.out.println("数据库连接成功");
        PreparedStatement pt = conn.prepareStatement("select * from student where son=?");
        pt.setInt(1,no);
        ResultSet ps = pt.executeQuery();
        while(ps.next()){
        int id = ps.getInt(1);
        String name = ps.getString(2);
        int score = ps.getInt(3);
        System.out.println("学号为："+id + "   名字为：" + name + "   分数为：" + score);
        }
    }

    }

