/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import domain.OrderModel;
import da.FoodDA;
import domain.Food;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import java.util.*;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class OrderFrame extends JFrame {

    private JLabel jlblOrdId = new JLabel("Order ID");
    private JLabel jlblTableNo = new JLabel("Table No");
    private JLabel jlblMenuName = new JLabel("Food & Beverage");
    private JLabel jlblDate = new JLabel("Date");
    private JLabel jlblQty = new JLabel("Quantity");
    private JLabel jlblPrice = new JLabel("Price(RM)");
    private JLabel jlblTotalPrice = new JLabel("Total Price(RM)");

    private DefaultComboBoxModel model = new DefaultComboBoxModel();
    private JComboBox jcboMenuName = new JComboBox(model);

    private JTextField jtfOrdId = new JTextField(5);
    private JTextField jtfTableNo = new JTextField(5);
    private JTextField jtfDate = new JTextField(10);
    private JTextField jtfQty = new JTextField(15);
    private JTextField jtfPrice = new JTextField(15);
    private JTextField jtfTotalPrice = new JTextField(15);
    private JTextField jtfFoodId = new JTextField(5);

    private FoodDA foodDA;
    private ArrayList<Food> food1;
    private ResultSet rs;

    private JButton jbtBack = new JButton("Back");
    private JButton jbtCal = new JButton("Calculate");
    private JButton jbtOrder = new JButton("Order");
    private JButton jbtNew = new JButton("New Order");

    private String host = "jdbc:derby://localhost:1527/RestaurantDB";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "Ordering";
    private String tableName2 = "OrderFood";
    private Connection conn;
    private PreparedStatement stmt;
    private static int counter = 0;
    private OrderModel newOrder;

    public OrderFrame() {

        foodDA = new FoodDA();
        initializeModel();
        createConnection();

        Font largeFont = new Font("TimesRoman", Font.BOLD, 18);
        Font largeFont2 = new Font("TimesRoman", Font.TRUETYPE_FONT, 15);
        jlblOrdId.setFont(largeFont2);
        jlblDate.setFont(largeFont2);
        jtfOrdId.setFont(largeFont);
        jtfDate.setFont(largeFont);

        JPanel jpNorth = new JPanel();
        jpNorth.add(jlblOrdId);
        jpNorth.add(jtfOrdId);
        jtfOrdId.setEditable(false);
        jpNorth.add(jlblDate);
        jpNorth.add(jtfDate);
        jtfDate.setEditable(false);
        add(jpNorth, BorderLayout.NORTH);
        OrderModel orderM = new OrderModel();
        jtfOrdId.setText("" + orderM.getOrderID());
        Calendar date = orderM.getDate();
        jtfDate.setText(date.get(Calendar.DATE) + " "
                + date.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH)
                + " " + date.get(Calendar.YEAR));

        JPanel jpWest = new JPanel(new GridLayout(5, 2));
        Font largeFont3 = new Font("TimesRoman", Font.TRUETYPE_FONT, 16);
        jlblTableNo.setFont(largeFont);
        jtfTableNo.setFont(largeFont3);
        jpWest.add(jlblTableNo);
        jpWest.add(jtfTableNo);
        jlblMenuName.setFont(largeFont);
        jcboMenuName.setFont(largeFont3);
        jpWest.add(jlblMenuName);
        jpWest.add(jcboMenuName);
        jlblQty.setFont(largeFont);
        jtfQty.setFont(largeFont3);
        jpWest.add(jlblQty);
        jpWest.add(jtfQty);
        jlblPrice.setFont(largeFont);
        jtfPrice.setFont(largeFont3);
        jpWest.add(jlblPrice);
        jpWest.add(jtfPrice);
        jtfPrice.setEditable(false);
        jlblTotalPrice.setFont(largeFont);
        jtfTotalPrice.setFont(largeFont3);
        jpWest.add(jlblTotalPrice);
        jpWest.add(jtfTotalPrice);
        jtfTotalPrice.setEditable(false);
        add(jpWest, BorderLayout.CENTER);

        JPanel jpSouth = new JPanel();
        jpSouth.add(jbtBack);
        jpSouth.add(jbtCal);
        jpSouth.add(jbtOrder);
        jpSouth.add(jbtNew);
        add(jpSouth, BorderLayout.SOUTH);

        jcboMenuName.addActionListener(new SelectNameListener());
        jbtBack.addActionListener(new BackListener());
        jbtCal.addActionListener(new CalListener());
        jbtOrder.addActionListener(new OrderListener());
        jbtNew.addActionListener(new NewListener());
        
        jpNorth.setBackground(Color.WHITE);
        jpWest.setBackground(Color.WHITE);
        jpSouth.setBackground(Color.WHITE);

        setTitle("Order System");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new OrderFrame();
    }

    private void initializeModel() {
        food1 = foodDA.getFoods();
        for (int i = 0; i < food1.size(); ++i) {
            model.addElement(food1.get(i).getFName());
        }
    }

    private class NewListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            jtfTableNo.setText("");
            clearTextField();
            createOrder();
            counter = 0;
        }
    }

    public void createOrder() {
        newOrder = new OrderModel();
        jtfOrdId.setText("" + newOrder.getOrderID());
        Calendar date = newOrder.getDate();
        jtfDate.setText(date.get(Calendar.DATE) + " "
                + date.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH)
                + " " + date.get(Calendar.YEAR));

    }

    private class OrderListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String insertStr2 = "Insert INTO " + tableName2 + " VALUES (?,?,?)";
            try {
                if (counter == 0) {
                    storeData();
                    counter++;
                }
                stmt = conn.prepareStatement(insertStr2);
                stmt.setString(1, jtfOrdId.getText());
                stmt.setString(2, jtfFoodId.getText());
                stmt.setString(3, jtfTotalPrice.getText());
                stmt.executeUpdate();
                clearTextField();

            } catch (SQLException ex) {
                Logger.getLogger(OrderFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void clearTextField() {
        jtfQty.setText("");
        jtfPrice.setText("");
        jtfTotalPrice.setText("");
    }

    private void storeData() {
        String insertStr1 = "Insert INTO " + tableName + " VALUES (?,?,?)";
        try {
            stmt = conn.prepareStatement(insertStr1);
            stmt.setString(1, jtfOrdId.getText());
            stmt.setString(2, jtfTableNo.getText());
            stmt.setString(3, jtfDate.getText());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private class CalListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            int qty = Integer.parseInt(jtfQty.getText());
            double price = Double.parseDouble(jtfPrice.getText());
            double total = qty * price;
            jtfTotalPrice.setText("" + String.format("%.2f", total));

        }
    }

    private class BackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            dispose();
            new MainMenu();
        }
    }

    private class SelectNameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            Food food = food1.get(jcboMenuName.getSelectedIndex());
            setTextField(food);

        }

        private void setTextField(Food food) {
            jtfPrice.setText("" + String.format("%.2f", food.getFPrice()));
            jtfFoodId.setText(food.getFId());
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
