package dao;

import pojo.Car;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDao {

    // 添加一辆车
    public boolean addCar(Car car) {
        String query = "INSERT INTO medicines (name, category, info, rent) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getCon();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, car.getName());
            pstmt.setString(2, car.getCategory());
            pstmt.setString(3, car.getInfo());
            pstmt.setDouble(4, car.getMonthlyRent());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // 如果影响的行数大于0，表示插入成功
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 更新车辆信息
    public boolean updateCar(Car car) {
        String query = "UPDATE medicines SET name = ?, category = ?, info = ?, rent = ? WHERE id = ?";

        try (Connection conn = DBUtil.getCon();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, car.getName());
            pstmt.setString(2, car.getCategory());
            pstmt.setString(3, car.getInfo());
            pstmt.setDouble(4, car.getMonthlyRent());
            pstmt.setInt(5, car.getId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // 如果影响的行数大于0，表示更新成功
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 删除车辆
    public boolean deleteCar(int carId) {
        String query = "DELETE FROM medicines WHERE id = ?";

        try (Connection conn = DBUtil.getCon();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, carId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // 如果影响的行数大于0，表示删除成功
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 查询所有车辆
    public List<Car> queryCars(String name, String category) {
        List<Car> cars = new ArrayList<>();
        String query = "SELECT * FROM medicines WHERE name LIKE ? AND category LIKE ?";

        try (Connection conn = DBUtil.getCon();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, "%" + name + "%"); // 模糊查询车辆名称
            pstmt.setString(2, "%" + category + "%"); // 模糊查询车辆类别

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Car car = new Car();
                car.setId(rs.getInt("id"));
                car.setName(rs.getString("name"));
                car.setCategory(rs.getString("category"));
                car.setInfo(rs.getString("info"));
                car.setMonthlyRent(rs.getDouble("rent"));
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    // 查询单个车辆
    public Car queryCarById(int carId) {
        Car car = null;
        String query = "SELECT * FROM medicines WHERE id = ?";

        try (Connection conn = DBUtil.getCon();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, carId);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                car = new Car();
                car.setId(rs.getInt("id"));
                car.setName(rs.getString("name"));
                car.setCategory(rs.getString("category"));
                car.setInfo(rs.getString("info"));
                car.setMonthlyRent(rs.getDouble("rent"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return car;
    }
}
