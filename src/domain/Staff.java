package domain;


import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class Staff implements Serializable{
    private String staffID;
    private String staffName;
    private String staffPhone;
    private String staffIC;
    private String staffPosition;
    private String staffPassword;
    
    public Staff(){
    }
    
    public Staff(String staffID, String staffName, String staffPhone, String staffIC, String staffPosition, String staffPassword){
        this.staffID = staffID;
        this.staffName = staffName;
        this.staffPhone = staffPhone;
        this.staffIC = staffIC;
        this.staffPosition = staffPosition;
        this.staffPassword = staffPassword;
        
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffPhone() {
        return staffPhone;
    }

    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone;
    }

    public String getStaffIC() {
        return staffIC;
    }

    public void setStaffIC(String staffIC) {
        this.staffIC = staffIC;
    }

    public String getStaffPosition() {
        return staffPosition;
    }

    public void setStaffPosition(String staffPosition) {
        this.staffPosition = staffPosition;
    }

    public String getStaffPassword() {
        return staffPassword;
    }

    public void setStaffPassword(String staffPassword) {
        this.staffPassword = staffPassword;
    }
    
    
    
}
