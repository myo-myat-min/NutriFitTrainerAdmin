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
public class schedule_table {

    public static void create_table() {
        String sql = "Create table Schedule("
                + "id varchar(50) primary key  ,"
                + "schedule_date Date  not null ,"
                + "content varchar(100) not null ,"
                + "trainer_id varchar(50) not null,"
                + "FOREIGN KEY (`TRAINER_ID`) REFERENCES `trainer`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT );";
        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void insert_schedule(schedule s) {
        String id = "SCHE-000001";
        String sql0 = "Select id from schedule order by id desc limit 1";
        String sql = "INSERT INTO `schedule`( `id`,`schedule_date`, `content`, `trainer_id`) VALUES (?,?,?,?)";
        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                PreparedStatement ps0 = con.prepareStatement(sql0)) {

            ResultSet rs0 = ps0.executeQuery();
            if (rs0.next()) {
                String text = rs0.getString(1);
                String i[] = text.split("-");
                int no = Integer.valueOf(i[1]);
                ++no;
                id = String.format("SCHE-%06d", no);

            } else {
                id = "SCHE-000001";
            }

            ps.setString(1, id);
            ps.setDate(2, Date.valueOf(s.getSchedule_date()));
            ps.setString(3, s.getContent());
            ps.setString(4, s.getTrainer_id());
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<schedule> showall() {
        ArrayList<schedule> s = new ArrayList<>();
        String sql = "Select * from schedule ,  trainer  where schedule.trainer_id=trainer.id";
        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                LocalDate date = rs.getDate(2).toLocalDate();
                String content = rs.getString(3);
                String trainer_id = rs.getString(4);
                String trainer_name = rs.getString("trainer.name");

                s.add(new schedule(id, date, content, trainer_id, trainer_name));
            }
            return s;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static ArrayList<schedule> searchbyname_ForViewSchedule(String name) {
        ArrayList<schedule> i = new ArrayList<>();

        String sql = "select * from schedule, trainer where trainer.id=schedule.trainer_id and trainer.name  like ? ";

        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, "%" + name + "%");
            // ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                i.add(new schedule(rs.getString("schedule.id"), rs.getDate("schedule.schedule_date").toLocalDate(), rs.getString("schedule.content"), rs.getString("trainer.id"), rs.getString("trainer.name")));
                System.err.println(i);
            }
            return i;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void update_sche(String sche_id, LocalDate sche_date, String content) {

        String sql = "UPDATE `schedule` SET `schedule_date`=?,`content`=? WHERE id=?";

        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setDate(1, Date.valueOf(sche_date));
            ps.setString(2, content);
            ps.setString(3, sche_id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<schedule> searchbydate_ForViewSchedule(LocalDate schedule_date) {
        ArrayList<schedule> i = new ArrayList<>();

        String sql = "select * from schedule left join trainer on  trainer.id=schedule.trainer_id  where schedule.schedule_date like ?";

        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {

            //creating the instance of LocalDate using the day, month, year info 
            //LocalDate localDate = LocalDate.of(2016, 8, 19); 
            //local date + atStartOfDay() + default time zone + toInstant() = Date 
            //Date date = Date.from(schedule_date.atStartOfDay(defaultZoneId).toInstant()); 
            //Displaying LocalDate and Date
            ps.setString(1, "" + schedule_date);
            // ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                i.add(new schedule(rs.getString("schedule.id"), rs.getDate("schedule.schedule_date").toLocalDate(), rs.getString("schedule.content"), rs.getString("trainer.id"), rs.getString("trainer.name")));
                System.out.println(rs.getString("trainer.id"));
            }
            return i;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void delete_sche(String Sche_id) {

        String sql = "DELETE FROM `schedule` WHERE id=?";

        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, Sche_id);
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        //   create_table();
        //  System.out.println(showall());
    }

}
