/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import da.PaymentDA;
import domain.Payment;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import ui.MainMenu;
import ui.PaymentFrame;

/**
 *
 * @author Yang
 */
public class PaymentFrame2 extends JFrame {

    private JLabel jlblOrdId = new JLabel("Order ID");
    private JLabel jlblPrice = new JLabel("Total Amount(RM)");
    private JLabel jlblReceive = new JLabel("Receive Amount(RM)");
    private JLabel jlblBal = new JLabel("Balance Due(RM)");

    private JTextField jtfOrdId = new JTextField(5);
    private JTextField jtfPrice = new JTextField(5);
    private JTextField jtfReceive = new JTextField(5);
    private JTextField jtfBal = new JTextField(5);

    private JButton jbBack = new JButton("Back");
    private JButton jbCal = new JButton("Calculate");
    private JButton jbPay = new JButton("Make Payment");

    private PaymentDA paymentDA;
    private ArrayList<Payment> payment;
    private static double price1;
    private static double totalPrice;
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    public PaymentFrame2() {
        paymentDA = new PaymentDA();
        JPanel jpEast = new JPanel(new GridLayout(4, 2));
        jpEast.add(jlblOrdId);
        jpEast.add(jtfOrdId);
        jpEast.add(jlblPrice);
        jpEast.add(jtfPrice);
        jtfPrice.setEditable(false);
        jpEast.add(jlblReceive);
        jpEast.add(jtfReceive);
        jpEast.add(jlblBal);
        jpEast.add(jtfBal);
        add(jpEast, BorderLayout.CENTER);

        JPanel jpSouth = new JPanel();
        jpSouth.add(jbBack);
        jpSouth.add(jbCal);
        jpSouth.add(jbPay);
        add(jpSouth, BorderLayout.SOUTH);

        jbBack.addActionListener(new BackListener());
        jbCal.addActionListener(new CalListener());
        jbPay.addActionListener(new PayListener());
        
        jpEast.setBackground(Color.WHITE);
        jpSouth.setBackground(Color.WHITE);

        setTitle("Payment System");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new PaymentFrame2();
    }

    private void calculatePrice() {
        int ordId = Integer.parseInt(jtfOrdId.getText());
        payment = paymentDA.getPayment(ordId);

        for (int i = 0; i < payment.size(); ++i) {
            price1 = payment.get(i).getPrice();
            totalPrice = totalPrice + price1;

        }
    }

    private class PayListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            jtfPrice.setText("" + String.format("%.2f", totalPrice));
            double price = Double.parseDouble(jtfPrice.getText());
            double payprice = Double.parseDouble(jtfReceive.getText());
            double balance = payprice - price;
            jtfBal.setText("" + String.format("%.2f", balance));
            JOptionPane.showMessageDialog(null, "Payment Success","SUCCESS MESSAGE",JOptionPane.INFORMATION_MESSAGE);
            clearTextField();

        }
    }

    private void clearTextField() {

        jtfOrdId.setText("");
        jtfPrice.setText("");
        jtfReceive.setText("");
        jtfBal.setText("");
    }

    private class CalListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            calculatePrice();
            jtfPrice.setText("" + String.format("%.2f", totalPrice));

        }

    }

    private class BackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            new MainMenu();
            dispose();
        }
    }
}
