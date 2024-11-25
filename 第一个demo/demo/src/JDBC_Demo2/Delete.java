package JDBC_Demo2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Delete {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要删除的学号：");
        int id = sc.nextInt();
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection coon = DriverManager.getConnection("jdbc:mysql://localhost:3306/stu","root","123456");
        PreparedStatement pt = coon.prepareStatement("delete from student where son=?");
        pt.setInt(1, id);
        int i = pt.executeUpdate();
        if (i > 0) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
        pt.close();
        coon.close();
    }
}
