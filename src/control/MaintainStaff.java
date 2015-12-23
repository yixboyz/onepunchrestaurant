package control;

import da.StaffDA;
import java.sql.ResultSet;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class MaintainStaff {
    
    private StaffDA staffDA;
    
    public MaintainStaff(){
        staffDA = new StaffDA();
    }
    
    public ResultSet selectRecord(String staffID){
        return staffDA.selectRecord(staffID);
    }
    
    public void CreateRecord(String staffID, String staffName, String staffPhone, String staffIC, String staffPosition, String staffPassword){
        staffDA.CreateRecord(staffID, staffName, staffPhone, staffIC, staffPosition, staffPassword);
    }
    
    public void UpdateRecord(String staffID, String staffName, String staffPhone, String staffIC, String staffPosition, String staffPassword){
        staffDA.UpdateRecord(staffName, staffPhone, staffIC, staffPosition, staffPassword);
    }
    
    public void DeleteRecord(String staffID){
        staffDA.DeleteRecord(staffID);
    }
}
