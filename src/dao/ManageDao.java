package dao;

import pojo.Manage;
import util.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static util.DBUtil.getCon;

public class ManageDao {

    // Add a new borrow record
    public boolean borrowEquipment(Manage manage) {
        String sql = "INSERT INTO equipment_borrow (equipment_name, category, borrower_name, borrower_student_id, borrow_date, return_date, quantity, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getCon();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            LocalDate borrowDate = manage.getBorrowDate();
            LocalDate returnDate = manage.getReturnDate();

            if (borrowDate == null) {
                borrowDate = LocalDate.now();
            }

            if (returnDate == null) {
                returnDate = borrowDate.plusDays(7);
            }

            Date sqlBorrowDate = Date.valueOf(borrowDate);
            Date sqlReturnDate = Date.valueOf(returnDate);

            stmt.setString(1, manage.getName());
            stmt.setString(2, manage.getCategory());
            stmt.setString(3, manage.getBorrowerName());
            stmt.setString(4, manage.getBorrowerStudentId());
            stmt.setDate(5, sqlBorrowDate);
            stmt.setDate(6, sqlReturnDate);
            stmt.setInt(7, manage.getQuantity());
            stmt.setString(8, manage.getStatus());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update the status of the equipment to "returned"
    public static boolean updateEquipmentStatusToReturned(String returnerName, String borrowerStudentId) {
        String sql = "UPDATE equipment_borrow SET status = '已归还' WHERE borrower_name = ? AND borrower_student_id = ? AND status != '已归还'";
        try (Connection conn = DBUtil.getCon();
             PreparedStatement psmt = conn.prepareStatement(sql)) {

            psmt.setString(1, returnerName);
            psmt.setString(2, borrowerStudentId);

            int rowsAffected = psmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Query equipment by name and return a list of Manage objects
    public static List<Manage> queryEquipment(String equipmentName) {
        List<Manage> resultList = new ArrayList<>();
        String query = "SELECT * FROM equipment_borrow WHERE equipment_name = ?";

        try (Connection conn = DBUtil.getCon();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, equipmentName);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String equipmentNameFetched = rs.getString("equipment_name");
                    String category = rs.getString("category");
                    String borrowerName = rs.getString("borrower_name");
                    String borrowerStudentId = rs.getString("borrower_student_id");
                    Date borrowDate = rs.getDate("borrow_date");
                    Date returnDate = rs.getDate("return_date");
                    int quantity = rs.getInt("quantity");
                    String status = rs.getString("status");

                    resultList.add(new Manage(id, equipmentNameFetched, category, borrowerName, borrowerStudentId,
                            borrowDate.toLocalDate(), returnDate != null ? returnDate.toLocalDate() : null, quantity, status));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    // Update the return date and status to "returned"
    public static boolean updateReturnDate(String borrowerName, int returnQuantity, String studentId, String returnDate) {
        boolean isSuccess = false;
        String sql = "UPDATE equipment_borrow SET return_date = ?, status = '已归还' " +
                "WHERE borrower_name = ? AND borrower_student_id = ? AND quantity >= ? AND status != '已归还'";

        try (Connection conn = getCon();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, returnDate);
            ps.setString(2, borrowerName);
            ps.setString(3, studentId);
            ps.setInt(4, returnQuantity);

            int rowsUpdated = ps.executeUpdate();
            isSuccess = rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    // Search for equipment by name and return a list of Manage objects
    public List<Manage> searchEquipmentByName(String name) {
        List<Manage> borrowList = new ArrayList<>();
        String sql = "SELECT * FROM equipment_borrow WHERE equipment_name LIKE ?";

        try (Connection conn = getCon();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + name + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String equipmentName = rs.getString("equipment_name");
                    String category = rs.getString("category");
                    String borrowerName = rs.getString("borrower_name");
                    String borrowerStudentId = rs.getString("borrower_student_id");
                    Date borrowDate = rs.getDate("borrow_date");
                    Date returnDate = rs.getDate("return_date");
                    int quantity = rs.getInt("quantity");
                    String status = rs.getString("status");

                    borrowList.add(new Manage(id, equipmentName, category, borrowerName, borrowerStudentId,
                            borrowDate.toLocalDate(), returnDate != null ? returnDate.toLocalDate() : null, quantity, status));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return borrowList;
    }
}
