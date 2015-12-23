/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import control.MaintainMembership;
import domain.Membership;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 *
 * @author User
 */
public class MembershipFrame extends JFrame {

    private MaintainMembership mainMem;

    private JButton jbtCreate = new JButton("Create");
    private JButton jbtRetrieve = new JButton("Retrieve");
    private JButton jbtUpdate = new JButton("Update");
    private JButton jbtDelete = new JButton("Delete");
    private JButton jbtBack = new JButton("Back");
    private JButton jbtRecord = new JButton("View All Record");

    private JLabel jlblId = new JLabel("Membership ID");
    private JLabel jlblName = new JLabel("Customer Name");
    private JLabel jlblGender = new JLabel("Customer Gender");
    private JLabel jlblIc = new JLabel("Customer IC");
    private JLabel jlblPhone = new JLabel("Customer Phone");
    private JLabel jlblEmail = new JLabel("Customer Email");
    private JLabel jlblPoint = new JLabel("Customer Point");

    private JTextField jtfId = new JTextField();
    private JTextField jtfName = new JTextField();
    private JTextField jtfGender = new JTextField();
    private JTextField jtfIc = new JTextField();
    private JTextField jtfPhone = new JTextField();
    private JTextField jtfEmail = new JTextField();
    private JTextField jtfPoint = new JTextField();

    public MembershipFrame() {

        mainMem = new MaintainMembership();

        JPanel jpNorth = new JPanel();
        jpNorth.add(jbtBack);
        jpNorth.add(jbtRecord);
        add(jpNorth, BorderLayout.NORTH);

        jtfPoint.setEditable(false);
        JPanel jpCenter = new JPanel(new GridLayout(7, 7));
        jpCenter.setBorder(new TitledBorder("Membership Detail"));
        jpCenter.add(jlblId);
        jpCenter.add(jtfId);
        jpCenter.add(jlblName);
        jpCenter.add(jtfName);
        jpCenter.add(jlblGender);
        jpCenter.add(jtfGender);
        jpCenter.add(jlblIc);
        jpCenter.add(jtfIc);
        jpCenter.add(jlblPhone);
        jpCenter.add(jtfPhone);
        jpCenter.add(jlblEmail);
        jpCenter.add(jtfEmail);
        jpCenter.add(jlblPoint);
        jpCenter.add(jtfPoint);
        add(jpCenter, BorderLayout.CENTER);

        JPanel jpSouth = new JPanel();
        jpSouth.add(jbtCreate);
        jpSouth.add(jbtRetrieve);
        jpSouth.add(jbtUpdate);
        jpSouth.add(jbtDelete);
        add(jpSouth, BorderLayout.SOUTH);

        jbtCreate.addActionListener(new CreateListener());
        jbtRetrieve.addActionListener(new RetrieveListener());
        jbtUpdate.addActionListener(new UpdateListener());
        jbtDelete.addActionListener(new DeleteListener());
        jbtBack.addActionListener(new BackListener());
        jbtRecord.addActionListener(new RecordListener());
        
        jpNorth.setBackground(Color.WHITE);
        jpCenter.setBackground(Color.WHITE);
        jpSouth.setBackground(Color.WHITE);

        setTitle("Membership Form");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MembershipFrame();
    }

    private class CreateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String memId = jtfId.getText();
            String memName = jtfName.getText();
            String memGender = jtfGender.getText();
            String memIc = jtfIc.getText();
            String memPhone = jtfPhone.getText();
            String memEmail = jtfEmail.getText();
            int memPoint = 0;
            ResultSet rs = mainMem.selectRecord(memId);
            try {
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "This member ID already exists.", "DUPLICATE RECORD", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    mainMem.createRecord(memId, memName, memGender, memIc, memPhone, memEmail, memPoint);
                    clearTextField();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    private class RetrieveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String memId = jtfId.getText();
            ResultSet rs = mainMem.selectRecord(memId);
            try {
                if (rs.next()) {
                    jtfName.setText(rs.getString("MemberName"));
                    jtfGender.setText(rs.getString("MemberGender"));
                    jtfIc.setText((rs.getString("MemberIc")));
                    jtfPhone.setText(rs.getString("MemberPhone"));
                    jtfEmail.setText(rs.getString("MemberEmail"));
                    jtfPoint.setText(rs.getString("MemberPoint"));
                } else {
                    JOptionPane.showMessageDialog(null, "Member ID not exist!", "ERROR MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }

    }

    private class UpdateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String memId = jtfId.getText();
            String memName = jtfName.getText();
            String memGender = jtfGender.getText();
            String memIc = jtfIc.getText();
            String memPhone = jtfPhone.getText();
            String memEmail = jtfEmail.getText();

            ResultSet rs = mainMem.selectRecord(memId);
            try {
                if (rs.next()) {
                    mainMem.UpdateRecord(memId, memName, memGender, memIc, memPhone, memEmail);
                    clearTextField();
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
            String memId = jtfId.getText();
            ResultSet rs = mainMem.selectRecord(memId);
            try {
                if (rs.next()) {
                    jtfName.setText(rs.getString("MemberName"));
                    jtfGender.setText(rs.getString("MemberGender"));
                    jtfIc.setText((rs.getString("MemberIc")));
                    jtfPhone.setText(rs.getString("MemberPhone"));
                    jtfEmail.setText(rs.getString("MemberEmail"));
                    jtfPoint.setText(rs.getString("MemberPoint"));

                    int option = JOptionPane.showConfirmDialog(null, "Are you sure?", "QUESTION", JOptionPane.YES_NO_OPTION);

                    if (option == JOptionPane.YES_OPTION) {
                        mainMem.DeleteRecord(memId);
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

    private class BackListener implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            new MainMenu();
            dispose();
        }
    }

    private class RecordListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            new MembershipTable();
        }
    }

    public void clearTextField() {
        jtfId.setText("");
        jtfName.setText("");
        jtfGender.setText("");
        jtfIc.setText("");
        jtfPhone.setText("");
        jtfEmail.setText("");
        jtfPoint.setText("");
    }

}
