package dao;

import pojo.Teacher;
import util.DBUtil;
import util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TeacherDao {
	Connection con = null;
	public boolean addTeacher(Teacher teacher){
		String sql = "insert into s_teacher values(null,?,?,?,?,?)";
		con = DBUtil.getCon();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, teacher.getName());
			preparedStatement.setString(2, teacher.getSex());
			preparedStatement.setString(3, teacher.getTitle());
			preparedStatement.setInt(4, teacher.getAge());
			preparedStatement.setString(5, teacher.getPassword());
			if(preparedStatement.executeUpdate() > 0)return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeCon(con);
		}
		return false;
	}

	public List<Teacher> getTeacherList(Teacher teacher) {
		List<Teacher> retList = new ArrayList<Teacher>();
		StringBuffer sqlString = new StringBuffer("select * from s_teacher");
		if(!StringUtil.isEmpty(teacher.getName())){
			sqlString.append(" where name like '%"+teacher.getName()+"%'");
		}
		con = DBUtil.getCon();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sqlString.toString());
			ResultSet executeQuery = preparedStatement.executeQuery();
			while(executeQuery.next()){
				Teacher t = new Teacher();
				t.setId(executeQuery.getInt("id"));
				t.setName(executeQuery.getString("name"));
				t.setSex(executeQuery.getString("sex"));
				t.setTitle(executeQuery.getString("title"));
				t.setAge(executeQuery.getInt("age"));
				t.setPassword(executeQuery.getString("password"));
				retList.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeCon(con);
		}
		return retList;
	}
	public boolean delete(int id){
		String sql = "delete from s_teacher where id=?";
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
	public boolean update(Teacher teacher){
		String sql = "update s_teacher set name=?, sex=?,title=?,age=?,password=? where id=?";
		con = DBUtil.getCon();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, teacher.getName());
			preparedStatement.setString(2, teacher.getSex());
			preparedStatement.setString(3, teacher.getTitle());
			preparedStatement.setInt(4, teacher.getAge());
			preparedStatement.setString(5, teacher.getPassword());
			preparedStatement.setInt(6, teacher.getId());
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
	public Teacher login(Teacher teacher){
		String sql = "select * from s_teacher where name=? and password=?";
		Teacher teacherRst = null;
		con = DBUtil.getCon();
		try {
			PreparedStatement prst = con.prepareStatement(sql);//把sql语句传给数据库操作对象
			prst.setString(1, teacher.getName());
			prst.setString(2, teacher.getPassword());
			ResultSet executeQuery = prst.executeQuery();
			if(executeQuery.next()){
				teacherRst = new Teacher();
				teacherRst.setId(executeQuery.getInt("id"));
				teacherRst.setName(executeQuery.getString("name"));
				teacherRst.setPassword(executeQuery.getString("password"));
				teacherRst.setSex(executeQuery.getString("sex"));
				teacherRst.setAge(executeQuery.getInt("Age"));
				teacherRst.setTitle(executeQuery.getString("title"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeCon(con);
		}
		return teacherRst;
	}
	public String editPassword(Teacher teacher,String newPassword){
		String sql = "select * from s_teacher where id=? and password=?";
		PreparedStatement prst = null;
		int id = 0;
		String retString = "修改失败";
		String sqlString = "update s_teacher set password = ? where id = ?";
		con = DBUtil.getCon();
		try {
			prst = con.prepareStatement(sql);
			prst.setInt(1, teacher.getId());
			prst.setString(2, teacher.getPassword());
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
		//把sql语句传给数据库操作对象
		return retString;
	}
}
