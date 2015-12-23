/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author User
 */
public class OrderModel {

    private Calendar date;
    private static int nextOrderID = 1001;
    private int orderID;
   

    public OrderModel() {
        this.date = new GregorianCalendar();
        this.orderID = nextOrderID++;
    }

    public static int getNextOrderID() {
        return nextOrderID;
    }

    public int getOrderID() {
        return orderID;
    }

    public Calendar getDate() {
        return date;
    }
    
 
}
