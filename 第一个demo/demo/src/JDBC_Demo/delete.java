package JDBC_Demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class delete {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
     Class.forName("com.mysql.cj.jdbc.Driver");
        Connection root = DriverManager.getConnection("jdbc:mysql://localhost:3306/stu", "root", "123456");
        System.out.println("数据库连接成功");
        Statement statement = root.createStatement();
        int count = statement.executeUpdate("delete from student where son=1019");
        if (count>0){
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
    }

}
