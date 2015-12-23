/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import da.StaffDataTable;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.regex.PatternSyntaxException;
import javax.swing.*;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableModel;

public class StaffData extends JFrame {

    static final String DATABASE_URL = "jdbc:derby://localhost:1527/RestaurantDB";
    static final String USERNAME = "nbuser";
    static final String PASSWORD = "nbuser";
    static final String QUERY = "SELECT * FROM Staff";
    
    private StaffDataTable tableModel;
    private JTable resultTable;
    private TableRowSorter<TableModel> sorter;
    
    private JTextArea queryArea;
    private JTextField filterText = new JTextField();
   
    private JLabel filterLabel = new JLabel("Filter:");
    private JButton filterButton = new JButton("Search");
    private JButton jbtExit = new JButton("Exit");
     
    public StaffData() {
        

        try {
            tableModel = new StaffDataTable(DATABASE_URL, USERNAME, PASSWORD, QUERY);
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
    
            addWindowListener(new WindowCloseListener());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            tableModel.disconnectFromDatabase();
            System.exit(1);
        }
        
        setTitle("Staff Record");
        setSize(1300, 400);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private class ExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            dispose();
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

    public static void main(String[] args) {
        StaffData frame = new StaffData();
    }
}


