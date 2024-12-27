package dao;

import pojo.Client;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {
    Connection con;

    // Method to get all clients from the database
    public List<Client> getAllClients() {
        List<Client> clientList = new ArrayList<>();
        String sql = "SELECT * FROM clients";  // Adjust the table name and columns as necessary

        try (Connection con = DBUtil.getCon();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getInt("id"));
                client.setName(rs.getString("name"));
                client.setGender(rs.getString("gender"));
                client.setPassword(rs.getString("password"));
                clientList.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientList;
    }

    // Method to search clients by name
    public List<Client> getClientsByName(String name) {
        List<Client> clientList = new ArrayList<>();
        String sql = "SELECT * FROM clients WHERE name LIKE ?";  // Adjust the table name and columns as necessary

        try (Connection con = DBUtil.getCon();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");  // Use '%' for wildcard search

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Client client = new Client();
                    client.setId(rs.getInt("id"));
                    client.setName(rs.getString("name"));
                    client.setGender(rs.getString("gender"));
                    client.setPassword(rs.getString("password"));
                    clientList.add(client);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientList;
    }

    // Method to add a new client
    public boolean addClient(Client client) {
        String sql = "INSERT INTO clients (name, gender, password) VALUES (?, ?, ?)";
        con=DBUtil.getCon();
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, client.getName());
            ps.setString(2, client.getGender());
            ps.setString(3, client.getPassword());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to update an existing client
    public boolean updateClient(Client client) {
        String sql = "UPDATE clients SET name = ?, gender = ?, password = ? WHERE id = ?";
        try (Connection con = DBUtil.getCon();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, client.getName());
            ps.setString(2, client.getGender());
            ps.setString(3, client.getPassword());
            ps.setInt(4, client.getId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to delete a client
    public boolean deleteClient(int clientId) {
        String sql = "DELETE FROM clients WHERE id = ?";
        try (Connection con = DBUtil.getCon();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, clientId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
