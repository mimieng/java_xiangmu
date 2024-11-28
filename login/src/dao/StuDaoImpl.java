package dao;

import Util.DBUtil;
import pojo.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StuDaoImpl implements  StuDao{

    @Override
    public boolean addStudent(Student stu) throws SQLException {
        boolean flag=false;
        Connection con= DBUtil.getcon();
        PreparedStatement ps=con.prepareStatement("insert into student values(?,?,?)");
        ps.setInt(1,stu.getId());
        ps.setString(2,stu.getName());
        ps.setString(3,stu.getScore()+"");
        int i=ps.executeUpdate();
        if(i>0){
            flag=true;
        }else {
            flag=false;
        }
        DBUtil.close(con);
        return flag;

    }

    @Override
    public Student findStudentById(int id) throws SQLException {
        Student stu=null;
        Connection con=DBUtil.getcon();
        PreparedStatement ps=con.prepareStatement("select * from student where son=?");
        ps.setInt(1,id);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            stu=new Student(rs.getInt(1),rs.getString(2),rs.getDouble(3));
        }
        return stu;
    }

    @Override
    public boolean updateStudent(Student stu) throws SQLException {
        boolean flag=false;
        Connection con=DBUtil.getcon();
        PreparedStatement ps = con.prepareStatement("update student set sname=?,score=? where son=?");
        ps.setString(1,stu.getName());
        ps.setDouble(2,stu.getScore());
        ps.setInt(3,stu.getId());
        int i=ps.executeUpdate();
        if(i>0){
            flag=true;
        }else {
            flag = false;
        }
        DBUtil.close(con);
        return  flag;
    }

    @Override
    public boolean deleteStudent(int id) throws SQLException {
        boolean flag=false;
        Connection con=DBUtil.getcon();
        PreparedStatement ps=con.prepareStatement("delete from student where son=?");
        ps.setInt(1,id);
        int i=ps.executeUpdate();
        if(i>0){
            flag=true;
        }else {
            flag=false;
        }
        return flag;
    }
}
