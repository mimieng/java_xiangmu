package dao;

import pojo.Admin;
import pojo.User;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    Connection con = null;
    /**
     * 管理员登陆
     */
    public User login(User admin){
        String sql = "select * from clients where name=? and password=?";
        User adminRst = null;
        con = DBUtil.getCon();
        try {
            PreparedStatement prst = con.prepareStatement(sql);//把sql语句传给数据库操作对象
            prst.setString(1, admin.getName());
            prst.setString(2, admin.getPassword());
            ResultSet executeQuery = prst.executeQuery();
            if(executeQuery.next()){
                adminRst = new User();
                adminRst.setId(executeQuery.getInt("id"));
                adminRst.setName(executeQuery.getString("name"));
                adminRst.setPassword(executeQuery.getString("password"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeCon(con);
        }
        return adminRst;
    }
    public String editPassword(User admin,String newPassword){
        String sql = "select * from clients where id=? and password=?";
        String sqlString = "update clients set password = ? where id = ?";
        PreparedStatement prst = null;
        String retString = "修改失败";
        int id = 0;
        con = DBUtil.getCon();
        try {
            prst = con.prepareStatement(sql);
            prst.setInt(1, admin.getId());
            prst.setString(2, admin.getPassword());
            ResultSet rs = prst.executeQuery();
            if(!rs.next()){
                retString = "旧密码错误！";
                return retString;
            }
            //获取id
            id = rs.getInt("id");
            //修改
            prst = con.prepareStatement(sqlString);
            prst.setString(1, newPassword);
            prst.setInt(2, id);
            int result = prst.executeUpdate();
            if(result > 0){
                retString = "密码修改成功！";
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }finally {
            DBUtil.closeCon(con);
        }
        return retString;
    }
}
