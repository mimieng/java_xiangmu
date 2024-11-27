package dao;

import pojo.Student;

import java.sql.SQLException;

public interface StuDao {
    boolean addStudent(Student stu) throws SQLException;
    Student findStudentById(int id) throws SQLException;
}
