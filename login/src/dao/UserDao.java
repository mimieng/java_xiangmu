package dao;

import pojo.User;

import java.sql.SQLException;

public interface UserDao {
    User login(String count,String psw) throws SQLException, ClassNotFoundException;
}
