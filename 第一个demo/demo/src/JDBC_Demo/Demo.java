package JDBC_Demo;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Demo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
       //1.先加载mysql驱动
        Connection conn ;
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/stu","root","123456");
        System.out.println("成功");
        //2,创建连接,返回链接对象
    }
}
