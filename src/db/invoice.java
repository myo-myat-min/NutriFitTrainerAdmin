/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.time.LocalDate;


/**
 *
 * @author Soe min hein
 */
public class invoice extends Member {

    private String Iid;
    private LocalDate invoice_date;
    private LocalDate end;
    private String member_id;
    private String membership_id;
    private String membership_name;
    private String admin_id;
    private String admin_name;
    private String price;

    public invoice(String Iid, LocalDate invoice_date, LocalDate end, String member_id, String membership_id, String admin_id, String id, String name, String password, LocalDate dob, String gender, int weight, int height, String email, String nrc, String ph, String address, String medical_data) {
        super(id, name, password, dob, gender, weight, height, email, nrc, ph, address, medical_data);
        this.Iid = id;
        this.invoice_date = invoice_date;
        this.end = end;
        this.member_id = member_id;
        this.membership_id = membership_id;
        this.admin_id = admin_id;
    }

    public invoice(String Iid, LocalDate invoice_date, LocalDate end, String member_id, String membership_id, String admin_id) {
        this.Iid = Iid;
        this.invoice_date = invoice_date;
        this.end = end;
        this.member_id = member_id;
        this.membership_id = membership_id;
        this.admin_id = admin_id;
    }

    public invoice(LocalDate end, String member_id, String membership_name, String name, String nrc) {
        super(name, nrc);
        this.end = end;
        this.member_id = member_id;
        this.membership_name = membership_name;
    }
    
    public invoice(String Iid, LocalDate invoice_date, LocalDate end, String membership_name, String id, String name, String email) {
        super(id, name, email);
        this.Iid = Iid;
        this.end = end;
        this.invoice_date = invoice_date;
        this.membership_name = membership_name;
    }
    
    public invoice(String Iid, LocalDate invoice_date, LocalDate end, String membership_name, String id, String name, String email, String adminName) {
        super(id, name, email);
        this.Iid = Iid;
        this.end = end;
        this.invoice_date = invoice_date;
        this.membership_name = membership_name;
        this.admin_name = adminName;
    }

    public invoice(String Iid, LocalDate invoice_date, LocalDate end, String membership_name, String id, String name, String email, String adminName, String price) {
        super(id, name, email);
        this.Iid = Iid;
        this.end = end;
        this.invoice_date = invoice_date;
        this.membership_name = membership_name;
        this.admin_name = adminName;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }
    
    public String getIid() {
        return Iid;
    }

    public void setIid(String Iid) {
        this.Iid = Iid;
    }

    public String getMembership_name() {
        return membership_name;
    }

    public void setMembership_name(String membership_name) {
        this.membership_name = membership_name;
    }



    public LocalDate getInvoice_date() {
        return invoice_date;
    }

    public void setInvoice_date(LocalDate invoice_date) {
        this.invoice_date = invoice_date;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getMembership_id() {
        return membership_id;
    }

    public void setMembership_id(String membership_id) {
        this.membership_id = membership_id;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    @Override
    public String toString() {
        return "invoice{" + "Iid=" + Iid + ", invoice_date=" + invoice_date + ", end=" + end + ", member_id=" + member_id + ", membership_id=" + membership_id + ", membership_name=" + membership_name + ", admin_id=" + admin_id + '}';
    }


        
    
}
