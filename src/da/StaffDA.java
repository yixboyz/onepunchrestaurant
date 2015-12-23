package da;


import domain.Staff;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class StaffDA {
    
    String host = "jdbc:derby://localhost:1527/RestaurantDB";
    String user = "nbuser";
    String password = "nbuser";
    private String tableName = "STAFF";
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    public StaffDA(){
        createConnection();
    }
    
    private void createConnection() {
        try {
            conn = DriverManager.getConnection(host,user,password);
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public ResultSet selectRecord(String staffID){
        String queryStr = "SELECT * FROM " + tableName + " WHERE StaffID = ?";
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, staffID);
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return rs;
    }
    
    public void CreateRecord(String staffID, String staffName, String staffPhone, String staffIC, String staffPosition, String staffPassword) {
        String insertStr = "Insert INTO " + tableName + " VALUES (?,?,?,?,?,?)";
        try {
          
                stmt = conn.prepareStatement(insertStr);

                stmt.setString(1, staffID);
                stmt.setString(2, staffName);
                stmt.setString(3, staffPhone);
                stmt.setString(4, staffIC);
                stmt.setString(5, staffPosition);
                stmt.setString(6, staffPassword);
                

                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "New staff added", "SUCCESS MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public void UpdateRecord(String staffName, String staffPhone, String staffIC, String staffPosition, String staffPassword) {
        String updateStr = "UPDATE " + tableName + " SET StaffName = ?, StaffPhone = ?, StaffIC = ?, StaffPosition = ?, StaffPassword = ? ";
        try {
            stmt = conn.prepareStatement(updateStr);

            stmt.setString(2, staffName);
            stmt.setString(3, staffPhone);
            stmt.setString(4, staffIC);
            stmt.setString(5, staffPosition);
            stmt.setString(6, staffPassword);
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record updated.", "SUCCESS MESSAGE", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
    }
    
     public void DeleteRecord(String staffID) {
        String deleteStr = "DELETE FROM  " + tableName + " WHERE StaffID = ?";
        try {

            stmt = conn.prepareStatement(deleteStr);
            stmt.setString(1, staffID);
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
