package dao;

import Util.DBUtil;
import pojo.User;

import java.sql.*;

public class UserDaoImpl implements UserDao{
    @Override
    public User login(String count, String psw) throws SQLException, ClassNotFoundException {
        User user = null;
        Connection coon = DBUtil.getcon();
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection coon = DriverManager.getConnection("jdbc:mysql://localhost:3306/stu", "root", "123456");
        PreparedStatement ps = coon.prepareStatement("select * from user where count=? and psw=?");
        ps.setString(1,count);
        ps.setString(2,psw);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int id = rs.getInt(1);
            String name = rs.getString(4);
            String count1 = rs.getString(2);
            String psw1 = rs.getString(3);

            user = new User(name,psw1,count1,id);
        }
        DBUtil.close(coon);
        return user;
    }
}
