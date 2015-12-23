/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author User
 */
public class Food {
    
    private String fId;
    private String fName;
    private String fType;
    private double fPrice;
    
    public Food(){
        
    }
    
    public Food(String fId, String fName, String fType, double fPrice){
        this.fId = fId;
        this.fName = fName;
        this.fType = fType;
        this.fPrice = fPrice;
    }
    
    public String getFId(){
        return fId;
    }
    
    public String getFName(){
        return fName;
    }
    
    public String getFType(){
        return fType;
    }    
    
    public double getFPrice(){
        return fPrice;
    }
    
    public void setFId(String fId) {
        this.fId = fId;
    }
    
    public void setFName(String fName){
        this.fName = fName;
    }
    
    public void setFType(String fType){
        this.fType = fType;
    }
    
    public void setFPrice(double fPrice){
        this.fPrice = fPrice;
    }
    
    
}
