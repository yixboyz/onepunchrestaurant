package ui;

import control.MaintainStaff;
import domain.Staff;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public class StaffFrame extends JFrame {

    private MaintainStaff mainStaff;

    private JLabel jlblStaffID = new JLabel("Staff ID");
    private JLabel jlblStaffName = new JLabel("Staff Name");
    private JLabel jlblStaffPhone = new JLabel("Staff Phone");
    private JLabel jlblStaffIC = new JLabel("Staff IC");
    private JLabel jlblStaffPosition = new JLabel("Staff Position");
    private JLabel jlblStaffPassword = new JLabel("Staff Password");

    private JTextField jtfStaffID = new JTextField();
    private JTextField jtfStaffName = new JTextField();
    private JTextField jtfStaffPhone = new JTextField();
    private JTextField jtfStaffIC = new JTextField();
    private JTextField jtfStaffPosition = new JTextField();
    private JTextField jtfStaffPassword = new JTextField();

    private JButton jbtCreate = new JButton("Create");
    private JButton jbtRetrieve = new JButton("Retrieve");
    private JButton jbtUpdate = new JButton("Update");
    private JButton jbtDelete = new JButton("Delete");
    private JButton jbtBack = new JButton("Back");
    private JButton jbtClear = new JButton("Clear");
    private JButton jbtViewData = new JButton("View All Record");

    public StaffFrame() {

        mainStaff = new MaintainStaff();
        JPanel jpCenter = new JPanel(new GridLayout(6, 2));
        jpCenter.setBorder(new TitledBorder("Staff Detail"));
        jpCenter.add(jlblStaffID);
        jpCenter.add(jtfStaffID);
        jpCenter.add(jlblStaffName);
        jpCenter.add(jtfStaffName);
        jpCenter.add(jlblStaffPhone);
        jpCenter.add(jtfStaffPhone);
        jpCenter.add(jlblStaffIC);
        jpCenter.add(jtfStaffIC);
        jpCenter.add(jlblStaffPosition);
        jpCenter.add(jtfStaffPosition);
        jpCenter.add(jlblStaffPassword);
        jpCenter.add(jtfStaffPassword);
        add(jpCenter, BorderLayout.CENTER);

        JPanel jpSouth = new JPanel();
        jpSouth.add(jbtCreate);
        jpSouth.add(jbtRetrieve);
        jpSouth.add(jbtUpdate);
        jpSouth.add(jbtDelete);
        jpSouth.add(jbtClear);
        add(jpSouth, BorderLayout.SOUTH);

        JPanel jpNorth = new JPanel();
        jpNorth.add(jbtBack);
        jpNorth.add(jbtViewData);
        add(jpNorth, BorderLayout.NORTH);

        jpNorth.setBackground(Color.WHITE);
        jpCenter.setBackground(Color.WHITE);
        jpSouth.setBackground(Color.WHITE);
        
        jbtCreate.addActionListener(new CreateListener());
        jbtRetrieve.addActionListener(new RetrieveListener());
        jbtUpdate.addActionListener(new UpdateListener());
        jbtDelete.addActionListener(new DeleteListener());
        jbtBack.addActionListener(new BackListener());
        jbtClear.addActionListener(new ClearListner());
        jbtViewData.addActionListener(new ViewDataListener());

        setTitle("Staff Form");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);

    }

    public static void main(String[] args) {
        new StaffFrame();
    }

    private class ViewDataListener implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            new StaffData();
            
        }
    }

    private class ClearListner implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            jtfStaffID.setText("");
            jtfStaffName.setText("");
            jtfStaffPhone.setText("");
            jtfStaffIC.setText("");
            jtfStaffPosition.setText("");
            jtfStaffPassword.setText("");

        }
    }

    private class BackListener implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            new MainMenu();
            dispose();
        }
    }

    private class DeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String staffID = jtfStaffID.getText();
            ResultSet rs = mainStaff.selectRecord(staffID);
            try {
                if (rs.next()) {
                    jtfStaffName.setText(rs.getString("StaffName"));
                    jtfStaffPhone.setText(rs.getString("StaffPhone"));
                    jtfStaffIC.setText((rs.getString("StaffIC")));
                    jtfStaffPosition.setText(rs.getString("StaffPosition"));
                    jtfStaffPassword.setText(rs.getString("StaffPassword"));

                    int option = JOptionPane.showConfirmDialog(null, "Are you sure?", "QUESTION", JOptionPane.YES_NO_OPTION);

                    if (option == JOptionPane.YES_OPTION) {
                        mainStaff.DeleteRecord(staffID);
                        clearTextField();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No Such Staff ID. ", "RECORD NOT FOUND", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    private class UpdateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String staffID = jtfStaffID.getText();
            ResultSet rs = mainStaff.selectRecord(staffID);
            try {
                if (rs.next()) {
                    jtfStaffName.setText(rs.getString("StaffName"));
                    jtfStaffPhone.setText(rs.getString("StaffPhone"));
                    jtfStaffIC.setText((rs.getString("StaffIC")));
                    jtfStaffPosition.setText(rs.getString("StaffPosition"));
                    jtfStaffPassword.setText(rs.getString("StaffPassword"));

                    int option = JOptionPane.showConfirmDialog(null, "Are you sure?", "QUESTION", JOptionPane.YES_NO_OPTION);

                    if (option == JOptionPane.YES_OPTION) {
                        mainStaff.DeleteRecord(staffID);
                        clearTextField();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No Such Member ID. ", "RECORD NOT FOUND", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    private class RetrieveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String staffID = jtfStaffID.getText();
            ResultSet rs = mainStaff.selectRecord(staffID);
            try {
                if (rs.next()) {
                    jtfStaffName.setText(rs.getString("StaffName"));
                    jtfStaffPhone.setText(rs.getString("StaffPhone"));
                    jtfStaffIC.setText((rs.getString("StaffIC")));
                    jtfStaffPosition.setText(rs.getString("StaffPosition"));
                    jtfStaffPassword.setText(rs.getString("StaffPassword"));
                } else {
                    JOptionPane.showMessageDialog(null, "Staff ID not exist!", "ERROR MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    private class CreateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String staffID = jtfStaffID.getText();
            String staffName = jtfStaffName.getText();
            String staffPhone = jtfStaffPhone.getText();
            String staffIC = jtfStaffIC.getText();
            String staffPosition = jtfStaffPhone.getText();
            String staffPassword = jtfStaffPassword.getText();
            ResultSet rs = mainStaff.selectRecord(staffID);
            try {
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "This staff ID already exists.", "DUPLICATE RECORD", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    mainStaff.CreateRecord(staffID, staffName, staffPhone, staffIC, staffPhone, staffPassword);
                    clearTextField();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    public void clearTextField() {
        jtfStaffID.setText("");
        jtfStaffName.setText("");
        jtfStaffPhone.setText("");
        jtfStaffIC.setText("");
        jtfStaffPosition.setText("");
        jtfStaffPassword.setText("");
    }
}
