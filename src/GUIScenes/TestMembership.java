/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIScenes;

/**
 *
 * @author ACER
 */
public class TestMembership {
    
    int id;
    String packageMonth;
    double price;

    public TestMembership(int id, String packageMonth, double price) {
        this.id = id;
        this.packageMonth = packageMonth;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPackageMonth() {
        return packageMonth;
    }

    public void setPackageMonth(String packageMonth) {
        this.packageMonth = packageMonth;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
    
}
