package dao;

import pojo.Query;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryDao {

    // Method to query equipment by name
    public List<Query> queryEquipment(String name) {
        List<Query> equipmentList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = DBUtil.getCon();
            String sql = "SELECT * FROM equipment WHERE name LIKE ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, "%" + name + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String equipmentName = rs.getString("name");
                int quantity = rs.getInt("quantity");
                equipmentList.add(new Query(id, equipmentName, quantity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt, connection);  // Improved resource management
        }

        return equipmentList;
    }




        // 查询所有装备的方法
        public List<Query> queryAllEquipment() {
            List<Query> equipmentList = new ArrayList<>();

            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;

            try {
                // 获取数据库连接
                conn = DBUtil.getCon();

                // 执行查询
                String sql = "SELECT id, name, quantity FROM equipment";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);

                // 处理结果集
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int quantity = rs.getInt("quantity");

                    Query equipment = new Query(id, name, quantity);
                    equipmentList.add(equipment);
                }
            } catch (SQLException se) {
                // 处理 JDBC 错误
                se.printStackTrace();
            } finally {
                // 清理环境
                try {
                    if (rs != null) rs.close();
                    if (stmt != null) stmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }

            return equipmentList;
        }


    // Method to update equipment information
    public boolean updateEquipment(Query equipment) {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DBUtil.getCon();
            String sql = "UPDATE equipment SET name = ?, quantity = ? WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, equipment.getName());
            stmt.setInt(2, equipment.getQuantity());
            stmt.setInt(3, equipment.getId());
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(null, stmt, connection);  // Improved resource management
        }
    }


    // Improved insert method, now uses DBUtil.getCon() to get a valid connection
    public boolean insertEquipment(String name, int quantity) {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DBUtil.getCon();
            String sql = "INSERT INTO equipment (name, quantity) VALUES (?, ?)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setInt(2, quantity);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  // If rows affected is more than 0, insert was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Return false if an error occurred
        } finally {
            closeResources(null, stmt, connection);  // Improved resource management
        }
    }

    // Method to delete equipment by id
    public boolean deleteEquipment(int id) {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DBUtil.getCon();
            String sql = "DELETE FROM equipment WHERE id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(null, stmt, connection);  // Improved resource management
        }
    }

    // Utility method to close resources
    private void closeResources(ResultSet rs, PreparedStatement stmt, Connection connection) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
