/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import domain.Membership;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class MembershipDA {

    String host = "jdbc:derby://localhost:1527/RestaurantDB";
    String user = "nbuser";
    String password = "nbuser";
    private Connection conn;
    private String tableName = "Membership";
    private PreparedStatement stmt;
    private ResultSet rs;

    public MembershipDA() {
        createConnection();
    }

    private void createConnection() {
        try {
            conn = DriverManager.getConnection(host, user, password);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ResultSet selectRecord(String memId) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE MemberId = ?";
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, memId);
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return rs;

    }

    public void createRecord(String memId, String memName, String memGender, String memIc, String memPhone, String memEmail, int memPoint) {
        String insertStr = "Insert INTO " + tableName + " VALUES (?,?,?,?,?,?,?)";
        try {
          
                stmt = conn.prepareStatement(insertStr);

                stmt.setString(1, memId);
                stmt.setString(2, memName);
                stmt.setString(3, memGender);
                stmt.setString(4, memIc);
                stmt.setString(5, memPhone);
                stmt.setString(6, memEmail);
                stmt.setInt(7, memPoint);

                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "New member added", "SUCCESS MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void UpdateRecord(String memId, String memName, String memGender, String memIc, String memPhone, String memEmail) {
        String updateStr = "UPDATE " + tableName + " SET MemberName = ?, MemberGender = ?, MemberIc = ?, MemberPhone = ?, MemberEmail = ? " + " WHERE MemberID = ?";
        try {
            stmt = conn.prepareStatement(updateStr);

            stmt.setString(1, memName);
            stmt.setString(2, memGender);
            stmt.setString(3, memIc);
            stmt.setString(4, memPhone);
            stmt.setString(5, memEmail);
            stmt.setString(6, memId);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record updated.", "SUCCESS MESSAGE", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
    }
    
     public void DeleteRecord(String memId) {
        String deleteStr = "DELETE FROM  " + tableName + " WHERE MemberId = ?";
        try {

            stmt = conn.prepareStatement(deleteStr);
            stmt.setString(1, memId);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record deleted.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
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
