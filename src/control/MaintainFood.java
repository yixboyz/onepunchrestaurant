/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import da.FoodDA;
import domain.Food;
import java.sql.ResultSet;

/**
 *
 * @author User
 */
public class MaintainFood {

    private FoodDA fDA;

    public MaintainFood() {
        fDA = new FoodDA();
    }

    public ResultSet selectRecord(String fId) {
        return fDA.selectRecord(fId);
    }

    public void createRecord(String fId, String fName, String fType, double fPrice) {
        fDA.createRecord(fId, fName, fType, fPrice);
    }

    public void UpdateRecord(String fId, String fName, String fType, double fPrice) {
        fDA.UpdateRecord(fId, fName, fType, fPrice);
    }

    public void DeleteRecord(String fId) {
        fDA.DeleteRecord(fId);
    }
}
