/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import javax.swing.JOptionPane;

/**
 *
 * @author Yang
 */
public class PaymentSelection {

    public PaymentSelection() {
        int option = JOptionPane.showConfirmDialog(null, "Customer is a member?", "QUESTION", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            new PaymentFrame();
        } else if(option == JOptionPane.NO_OPTION) {
            new PaymentFrame2();
        }
    }

    public static void main(String[] args){
        new PaymentSelection();
    }
}
