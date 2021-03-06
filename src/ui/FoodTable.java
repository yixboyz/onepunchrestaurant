/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

/**
 *
 * @author User
 */

import da.FoodTableModel;
import java.awt.*;
import java.awt.event.*;
import static java.lang.System.exit;
import java.sql.SQLException;
import java.util.regex.PatternSyntaxException;
import javax.swing.*;
import javax.swing.table.*;
public class FoodTable extends JFrame{

    static final String host = "jdbc:derby://localhost:1527/RestaurantDB";
    static final String user = "nbuser";
    static final String password = "nbuser";
    static final String QUERY = "SELECT * FROM FOOD";
    
    private FoodTableModel tableModel;
    private JTable resultTable;
    private TableRowSorter<TableModel> sorter;

    private JTextArea queryArea;
    private JTextField filterText = new JTextField();

    private JLabel filterLabel = new JLabel("Filter:");
    private JButton filterButton = new JButton("Search");
    private JButton jbtExit = new JButton("Exit");
    
    public FoodTable(){
        
        try {
            tableModel = new FoodTableModel(host, user, password, QUERY);
            resultTable = new JTable(tableModel);

            Box boxNorth = Box.createHorizontalBox();
            boxNorth.add(filterLabel);
            boxNorth.add(filterText);
            boxNorth.add(filterButton);
            filterButton.addActionListener(new FilterButtonListener());

            JPanel jpSouth = new JPanel();
            jpSouth.add(jbtExit);
            jbtExit.addActionListener(new ExitListener());

            add(new JScrollPane(resultTable), BorderLayout.CENTER);
            add(boxNorth, BorderLayout.NORTH);
            add(jpSouth, BorderLayout.SOUTH);

            sorter = new TableRowSorter<TableModel>(tableModel);
            resultTable.setRowSorter(sorter);

            setTitle("Staff Record");
            setSize(1300, 400);
            setVisible(true);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            addWindowListener(new WindowCloseListener());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            tableModel.disconnectFromDatabase();
            System.exit(1);
        }
    }
   
    private class FilterButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String text = filterText.getText();
            if (text.length() == 0) {
                sorter.setRowFilter(null);
            } else {
                try {
                    sorter.setRowFilter(RowFilter.regexFilter(text));
                } catch (PatternSyntaxException pse) {
                    JOptionPane.showMessageDialog(null, "Bad regex pattern", "Bad regex pattern", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private class WindowCloseListener extends WindowAdapter {

        @Override
        public void windowClosed(WindowEvent event) {
            tableModel.disconnectFromDatabase();
            dispose();
        }
    }

    private class ExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            dispose();
        }
    }

    public static void main(String[] args) {
       new FoodTable();
    }
}
