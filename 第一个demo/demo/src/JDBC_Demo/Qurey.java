package JDBC_Demo;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class Qurey {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection coon = DriverManager.getConnection("jdbc:mysql://localhost:3306/stu", "root", "123456");
        Statement st = coon.createStatement();
        ResultSet rs = st.executeQuery("select * from student");
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            int score = rs.getInt(3);
            System.out.println("学号为："+id + "   名字为：" + name + "   分数为：" + score);
        }
        rs.close();
        st.close();
        coon.close();
    }
}
