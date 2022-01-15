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
public class trainer {

    private String id;
    private String name;
    private String password;
    private LocalDate dob;
    private String gender;
    /**
     * *******
     */
    private String email;
    private String nrc;
    private String ph;//int
    private String address;
    private String status;
    private int fees;

    public trainer(String id, String name, String password, LocalDate dob, String gender, String email, String nrc, String ph, String address, String status, int fees) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.nrc = nrc;
        this.ph = ph;
        this.address = address;
        this.status = status;
        this.fees = fees;
    }
    
    public trainer(String name, String password, LocalDate dob, String gender, String email, String nrc, String ph, String address, String status) {
        this.name = name;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.nrc = nrc;
        this.ph = ph;
        this.address = address;
        this.status = status;
    }
    
    public trainer(String name, String password, LocalDate dob, String gender, String email, String nrc, String ph, String address, String status, int fees) {
        this.name = name;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.nrc = nrc;
        this.ph = ph;
        this.address = address;
        this.status = status;
        this.fees = fees;
    }

    public trainer(String id, String name, String password, LocalDate dob, String gender, String email, String nrc, String ph, String address) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.nrc = nrc;
        this.ph = ph;
        this.address = address;
    }
       

    public trainer() {
    }

    public trainer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }

    @Override
    public String toString() {
        return "trainer{" + "id=" + id + ", name=" + name + ", password=" + password + ", dob=" + dob + ", gender=" + gender + ", email=" + email + ", nrc=" + nrc + ", ph=" + ph + ", address=" + address + ", status=" + status + ", fees=" + fees + '}';
    }

//    public void setHeight(double nv) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    
    
}
