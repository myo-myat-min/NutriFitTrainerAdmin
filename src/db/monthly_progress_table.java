/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Soe min hein
 */
public class monthly_progress_table {

    public static void create_table() {
        String sql = "Create table monthly_progress ("
                + "id varchar(50) primary key ,"
                + "progress_date Date not null ,"
                + "member_id varchar(50) not null ,"
                + "workout_id  varchar(50) not null ,"
                + "FOREIGN KEY (`MEMBER_ID`) REFERENCES `member`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT ,"
                + "FOREIGN KEY (`WORKOUT_ID`) REFERENCES `workout`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT   );";

        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.execute();
            System.out.println("monthl_progress_table  created");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void insert_monthly_progress(monthly_progress mp, Member m, workout w) {
        String sql = "INSERT INTO `monthly_progress`( `progress_date`, `member_id`, `workout_id`)"
                + " VALUES (?,?,?)";

        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(mp.getProgress_date()));
            ps.setString(2, m.getId());
            ps.setInt(3, w.getId());
            ps.execute();
          
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public static ArrayList<monthly_progress> showall(){
        ArrayList<monthly_progress> mp=new ArrayList<>();
        String sql="Select * from monthly_progress";
        
        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                mp.add(new monthly_progress(rs.getInt(1), rs.getDate(2).toLocalDate(), rs.getInt(3), rs.getInt(4)));
            }
          return mp;
        } catch (Exception e) {
            e.printStackTrace();
        }
return null;
        
    }

    public static void main(String[] args) {
        //create_table();
        System.out.println(showall());
    }

}
