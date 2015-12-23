/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import da.MembershipDA;
import domain.Membership;
import java.sql.ResultSet;

/**
 *
 * @author User
 */
public class MaintainMembership {
    
    private MembershipDA memDA;
    
    public MaintainMembership() {
        memDA = new MembershipDA();
    }

    public ResultSet selectRecord(String memId) {
        return memDA.selectRecord(memId);
    }

    public void createRecord(String memId, String memName, String memGender, String memIc, String memPhone, String memEmail, int memPoint) {
        memDA.createRecord(memId, memName, memGender, memIc, memPhone, memEmail, memPoint);
    }

    public void UpdateRecord(String memId, String memName, String memGender, String memIc, String memPhone, String memEmail) {
        memDA.UpdateRecord(memId, memName, memGender, memIc, memPhone, memEmail);
    }

    public void DeleteRecord(String memId) {
        memDA.DeleteRecord(memId);
    }
}
