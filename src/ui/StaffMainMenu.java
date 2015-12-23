package ui;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
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
public class StaffMainMenu extends JFrame {

    private ImageIcon logo = new ImageIcon("C:\\Users\\User\\Desktop\\Assignment\\src\\image\\flat,800x800,075,t.u4.jpg");
    private JLabel jlbllogo = new JLabel();
    private JLabel jlblTitle = new JLabel("Welcome");
    private JButton jbStaff = new JButton("Staff");
    private JButton jbMembership = new JButton("Membership");
    private JButton jbMenu = new JButton("Food Menu");
    private JButton jbOrder = new JButton("Make Order");
    private JButton jbPayment = new JButton("Make Payment");
    private JButton jbLogout = new JButton("LogOut");
    
    private JMenuItem jmiStaff, jmiMember, jmiFood, jmiAbout, jmiHelp, jmiLogOut, jmiOrder, jmiPayment;

    public StaffMainMenu() {
        JPanel jpNorth = new JPanel();
        jlblTitle.setFont(new Font("Times New Roman", Font.BOLD, 40));
        jlbllogo.setIcon(logo);
        jpNorth.add(jlbllogo);
        jpNorth.add(jlblTitle);
        add(jpNorth, BorderLayout.NORTH);

        JPanel jpCenter = new JPanel(new GridLayout(5,1));
        jpCenter.setBorder(new TitledBorder("Edit Section"));
        jpCenter.add(jbStaff);
        jpCenter.add(jbMembership);
        jpCenter.add(jbMenu);
        jpCenter.add(jbOrder);
        jpCenter.add(jbPayment);
        add(jpCenter,BorderLayout.CENTER);
        
        JPanel jpSouth = new JPanel();
        jpSouth.add(jbLogout);
        add(jpSouth, BorderLayout.SOUTH);
        
        jpNorth.setBackground(Color.WHITE);
        jpCenter.setBackground(Color.WHITE);
        jpSouth.setBackground(Color.WHITE);
        
        jbStaff.addActionListener(new StaffListener());
        jbMembership.addActionListener(new MembershipListener());
        jbMenu.addActionListener(new MenuListener());
        jbOrder.addActionListener(new OrderListener());
        jbPayment.addActionListener(new PaymentListener());
        jbLogout.addActionListener(new LogoutListner());
        
        JMenuBar jmb = new JMenuBar();
        setJMenuBar(jmb);
        
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');
        jmb.add(fileMenu);
        
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic('H');
        jmb.add(helpMenu);
        
        fileMenu.add(jmiStaff = new JMenuItem("Staff",'S'));
        fileMenu.add(jmiMember = new JMenuItem("Membership",'M'));
        fileMenu.add(jmiFood = new JMenuItem("Food Menu",'F'));
        fileMenu.add(jmiOrder = new JMenuItem("Order",'O'));
        fileMenu.add(jmiPayment = new JMenuItem("Payment",'P'));
        fileMenu.add(jmiLogOut = new JMenuItem("Exit",'E'));
        helpMenu.add(jmiHelp = new JMenuItem("Help Content",'H'));
        helpMenu.add(jmiAbout = new JMenuItem("About us",'A'));
        
        jmiStaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(null, "Not allow to peformed this function with current account!");
            }
        });
        
        jmiMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(null, "Not allow to peformed this function with current account!");
            }
        });
        
        jmiFood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(null, "Not allow to peformed this function with current account!");
            }
        });
        
        jmiLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new Login();
                dispose();
            }
        });
        
        jmiHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                HelpContent help = new HelpContent();
                help.pack();
                help.setVisible(true);
                help.setLocationRelativeTo(null);
                help.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
        
        jmiAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                AboutUs about = new AboutUs();
                about.pack();
                about.setVisible(true);
                about.setLocationRelativeTo(null);
                about.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
        
        jmiOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new OrderFrame();
                dispose();
            }
        });
        
        jmiPayment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new PaymentSelection(); 
                dispose();
            }
        });

        setTitle("Main Menu");
        setSize(500,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }
    
    public static void main(String [] args){
        new StaffMainMenu();
    }

    private class StaffListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            JOptionPane.showMessageDialog(null, "Not allow to peformed this function with current account!");
        }
    }

    private class MembershipListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            JOptionPane.showMessageDialog(null, "Not allow to peformed this function with current account!");
        }
    }

    private class MenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            JOptionPane.showMessageDialog(null, "Not allow to peformed this function with current account!");
        }
    }

    private class OrderListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            new OrderFrame();
            dispose();
        }
    }

    private class PaymentListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            new PaymentSelection();
            dispose();
        }
    }

    private class LogoutListner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            new Login();
            dispose();
            JOptionPane.showMessageDialog(null, "Thank You!");
            
        }
    }

}
