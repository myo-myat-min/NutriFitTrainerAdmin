/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

/**
 *
 * @author Soe min hein
 */
public class Membership {
    private String id;
    private String name;
    private int price;

    public Membership(String id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Membership(String name, int price) {
        this.name = name;
        this.price = price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Membership{" + "id=" + id + ", name=" + name + ", price=" + price + '}';
    }
    
    
    
}
