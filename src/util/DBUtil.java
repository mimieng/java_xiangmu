package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    //1、连接数据库的方法

    public static Connection getCon()
    {
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/pe";
        String user = "root";
        String psw = "123456";
        try {
            //获取数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //建立链接，返回链接对象
            con = DriverManager.getConnection(url,user,psw);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
    //2、关闭数据库的方法
    public static void closeCon(Connection con)
    {
        if (con!=null)
        {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
