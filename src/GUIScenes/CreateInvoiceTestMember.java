/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIScenes;

import java.time.LocalDate;

/**
 *
 * @author ACER
 */
public class CreateInvoiceTestMember {
    
    int id;
    String memberName;
    String packageMonth;
    String nrc;
    LocalDate endDate;

    public CreateInvoiceTestMember(int id, String memberName, String packageMonth, String nrc, LocalDate endDate) {
        this.id = id;
        this.memberName = memberName;
        this.packageMonth = packageMonth;
        this.nrc = nrc;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPackageMonth() {
        return packageMonth;
    }

    public void setPackageMonth(String packageMonth) {
        this.packageMonth = packageMonth;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    
}
