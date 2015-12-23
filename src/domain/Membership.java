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
public class Membership {

    private String memId;
    private String memName;
    private String memGender;
    private String memIc;
    private String memPhone;
    private String memEmail;
    private int memPoint;

    public Membership() {

    }

    public Membership(String memId, String memName, String memGender, String memIc, String memPhone, String memEmail, int memPoint) {
        this.memId = memId;
        this.memName = memName;
        this.memGender = memGender;
        this.memIc = memIc;
        this.memPhone = memPhone;
        this.memEmail = memEmail;
        this.memPoint = memPoint;
    }

    public String getMemId() {
        return memId;
    }

    public String getMemName() {
        return memName;
    }

    public String getMemGender() {
        return memGender;
    }

    public String getMemIc() {
        return memIc;
    }

    public String getMemPhone() {
        return memPhone;
    }

    public String getMemEmail() {
        return memId;
    }

    public int getMemPoint() {
        return memPoint;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }

    public void setMemGender(String memGender) {
        this.memGender = memGender;
    }

    public void setMemIc(String memIc) {
        this.memIc = memIc;
    }

    public void setMemPhone(String memPhone) {
        this.memPhone = memPhone;
    }

    public void setMemEmail(String memEmail) {
        this.memEmail = memEmail;
    }

    public void setMemPoint(int memPoint) {
        this.memPoint = memPoint;
    }

}
