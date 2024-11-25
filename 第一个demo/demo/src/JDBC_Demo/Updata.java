package JDBC_Demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.IllegalFormatCodePointException;

public class Updata {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection coon = DriverManager.getConnection("jdbc:mysql://localhost:3306/stu", "root", "123456");
        System.out.println("数据库连接成功");
        Statement statement = coon.createStatement();
        int count = statement.executeUpdate("update student set score=score+1 where son=1019");
        if (count>0){
            System.out.println("更新成功");
        }else {
            System.out.println("更新失败");
        }

    }
}
