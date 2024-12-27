package dao;

import pojo.Staff;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDao {

    // 获取所有员工信息
    public List<Staff> getAllStaff() {
        List<Staff> staffList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getCon();
            String sql = "SELECT * FROM staff";
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();

            while (rs.next()) {
                Staff staff = new Staff();
                staff.setId(rs.getInt("id"));
                staff.setName(rs.getString("name"));
                staff.setGender(rs.getString("gender"));
                staff.setPosition(rs.getString("position"));
                staff.setAge(rs.getInt("age"));
                staff.setPassword(rs.getString("password"));
                staffList.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeCon(conn);
        }
        return staffList;
    }

    // 根据姓名查询员工
    public List<Staff> getStaffByName(String name) {
        List<Staff> staffList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getCon();
            String sql = "SELECT * FROM staff WHERE name LIKE ?";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, "%" + name + "%");  // 用模糊查询来搜索姓名
            rs = psmt.executeQuery();

            while (rs.next()) {
                Staff staff = new Staff();
                staff.setId(rs.getInt("id"));
                staff.setName(rs.getString("name"));
                staff.setGender(rs.getString("gender"));
                staff.setPosition(rs.getString("position"));
                staff.setAge(rs.getInt("age"));
                staff.setPassword(rs.getString("password"));
                staffList.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeCon(conn);
        }
        return staffList;
    }

    // 添加员工
    public boolean addStaff(Staff staff) {
        Connection conn = null;
        PreparedStatement psmt = null;

        try {
            conn = DBUtil.getCon();
            String sql = "INSERT INTO staff (name, gender, position, age, password) VALUES (?, ?, ?, ?, ?)";
            psmt = conn.prepareStatement(sql);

            psmt.setString(1, staff.getName());
            psmt.setString(2, staff.getGender());
            psmt.setString(3, staff.getPosition());
            psmt.setInt(4, staff.getAge());
            psmt.setString(5, staff.getPassword());

            int rowsAffected = psmt.executeUpdate();
            return rowsAffected > 0;  // 返回是否插入成功
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.closeCon(conn);
        }
    }

    // 获取所有职位的方法
    public List<String> getAllPositions() {
        List<String> positions = new ArrayList<>();
        String sql = "SELECT DISTINCT position FROM staff";  // 查询所有不同的职位

        try (Connection conn = DBUtil.getCon();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // 将查询到的所有职位添加到列表中
            while (rs.next()) {
                positions.add(rs.getString("position"));
            }
        } catch (SQLException e) {
            e.printStackTrace();  // 打印异常信息
        }
        return positions;
    }

    // 删除员工
    public boolean deleteStaffById(int id) {
        Connection conn = null;
        PreparedStatement psmt = null;

        try {
            conn = DBUtil.getCon();
            String sql = "DELETE FROM staff WHERE id = ?";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, id);

            int rowsAffected = psmt.executeUpdate();
            return rowsAffected > 0;  // 返回是否删除成功
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.closeCon(conn);
        }
    }

    // 更新员工信息
    public boolean updateStaff(Staff staff) {
        Connection conn = null;
        PreparedStatement psmt = null;

        try {
            conn = DBUtil.getCon();
            String sql = "UPDATE staff SET name = ?, gender = ?, position = ?, age = ?, password = ? WHERE id = ?";
            psmt = conn.prepareStatement(sql);

            psmt.setString(1, staff.getName());
            psmt.setString(2, staff.getGender());
            psmt.setString(3, staff.getPosition());
            psmt.setInt(4, staff.getAge());
            psmt.setString(5, staff.getPassword());
            psmt.setInt(6, staff.getId());

            int rowsAffected = psmt.executeUpdate();
            return rowsAffected > 0;  // 返回是否更新成功
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.closeCon(conn);
        }
    }

    // 通过ID查询员工信息
    public Staff getStaffById(int id) {
        Staff staff = null;
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getCon();
            String sql = "SELECT * FROM staff WHERE id = ?";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, id);
            rs = psmt.executeQuery();

            if (rs.next()) {
                staff = new Staff();
                staff.setId(rs.getInt("id"));
                staff.setName(rs.getString("name"));
                staff.setGender(rs.getString("gender"));
                staff.setPosition(rs.getString("position"));
                staff.setAge(rs.getInt("age"));
                staff.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeCon(conn);
        }
        return staff;
    }
}
