/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import domain.Payment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Yang
 */
public class PaymentDA {

    private String host = "jdbc:derby://localhost:1527/RestaurantDB";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "OrderFood";
    private String tableName2 = "Membership";
    private String sqlQueryStr = "SELECT * FROM " + tableName + " WHERE OrderId = ?";
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    public PaymentDA() {
        createConnection();
    }

    public ArrayList<Payment> getPayment(int ordId) {
        ArrayList<Payment> payment = new ArrayList<Payment>();
        try {
            stmt = conn.prepareStatement(sqlQueryStr);
            stmt.setInt(1, ordId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                payment.add(new Payment(rs.getInt("OrderId"), rs.getString("FoodId"), rs.getDouble("Price")));

            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

        return payment;
    }

    public ResultSet RetrieveRecord(String id) {
        String queryStr = "SELECT * FROM " + tableName2 + " WHERE memberid = ?";
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, id);
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return rs;

    }

    public void MakePayment(String memId) {
        String updateStr = "UPDATE Membership SET MemberPoint = ? " + " WHERE MemberId = ?";
 
        try {
            if(rs.next()){
            int point = rs.getInt("MemberPoint") + 10;
            stmt = conn.prepareStatement(updateStr);
            stmt.setInt(1, point);
            stmt.setString(2, memId);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Payment Success.", "SUCCESS MESSAGE", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private void createConnection() {
        try {
            conn = DriverManager.getConnection(host, user, password);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
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
