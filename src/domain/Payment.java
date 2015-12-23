/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Yang
 */
public class Payment {

    private int ordId;
    private String foodId;
    private double price;

    public Payment() {

    }

    public Payment(int ordId, String foodId, double price) {
        this.ordId = ordId;
        this.foodId = foodId;
        this.price = price;
    }

    public int getOrdId() {
        return ordId;
    }

    public String getFoodId() {
        return foodId;
    }

    public double getPrice() {
        return price;
    }

    public void setOrdId(int ordId) {
        this.ordId = ordId;
    }

    public void setFoodId(String FoodId) {
        this.foodId = foodId;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
