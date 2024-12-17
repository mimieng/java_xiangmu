package dao;

import pojo.Student;
import pojo.User;
import util.DBUtil;
import util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDao  {
    Connection con = null;
    public boolean addStudent(Student student){
        String sql = "insert into s_student values(null,?,?,?,?)";
        con = DBUtil.getCon();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getClassId());
            preparedStatement.setString(3, student.getPassword());
            preparedStatement.setString(4, student.getSex());
            if(preparedStatement.executeUpdate() > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeCon(con);
        }
        return false;
    }
    public List<Student> getStudentList(Student student){
        List<Student> retList = new ArrayList<Student>();
        StringBuffer sqlString = new StringBuffer("select * from user");
        if(!StringUtil.isEmpty(student.getName())){
            sqlString.append(" and name like '%"+student.getName()+"%'");
        }
        if(student.getClassId() != 0){
            sqlString.append(" and classId ="+student.getClassId());
        }
        con = DBUtil.getCon();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sqlString.toString().replaceFirst("and", "where"));
            ResultSet executeQuery = preparedStatement.executeQuery();
            while(executeQuery.next()){
                Student s = new Student();
                s.setId(executeQuery.getInt("id"));
                s.setName(executeQuery.getString("name"));

                s.setSex(executeQuery.getString("gender"));
                s.setPassword(executeQuery.getString("password"));
                retList.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeCon(con);
        }
        return retList;
    }
    public boolean delete(int id){
        String sql = "delete from user where id=?";
        con = DBUtil.getCon();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            if(preparedStatement.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeCon(con);
        }
        return false;
    }
    public boolean update(Student student){
        String sql = "update user set name=?, classId=?,sex=?,password=? where id=?";
        con = DBUtil.getCon();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getClassId());
            preparedStatement.setString(3, student.getSex());
            preparedStatement.setString(4, student.getPassword());
            preparedStatement.setInt(5, student.getId());
            if(preparedStatement.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeCon(con);
        }
        return false;
    }
    public String editPassword(Student student,String newPassword){
        String sql = "select * from user where id=? and password=?";
        String sqlString = "update user set password = ? where id = ?";
        PreparedStatement prst = null;
        int id = 0;
        String retString = "修改失败";
        con = DBUtil.getCon();
        try {
            prst = con.prepareStatement(sql);
            prst.setInt(1, student.getId());
            prst.setString(2, student.getPassword());
            ResultSet executeQuery = prst.executeQuery();
            if(!executeQuery.next()){
                retString = "旧密码错误！";
                return retString;
            }
            id = executeQuery.getInt("id");
            prst = con.prepareStatement(sqlString);
            prst.setString(1, newPassword);
            prst.setInt(2, id);
            int rst = prst.executeUpdate();
            if(rst > 0){
                retString = "密码修改成功！";
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }finally {
            DBUtil.closeCon(con);
        }
        return retString;
    }
    public User login(User student){
        String sql = "select * from user where name=? and password=?";
        User studentRst = null;
        con = DBUtil.getCon();
        try {
            PreparedStatement prst = con.prepareStatement(sql);//把sql语句传给数据库操作对象
            prst.setString(1, student.getName());
            prst.setString(2, student.getPassword());
            ResultSet executeQuery = prst.executeQuery();
            if(executeQuery.next()){
                studentRst = new User();
                studentRst.setId(executeQuery.getInt("id"));
                studentRst.setName(executeQuery.getString("name"));
                studentRst.setPassword(executeQuery.getString("password"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeCon(con);
        }
        return studentRst;
    }
}
