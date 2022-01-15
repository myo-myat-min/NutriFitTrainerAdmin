/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import GUIScenes.RecordAttendanceTrainer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Soe min hein
 */
public class DailyAttendancetable {
    
    public static void create_table() {

        String sql = "Create table dailyattendance("
                + "id  int primary key auto_increment,"
                + "attend varchar(10) not null,"
                + "arrive_time time, "
                + "note varchar(50),"
                + "trainer_id varchar(50) not null,"
                + "attendance_id int not null,"
                + "FOREIGN KEY (`TRAINER_ID`) REFERENCES `trainer`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT  ,"
                + "FOREIGN KEY (`ATTENDANCE_ID`) REFERENCES `attendance`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT ,"
                + "UNIQUE( `trainer_id`, `attendance_id`)   );";

        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void insert_DA(ArrayList<GUIScenes.RecordAttendanceTrainer> ta) throws SQLException {
        String attendance = "INSERT INTO `attendance`( `attendance`) VALUES (?)";
        String DA = "INSERT INTO `dailyattendance`(`trainer_id`, `attendance_id`,`attend`,`arrive_time`,`note`) VALUES (?,?,?,?,?)";

        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(attendance, PreparedStatement.RETURN_GENERATED_KEYS);
                PreparedStatement ps1 = con.prepareStatement(DA)) {
            con.setAutoCommit(false);

            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.execute();
            new Alert(Alert.AlertType.INFORMATION, "Data are recorded", ButtonType.OK).showAndWait();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                ta.forEach((t) -> {
//                    System.err.println(t.getId());
//                    System.err.println(t);
                    try {
                        System.out.println(t.getTrainer_id());
                        ps1.setString(1, t.getId());
                        ps1.setInt(2, rs.getInt(1));
                        ps1.setString(3,t.getAttend());
                        ps1.setTime(4, Time.valueOf(t.getArrived_Time()));
                        ps1.setString(5, t.getNote());
                       
                        ps1.addBatch();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                });

            }
            ps1.executeBatch();
//            System.err.println(Arrays.toString(ps1.executeBatch()));
            con.commit();
        } 

    }

//    public static ArrayList<DailyAttendance> showda() {
//
//        String sql = "SELECT id FROM `attendance` WHERE `attendance` LIKE ?";
//        String sql2 = "SELECT * FROM `dailyattendance` WHERE `attendance_id` = ?";
//        String sql3 = "SELECT * FROM `trainer` WHERE `id` = ?";
//        try (Connection con = db.ConnectDB.CreateConnection();
//                PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
//                PreparedStatement ps1 = con.prepareStatement(sql2);
//                PreparedStatement ps2=con.prepareStatement(sql3)) {
//
//            ps.setDate(1, Date.valueOf(LocalDate.now()));
//            ps.execute();
//            ResultSet rs = ps.getGeneratedKeys();
//            if (rs.next()) {
//                ps1.setInt(1, rs.getInt(1));
//            }
//
//            ResultSet s1=ps1.executeQuery();
//            while (s1.next()) {                
//                
//            }
//        } catch (Exception e) {
//        }
//return  null;
//    }
    
    public static ArrayList<GUIScenes.RecordAttendanceTrainer> showda(LocalDate attend_date) {
//SELECT a.trainer_id,trainer.name,a.attend,a.arrive_time,a.note FROM dailyattendance a,trainer,attendance WHERE a.trainer_id=trainer.id  and attendance.attendance=2020-1-1

        ArrayList<GUIScenes.RecordAttendanceTrainer> ta = new ArrayList<>();
        String sql = "SELECT a.trainer_id as id,trainer.name,a.attend,a.arrive_time,a.note FROM dailyattendance a,trainer,attendance WHERE a.trainer_id=trainer.id and a.attendance_id=attendance.id AND attendance.attendance=?";

        try (Connection con = db.ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setDate(1, Date.valueOf(attend_date));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                LocalTime AT;
                if (rs.getTime("arrive_time") != null) {
                    AT = rs.getTime("arrive_time").toLocalTime();
                } else {
//                    AT = LocalTime.of(9, 0);
                    AT = LocalTime.of(17, 0);
                }

                ta.add(new RecordAttendanceTrainer(rs.getString("attend"), AT, rs.getString("note"), rs.getString("id"), rs.getString("name")));
//                System.out.println(ta);
            }
            return ta;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void Update_Da(ArrayList<GUIScenes.RecordAttendanceTrainer> ta, LocalDate attended_date) {

        String sql = "Select id from attendance where attendance=?";
        String sql1 = "UPDATE `dailyattendance`   SET `attend`=?,`arrive_time`=?,`note`=? WHERE  attendance_id=? and trainer_id=?";

        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                PreparedStatement ps1 = con.prepareStatement(sql1);) {

            ps.setDate(1, Date.valueOf(attended_date));
            ResultSet rs=ps.executeQuery();
            if (rs.next()) {
                   
                 ta.forEach((t) -> {
                     System.out.println(t);
                    try {
                        ps1.setString(1, t.getAttend());
                    
                        if (t.getArrived_Time() != null) {
                            ps1.setTime(2, Time.valueOf(t.getArrived_Time()));
                        } else {
                            ps1.setTime(2, null);
                        }
                        ps1.setString(3, t.getNote());
                        ps1.setInt(4, rs.getInt("id"));
                        ps1.setString(5, t.getId());

                        ps1.addBatch();
                        System.out.println(rs.getInt("id"));
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                });

            }

            ps1.executeBatch();
            System.out.println("noice");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<MonthlyReportTrainer> showAllTrainerMonthlyAtt(LocalDate localDate, int finePerMinute, int finePerDay) {
        if (localDate != null) {
            ArrayList<MonthlyReportTrainer> ms = new ArrayList<>();
            String sql = String.format("SELECT t.name, TIME_TO_SEC(SEC_TO_TIME(SUM(CASE WHEN d.arrive_time > '09:00:00' AND "
                    + "d.arrive_time <> '17:00:00' THEN TIME_TO_SEC(d.arrive_time) ELSE 0 END)) - SEC_TO_TIME(SUM(CASE "
                    + "WHEN d.arrive_time > '09:00:00' AND d.arrive_time <> '17:00:00' THEN TIME_TO_SEC('09:00:00') ELSE "
                    + "0 END))) / 60 AS late_time, COUNT(CASE d.attend WHEN 'X' THEN 1 ELSE null END) AS leave_day, t.fees FROM "
                    + "attendance a LEFT JOIN dailyattendance d ON a.id = d.attendance_id JOIN trainer"
                    + " t ON t.id = d.trainer_id WHERE MONTH(a.attendance)='%s' && YEAR(a.attendance)='%s' GROUP BY d.trainer_id;",
                    localDate.getMonthValue(), localDate.getYear());

            try (Connection con = ConnectDB.CreateConnection();
                    PreparedStatement ps = con.prepareStatement(sql);) {

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {                    
                    int fees = rs.getInt("t.fees") - ((Integer.parseInt(rs.getString("leave_day"))) * finePerDay) - (Integer.parseInt(String.format("%.0f",(Float.parseFloat(rs.getString("late_time"))))) * finePerMinute);
                    ms.add(new MonthlyReportTrainer(rs.getString("t.name"), rs.getString("late_time"), rs.getString("leave_day"), fees));
                }
                return ms;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
    
    public static ArrayList<GUIScenes.RecordAttendanceTrainer> searchbyid(String id, LocalDate attend_date) {
        ArrayList<GUIScenes.RecordAttendanceTrainer> ta = new ArrayList<>();

        String sql = "SELECT a.trainer_id as id,trainer.name,a.attend,a.arrive_time,a.note FROM dailyattendance a,trainer,attendance WHERE a.trainer_id=trainer.id and a.attendance_id=attendance.id AND  a.trainer_id like ? and  attendance.attendance=?";

        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, "%" + id + "%");
            ps.setDate(2, Date.valueOf(attend_date));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                LocalTime AT;
                if (rs.getTime("arrive_time") != null) {
                    AT = rs.getTime("arrive_time").toLocalTime();
                } else {
                    AT = null;
                }

                ta.add(new RecordAttendanceTrainer(rs.getString("attend"), AT, rs.getString("note"), rs.getString("id"), rs.getString("name")));

                System.out.println(ta);
            }
            return ta;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<GUIScenes.RecordAttendanceTrainer> searchbyname(String name, LocalDate attend_date) {
        ArrayList<GUIScenes.RecordAttendanceTrainer> ta = new ArrayList<>();
        
        
        String sql = "SELECT a.trainer_id as id,trainer.name,a.attend,a.arrive_time,a.note FROM dailyattendance a,trainer,attendance WHERE a.trainer_id=trainer.id and a.attendance_id=attendance.id AND  trainer.name like ? and  attendance.attendance =?";


        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, "%" + name + "%");
            ps.setDate(2, Date.valueOf(attend_date));
            // ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                LocalTime AT;
                if (rs.getTime("arrive_time") != null) {
                    AT = rs.getTime("arrive_time").toLocalTime();
                } else {
                    AT = null;
                }
                ta.add(new RecordAttendanceTrainer(rs.getString("attend"), AT, rs.getString("note"), rs.getString("id"), rs.getString("name")));

                System.out.println(ta);
            }
            return ta;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
        //create_table();
        //    insert_DA();

    }

}
