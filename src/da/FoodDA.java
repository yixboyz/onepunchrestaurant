/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import domain.Food;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class FoodDA {

    String host = "jdbc:derby://localhost:1527/RestaurantDB";
    String user = "nbuser";
    String password = "nbuser";
    private Connection conn;
    private String tableName = "Food";
    private String sqlQueryStr = "SELECT * FROM " + tableName;
    private PreparedStatement stmt;
    private ResultSet rs;

    public FoodDA() {
        createConnection();
    }

    private void createConnection() {
        try {
            conn = DriverManager.getConnection(host, user, password);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ResultSet selectRecord(String foodId) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE foodID = ?";
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, foodId);
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return rs;

    }

    public void createRecord(String fId, String fName, String fType, double fPrice) {
        String insertStr = "Insert INTO " + tableName + " VALUES (?,?,?,?)";

        try {
            stmt = conn.prepareStatement(insertStr);

            stmt.setString(1, fId);
            stmt.setString(2, fName);
            stmt.setString(3, fType);
            stmt.setDouble(4, fPrice);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "New item is added on the list.", "SUCCESS MESSAGE", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void UpdateRecord(String fId, String fName, String fType, double fPrice) {
        String updateStr = "UPDATE " + tableName + " SET foodName = ?, foodType = ?, foodPrice = ? " + " WHERE foodID = ?";

        try {
            stmt = conn.prepareStatement(updateStr);

            stmt.setString(1, fName);
            stmt.setString(2, fType);
            stmt.setDouble(3, fPrice);
            stmt.setString(4, fId);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record updated.", "SUCCESS MESSAGE", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void DeleteRecord(String fId) {
        String deleteStr = "DELETE FROM  " + tableName + " WHERE foodID = ?";
        try {
            stmt = conn.prepareStatement(deleteStr);
            stmt.setString(1, fId);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record deleted.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public ArrayList<Food> getFoods() {
        ArrayList<Food> food = new ArrayList<Food>();
        try {
            stmt = conn.prepareStatement(sqlQueryStr);
            rs = stmt.executeQuery();
            while (rs.next()) {
                food.add(new Food(rs.getString("foodId"), rs.getString("foodName"), rs.getString("foodType"), rs.getDouble("foodPrice")));

            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

        return food;
    }

    private void shutDown() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
