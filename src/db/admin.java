/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

public class admin {

    private String id;
    private String name;
    private String password;
    private String phone;
    private String status;
    private String email;

    public admin(String id, String name, String password, String email, String phone, String status) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.status = status;
        this.email = email;
    }
    
    public admin(String name,String password, String email, String phone, String status) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.status = status;
        this.email = email;
    }
    
//    public admin(String name, String email, String phone, String status) {
//        this.name = name;
//        this.phone = phone;
//        this.status = status;
//        this.email = email;
//    }

    public admin(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    @Override
    public String toString() {
        return "admin{" + "id=" + id + ", name=" + name + ", password=" + password + '}';
    }
    
    
    
    
}
