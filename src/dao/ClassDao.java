package dao;

import pojo.StudentClass;
import util.DBUtil;
import util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * 班级信息与数据库的操作
 * @author 
 *
 */
public class ClassDao  {
	Connection con = null;
	public boolean addClass(StudentClass scl){
		String sql = "insert into s_class values(null,?,?)";
		con = DBUtil.getCon();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, scl.getName());
			preparedStatement.setString(2, scl.getInfo());
			if(preparedStatement.executeUpdate() > 0)return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeCon(con);
		}
		return false;
	}
	public List<StudentClass> getClassList(StudentClass studentClass){
		List<StudentClass> retList = new ArrayList<StudentClass>();
		String sqlString = "select * from s_class";
		if(!StringUtil.isEmpty(studentClass.getName())){
			sqlString += " where name like '%"+studentClass.getName()+"%'";
		}
		con = DBUtil.getCon();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sqlString);
			ResultSet executeQuery = preparedStatement.executeQuery();
			while(executeQuery.next()){
				StudentClass sc = new StudentClass();
				sc.setId(executeQuery.getInt("id"));
				sc.setName(executeQuery.getString("name"));
				sc.setInfo(executeQuery.getString("info"));
				retList.add(sc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeCon(con);
		}
		return retList;
	}
	public boolean delete(int id){
		String sql = "delete from s_class where id=?";
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
	public boolean update(StudentClass sc){
		String sql = "update s_class set name=?, info=? where id=?";
		con = DBUtil.getCon();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, sc.getName());
			preparedStatement.setString(2, sc.getInfo());
			preparedStatement.setInt(3, sc.getId());
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
}
