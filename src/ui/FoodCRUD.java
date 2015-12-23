/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import control.MaintainFood;
import domain.Food;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author User
 */
public class FoodCRUD extends JFrame {
    
    //declare variable
    private MaintainFood mainFood;

    private JLabel jlblID = new JLabel("Food / Beverage ID");
    private JTextField jtfID = new JTextField();
    private JLabel jlblName = new JLabel("Food / Beverage Name");
    private JTextField jtfName = new JTextField();
    private JLabel jlblCat = new JLabel("Categories");
    private JTextField jtfCat = new JTextField();
    private JLabel jlblPrice = new JLabel("Price");
    private JTextField jtfPrice = new JTextField();

    private JButton jbtAdd = new JButton("Create");
    private JButton jbtRetrieve = new JButton("Retrieve");
    private JButton jbtUpdate = new JButton("Update");
    private JButton jbtDelete = new JButton("Delete");
    private JButton jbtBack = new JButton("Back");
    private JButton jbtRecord = new JButton("View All Record");

    String host = "jdbc:derby://localhost:1527/RestaurantDB";
    String user = "nbuser";
    String password = "nbuser";
    private String tableName = "FOOD";
    public Connection conn;
    private PreparedStatement prepStmt;

    //static final String DATABASE_URL = "jdbc:derby://localhost:1527/upperstardb";
    //static final String USERNAME = "nbuser";
    //static final String PASSWORD = "nbuser";
    //static final String QUERY = "SELECT * FROM Countries";   
    //private JTextArea queryArea;
    //private JTextField filterText = new JTextField();
    public FoodCRUD() {

        mainFood = new MaintainFood();
        
        JPanel jpNorth = new JPanel();
        jpNorth.add(jbtBack);
        jpNorth.add(jbtRecord);
        add(jpNorth, BorderLayout.NORTH);

        JPanel jpCenter = new JPanel(new GridLayout(4, 2));

        jpCenter.add(jlblID);
        jpCenter.add(jtfID);
        jpCenter.add(jlblName);
        jpCenter.add(jtfName);
        jpCenter.add(jlblCat);
        jpCenter.add(jtfCat);
        jpCenter.add(jlblPrice);
        jpCenter.add(jtfPrice);

        add(jpCenter, BorderLayout.CENTER);

        JPanel jpSouth = new JPanel();

        jpSouth.add(jbtAdd);
        jpSouth.add(jbtRetrieve);
        jpSouth.add(jbtUpdate);
        jpSouth.add(jbtDelete);

        add(jpSouth, BorderLayout.SOUTH);
        //add action listener to all button
        jbtAdd.addActionListener(new AddListener());
        jbtRetrieve.addActionListener(new RetrieveListener());
        jbtUpdate.addActionListener(new UpdateListener());
        jbtDelete.addActionListener(new DeleteListener());
        jbtBack.addActionListener(new BackListener());
        jbtRecord.addActionListener(new RecordListener());
        
        jpNorth.setBackground(Color.WHITE);
        jpCenter.setBackground(Color.WHITE);
        jpSouth.setBackground(Color.WHITE);

        createConnection();
        setTitle("Food CRUD");
        setSize(600, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
    }

    private void createConnection() {
        try {
            conn = DriverManager.getConnection(host, user, password);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "CONNECTION ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new FoodCRUD();
       
    }

    private class AddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String fId = jtfID.getText();
            String fName = jtfName.getText();
            String fType = jtfCat.getText();
            double fPrice = Double.parseDouble(jtfPrice.getText());

            ResultSet rs = mainFood.selectRecord(fId);

            try {

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Record is duplicated. Please re-enter.", "Duplicated Item", JOptionPane.INFORMATION_MESSAGE);
                    clearText();
                } else {
                    mainFood.createRecord(fId, fName, fType, fPrice);
                    clearText();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    private class RetrieveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String foodID = jtfID.getText();
            ResultSet rs = mainFood.selectRecord(foodID);

            try {
                if (rs.next()) {
                    jtfName.setText(rs.getString("foodName"));
                    jtfCat.setText(rs.getString("foodType"));
                    jtfPrice.setText(rs.getString("foodPrice"));
                } else {
                    JOptionPane.showMessageDialog(null, "No Recorded data.", "Item not found", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    private class UpdateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String fId = jtfID.getText();
            String fName = jtfName.getText();
            String fType = jtfCat.getText();
            double fPrice = Double.parseDouble(jtfPrice.getText());

            ResultSet rs = mainFood.selectRecord(fId);

            try {
                if (rs.next()) {
                    mainFood.UpdateRecord(fId, fName, fType, fPrice);
                    clearText();
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    private class DeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String fId = jtfID.getText();
            ResultSet rs = mainFood.selectRecord(fId);
            try {
                if (rs.next()) {
                    jtfName.setText(rs.getString("foodName"));
                    jtfCat.setText(rs.getString("foodType"));
                    jtfPrice.setText(rs.getString("foodPrice"));

                    int option = JOptionPane.showConfirmDialog(null, "Are you sure?", "QUESTION", JOptionPane.YES_NO_OPTION);

                    if (option == JOptionPane.YES_OPTION) {
                        mainFood.DeleteRecord(fId);
                        clearText();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No selected record found in the list.", "RECORD NOT FOUND", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    private class BackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            new MainMenu();
            dispose();
        }
    }

    private class RecordListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            new FoodTable();
        }
    }

    public void clearText() {
        jtfID.setText("");
        jtfName.setText("");
        jtfCat.setText("");
        jtfPrice.setText("");
    }

}
