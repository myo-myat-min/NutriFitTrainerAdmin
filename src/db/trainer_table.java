/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import GUIScenes.TrainerSignUpController;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Soe min hein
 */
public class trainer_table {

    public static void create_table() {

        String sql = "Create table trainer("
                + "id varchar(50) Primary key,"
                + "name Varchar(50) NOT NULL,"
                + "password Varchar(50) NOT NULL ,"
                + "dob Date NOT NULL,"
                + "gender Varchar(6) NOT NULL,"
                + "email Varchar(50) NOT NULL Unique, "
                + "nrc Varchar(50) NOT NULL Unique,"
                + "phone_no Varchar(50) NOT NULL,"
                + "address Varchar(150) NOT NULL,"
                + "status Varchar(20) not null, "
                + "fees INT not null );";

        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.execute();
            System.out.println("trainer table created");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert_trainer(trainer t) throws SQLException {

        String id = "T-00001";
        String sql = "INSERT INTO `trainer`(`id`, `name`, `password`, `dob`, `gender`,  `email`, `nrc`, `phone_no`, `address`, `status` ,`fees`) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        String sql1 = "Select id from trainer order by id desc limit 1";

        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                PreparedStatement ps1 = con.prepareStatement(sql1);) {

            ResultSet rs = ps1.executeQuery();

            if (rs.next()) {
                String s = rs.getString(1);
                String i[] = s.split("-");
                int no = Integer.valueOf(i[1]);
                ++no;
                id = String.format("T-%05d", no);

            } else {
                id = "T-00001";
            }

            ps.setString(1, id);
            ps.setString(2, t.getName());
            ps.setString(3, t.getPassword());
            ps.setDate(4, Date.valueOf(t.getDob()));
            ps.setString(5, t.getGender());
            ps.setString(6, t.getEmail());
            ps.setString(7, t.getNrc());
            ps.setString(8, t.getPh());
            ps.setString(9, t.getAddress());
            ps.setString(10, t.isStatus());
            ps.setInt(11, t.getFees());
            GUIScenes.TrainerSignUpController trainerSignUp = new GUIScenes.TrainerSignUpController();
            ps.execute();
//            try {
//                ps.execute();
//                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Trainer data are recorded", ButtonType.OK);
//                alert.showAndWait();
//            } catch (SQLException e) {
//                new Alert(AlertType.ERROR, "Duplicated NRC is not allowed", ButtonType.OK).showAndWait();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    public static void updatetrainer(trainer t) throws SQLException {

        String sql = "UPDATE `trainer` SET  `name`=?,`password`=?,`dob`=?,`gender`=?,"
                + "`email`=?,`nrc`=?,`phone_no`=?,`address`=?,`status`=?,`fees`=? WHERE id=?";

        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, t.getName());
            ps.setString(2, t.getPassword());
            ps.setDate(3, Date.valueOf(t.getDob()));
            ps.setString(4, t.getGender());
            ps.setString(5, t.getEmail());
            ps.setString(6, t.getNrc());
            ps.setString(7, t.getPh());
            ps.setString(8, t.getAddress());
            ps.setString(9, t.isStatus());
            ps.setInt(10, t.getFees());
            ps.setString(11, t.getId());
            ps.execute();
//            try {
            ps.executeUpdate();
//            } catch (SQLException e) {
//                new Alert(Alert.AlertType.ERROR, "Duplicated NRC is not allowed", ButtonType.OK).showAndWait();
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
        }

    }

    public static void updatetrainer_ByTrainer(String trainer_id, String trainerName, String pass, LocalDate dob, String gender, String email, String nrc, String phone, String address) throws SQLException {

        String sql = "UPDATE `trainer` SET  `name`=?,`password`=?,`dob`=?,`gender`=?,"
                + "`email`=?,`nrc`=?,`phone_no`=?,`address`=? WHERE id=?";

        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, trainerName);
            ps.setString(2, pass);
            ps.setDate(3, Date.valueOf(dob));
            ps.setString(4, gender);
            ps.setString(5, email);
            ps.setString(6, nrc);
            ps.setString(7, phone);
            ps.setString(8, address);
            ps.setString(9, trainer_id);
            ps.execute();
            ps.executeUpdate();

//        } catch (Exception e) {
//            e.printStackTrace();
        }

    }

    public static ArrayList<trainer> showall() throws SQLException {
        ArrayList<trainer> m = new ArrayList<>();
        String sql = "SELECT * from   trainer ";
        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                m.add(new trainer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString("status"), rs.getInt(11)));

            }

            return m;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static ArrayList<trainer> searchByname(String name) throws SQLException {
        ArrayList<trainer> m = new ArrayList<>();
        String sql = "Select * from  trainer where name like ? ";
        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                m.add(new trainer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString("status"), rs.getInt(11)));

            }

            return m;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static ArrayList<trainer> searchByid(String id) throws SQLException {
        ArrayList<trainer> m = new ArrayList<>();
        String sql = "Select * from  trainer where id like ? ";
        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, "%" + id + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                m.add(new trainer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString("status"), rs.getInt(11)));

            }

            return m;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static void batchupdatetrainer(ArrayList<trainer> tt) {

        String sql = "UPDATE `trainer` SET  `name`=?,`password`=?,`dob`=?,`gender`=?,"
                + "`email`=?,`nrc`=?,`phone_no`=?,`address`=?,`status`=?,`fees`=? WHERE id=?";

        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            tt.forEach((t) -> {
                try {
                    ps.setString(1, t.getName());
                    ps.setString(2, t.getPassword());
                    ps.setDate(3, Date.valueOf(t.getDob()));
                    ps.setString(4, t.getGender());
                    ps.setString(5, t.getEmail());
                    ps.setString(6, t.getNrc());
                    ps.setString(7, t.getPh());
                    ps.setString(8, t.getAddress());
                    ps.setString(9, t.isStatus());
                    ps.setInt(10, t.getFees());
                    ps.setString(11, t.getId());
                    ps.addBatch();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });

            ps.executeBatch();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String GetTrainerId_ByEmail(String email) {
        String Trainer_id = "T-00001";
        String sql = "SELECT * FROM `trainer` where email=?";

        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, email);
            ps.execute();
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Trainer_id = rs.getString(1);

            }
            return Trainer_id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        // create_table();
        // insert_trainer(new trainer("asas","asdsa", LocalDate.now(), true, "asd", "asda", "asd", "assa", true, 0));
        //updatetrainer(new trainer("asas","asdsa", LocalDate.now(),Boolean.FALSE, "asd", "asda", "asd", "assa", true, 01111));

    }

}
