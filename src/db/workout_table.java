/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.ByteArrayInputStream;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Soe min hein
 */
public class workout_table {

    public static void create_table() {

        String sql = "Create table workout("
                + "id int(50) primary key autoincrement ,"
                + "name varchar(50) not null ,"
                + "video varchar(60) not null ,"
                + "burnt_calorie float not null , "
                + "workout_type varchar(15) , "
                + "effected_muscle varchar(40) ,"
                + "trainer_id  varchar(50) not null ,"
                + "image longblob ,"
                + "FOREIGN KEY (`TRAINER_ID`) REFERENCES `trainer`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT )";
        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.execute();
            System.out.println("workout table created");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void insert_video(workout w) {

        String sql = "Select id from trainer where email=?";

        String sql1 = "INSERT INTO `workout`( `name`, `video`, `burnt_calorie`, `workout_type`, `effected_muscle`, `trainer_id`, `image`) "
                + "VALUES (?,?,?,?,?,?,?)";
        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareCall(sql);
                PreparedStatement ps1 = con.prepareStatement(sql1)) { 
            
             ps.setString(1, w.getEmail());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                ps1.setString(1, w.getName());
                ps1.setString(2, w.getWorkout_video());
                ps1.setFloat(3, w.getBurnt_calorie());
                ps1.setString(4, w.getWorkout_type());
                ps1.setString(5, w.getEffectedMuscle());
                ps1.setString(6, rs.getString(1));
                ps1.setBlob(7, new ByteArrayInputStream(w.getImage()));

            }
            ps1.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<workout> showall() {
        ArrayList<workout> w = new ArrayList<>();
        String sql = "Select * from workout left join trainer on trainer.id = workout.trainer_id";
        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Blob image = rs.getBlob("image");
                byte[] i = image.getBytes(1, (int) image.length());
                w.add(new workout(rs.getInt("workout.id"), rs.getString("workout.name"), rs.getString("workout.video"),
                        rs.getString("workout.workout_type"), rs.getFloat("workout.burnt_calorie"), rs.getString("trainer.name"),
                        rs.getString("effected_muscle"), rs.getString("trainer.email"), i));

            }
            return w;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public static ArrayList<workout> showLooseWeight() {
        ArrayList<workout> w = new ArrayList<>();
        String sql = "Select * from workout left join trainer on trainer.id = workout.trainer_id WHERE workout_type = 'Loose Weight'";
        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Blob image = rs.getBlob("image");
                byte[] i = image.getBytes(1, (int) image.length());
                w.add(new workout(rs.getInt("workout.id"), rs.getString("workout.name"), rs.getString("workout.video"),
                        rs.getString("workout.workout_type"), rs.getFloat("workout.burnt_calorie"), rs.getString("trainer.name"),
                        rs.getString("effected_muscle"), rs.getString("trainer.email"), i));

            }
            return w;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public static ArrayList<workout> showGainWeight() {
        ArrayList<workout> w = new ArrayList<>();
        String sql = "Select * from workout left join trainer on trainer.id = workout.trainer_id WHERE workout_type = 'Gain Weight'";
        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Blob image = rs.getBlob("image");
                byte[] i = image.getBytes(1, (int) image.length());
                w.add(new workout(rs.getInt("workout.id"), rs.getString("workout.name"), rs.getString("workout.video"),
                        rs.getString("workout.workout_type"), rs.getFloat("workout.burnt_calorie"), rs.getString("trainer.name"),
                        rs.getString("effected_muscle"), rs.getString("trainer.email"), i));

            }
            return w;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public static ArrayList<workout> showGetFitter() {
        ArrayList<workout> w = new ArrayList<>();
        String sql = "Select * from workout left join trainer on trainer.id = workout.trainer_id WHERE workout_type = 'Get Fitter'";
        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Blob image = rs.getBlob("image");
                byte[] i = image.getBytes(1, (int) image.length());
                w.add(new workout(rs.getInt("workout.id"), rs.getString("workout.name"), rs.getString("workout.video"),
                        rs.getString("workout.workout_type"), rs.getFloat("workout.burnt_calorie"), rs.getString("trainer.name"),
                        rs.getString("effected_muscle"), rs.getString("trainer.email"), i));

            }
            return w;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public static void delete_video(int id) {

        String sql = "DELETE FROM `workout` WHERE id=?";

        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareCall(sql);) {

            ps.setInt(1, id);
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
//        create_table();
//        showall();
    }
}
