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
import java.sql.SQLException;
import java.time.LocalDate;
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
public class Membertable {

    public static void CreateTable() {

        String sql = "CREATE TABLE Member("
                + "id  varchar(50) Primary key   , "
                + "name  Varchar(50) NOT NULL, "
                + "password Varchar(50) NOT  NULL ,  "
                + "dob Date NOT NULL,"
                + "gender Varchar(6) NOT NULL,"
                + "weight Int NOT NULL,"
                + "height Int  NOT NULL,"
                + "email Varchar(50) not null Unique,"
                + "nrc Varchar(50) NOT NULL Unique,"
                + "phone_no Varchar(50),"
                + "address Varchar(100) NOT NULL,"
                + "medical_data Varchar(100) );";
        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void deletetable() {
        String sql = "Drop table Member";

        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.execute();
            System.out.println("deleted member table");
        } catch (Exception e) {
            System.out.println("Fail to delete member table");
        }
    }

    public static String getMemberID_ByEmail(String email) {

        String Member_id = "A-001";

        String sql = "SELECT * FROM member where email=?";

        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, email);
            ps.execute();
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Member_id = rs.getString(1);

            }
            return Member_id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void InsertMember(Member m) {

        String sql = "INSERT INTO `member`( `id`,`name`, `password`, `dob`, `gender`, `weight`, `height`, `email`, `nrc`, `phone_no`, `address`, `medical_data`) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setString(1, m.getId());
            ps.setString(2, m.getName());
            ps.setString(3, m.getPassword());
            ps.setString(4, m.getDob().toString());
            ps.setString(5, m.getGender());
            ps.setInt(6, m.getWeight());
            ps.setInt(7, m.getHeight());
            ps.setString(8, m.getEmail());
            ps.setString(9, m.getNrc());
            ps.setString(10, m.getPh());
            ps.setString(11, m.getAddress());
            ps.setString(12, m.getMedical_data());

            ps.execute();
            System.out.println("add member");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    public static void batch_InsertMember(ArrayList<Member> mm) {
//
//        mm.forEach((t) -> {
//            System.out.println(mm);
//        });
//        String sql = "UPDATE `member` SET `name`=?,`password`=?,`dob`=?,`gender`=?,`weight`=?,`height`=?,`email`=?,`nrc`=?,"
//                + "`phone_no`=?,`address`=?,`medical_data`=? WHERE id=?";
//
//        try (Connection con = ConnectDB.CreateConnection();
//                PreparedStatement ps = con.prepareStatement(sql);) {
//
//            mm.forEach((m) -> {
//
//                try {
//                    ps.setString(1, m.getName());
//                    ps.setString(2, m.getPassword());
//                    ps.setString(3, m.getDob().toString());
//                    ps.setBoolean(4, m.getGender());
//                    ps.setFloat(5, m.getWeight());
//                    ps.setFloat(6, m.getHeight());
//                    ps.setString(7, m.getEmail());
//                    ps.setString(8, m.getNrc());
//                    ps.setString(9, m.getPh());
//                    ps.setString(10, m.getAddress());
//                    ps.setString(11, m.getMedical_data());
//                    ps.setInt(12, m.getId());
//                    ps.addBatch();
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
//
//            });
//
//            System.out.println(Arrays.asList(ps.executeBatch()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
    public static ArrayList<Member> showall() throws SQLException {
        ArrayList<Member> m = new ArrayList<>();
        String sql = "SELECT * from member ";
        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                m.add(new Member(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(), rs.getString(5),
                        rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12)));

            }

            return m;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static ArrayList<Member> searchbyname(String name) {
        ArrayList<Member> m = new ArrayList<>();

        String sql = "Select * from  member  where name like ?";

        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, "%" + name + "%");
            // ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                m.add(new Member(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(), rs.getString(5),
                        rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12)));

            }
            return m;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static ArrayList<Member> searchbyid(String id) {
        ArrayList<Member> m = new ArrayList<>();

        String sql = "Select * from  member  where id like ?";

        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, "%" + id + "%");
            // ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                m.add(new Member(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(), rs.getString(5),
                        rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12)));

            }
            return m;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void Update_Member(Member m) throws SQLException {

        String sql = "UPDATE `member` SET `name`=?,`password`=?,`dob`=?,`gender`=?,`weight`=?,`height`=?,`email`=?,"
                + "`nrc`=?,`phone_no`=?,`address`=?,`medical_data`=? WHERE id=?";

        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, m.getName());
            ps.setString(2, m.getPassword());
            ps.setDate(3, Date.valueOf(m.getDob()));
            ps.setString(4, m.getGender());
            ps.setInt(5, m.getWeight());
            ps.setInt(6, m.getHeight());
            ps.setString(7, m.getEmail());
            ps.setString(8, m.getNrc());
            ps.setString(9, m.getPh());
            ps.setString(10, m.getAddress());
            ps.setString(11, m.getMedical_data());
            ps.setString(12, m.getId());
//            try {
            ps.executeUpdate();
//            } catch (SQLException e) {
//                new Alert(Alert.AlertType.ERROR, "Duplicated NRC is not allowed", ButtonType.OK).showAndWait();
//            }
//            System.out.println("updated");
//
//        } catch (Exception e) {
//            e.printStackTrace();
        }

    }

    public static void add_member_invoice(Member m, String admin_id, LocalDate enddate, String membership_id) throws SQLException {
        String Mid = "M-000001";
        String Iid = "INV-000001";
        String sql0 = "Select id from invoice order by id desc limit 1";
        String sql2 = "Select id from member order by id desc limit 1";
        LocalDate end;

        String member = "INSERT INTO `member`( `id`,`name`, `password`, `dob`, `gender`, `weight`, `height`, `email`, `nrc`, `phone_no`, `address`, `medical_data`) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        String invoice = "INSERT INTO `invoice`( `id`,`invoice_date`,  `end_date`, `member_id`, `membership_id`, `admin_id`) "
                + "VALUES (?,?,?,?,?,?)";
        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps0 = con.prepareStatement(sql0);
                PreparedStatement ps = con.prepareStatement(member);
                PreparedStatement ps1 = con.prepareStatement(invoice);
                PreparedStatement ps2 = con.prepareStatement(sql2)) {

            con.setAutoCommit(false);

            ResultSet rs0 = ps2.executeQuery();
            if (rs0.next()) {
                String s = rs0.getString(1);
                String i[] = s.split("-");
                int no = Integer.valueOf(i[1]);
                ++no;
                Mid = String.format("M-%06d", no);

            } else {
                Mid = "M-000001";
            }

            ResultSet rs2 = ps0.executeQuery();

            if (rs2.next()) {
                String s = rs2.getString(1);
                String i[] = s.split("-");
                int no1 = Integer.valueOf(i[1]);
                ++no1;
                Iid = String.format("INV-%06d", no1);

            } else {
                Iid = "INV-000001";
            }

            ps.setString(1, Mid);
            ps.setString(2, m.getName());
            ps.setString(3, m.getPassword());
            ps.setString(4, m.getDob().toString());
            ps.setString(5, m.getGender());
            ps.setInt(6, m.getWeight());
            ps.setInt(7, m.getHeight());
            ps.setString(8, m.getEmail());
            ps.setString(9, m.getNrc());
            ps.setString(10, m.getPh());
            ps.setString(11, m.getAddress());
            ps.setString(12, m.getMedical_data());
            ps.execute();
            System.out.println(ps.getResultSet());

            ps1.setString(1, Iid);
            ps1.setDate(2, Date.valueOf(LocalDate.now()));
            ps1.setDate(3, Date.valueOf(enddate));
            ps1.setString(4, Mid);
            ps1.setString(5, membership_id);
            ps1.setString(6, admin_id);
            ps1.execute();

            System.out.println("add member");

            con.commit();

        }

    }

    public static int new_member(int month, int year, int end_day) {

        int i = 0;

        LocalDate Start = LocalDate.of(year, month, 1);

        LocalDate end = LocalDate.of(year, month, end_day);

        String sql = " SELECT COUNT(DISTINCT member_id) FROM invoice WHERE NOT member_id IN(SELECT ID FROM invoice WHERE invoice_date<?) AND invoice_date BETWEEN ? and  ?";// new member;
        //  String sql = "select count(id) as count from invoice where invoice_date between '2020-1-01' AND '2020-12-28'";

        //select COUNT(id) FROM invoice WHERE id in ( SELECT id from invoice where invoice_date between ? and ? group by (member_id) having count(member_id)<2)
        //SELECT COUNT(DISTINCT member_id) FROM invoice WHERE NOT EXISTS(SELECT ID FROM invoice WHERE invoice_date<'2021') AND invoice_date BETWEEN '2021-1-1' and '2021-2-1'
        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setDate(1, Date.valueOf(Start));
            ps.setDate(2, Date.valueOf(Start));
            ps.setDate(3, Date.valueOf(end));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                i = rs.getInt(1);
                System.out.println(i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return i;
    }

    public static ArrayList<invoice> Member_ForCreatingInvoices() {

        ArrayList<invoice> i = new ArrayList<>();

        String sql = "select member.id,member.name,membership.membership_name,member.nrc,invoice.end_date "
                + "FROM invoice , member,membership WHERE invoice.member_id=member.id AND invoice.membership_id=membership.membership_id AND invoice.id in ( SELECT MAX(id) from invoice group by (member_id) ) ORDER BY member_id ASC";

        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                i.add(new invoice(rs.getDate("end_date").toLocalDate(), rs.getString("id"), rs.getString("membership_name"), rs.getString("name"), rs.getString("nrc")));
                System.out.println(rs.getDate("end_date"));
                System.out.println(rs.getString("id"));
            }
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<invoice> searchbyname_ForCreateInvoice(String name) {
        ArrayList<invoice> i = new ArrayList<>();

        String sql = "Select * from a  where name like ?";

        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, "%" + name + "%");
            // ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                i.add(new invoice(rs.getDate("end_date").toLocalDate(), rs.getString("id"), rs.getString("membership_name"), rs.getString("name"), rs.getString("nrc")));

            }
            return i;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static ArrayList<invoice> searchbyid_ForCreateInvoice(String id) {
        ArrayList<invoice> i = new ArrayList<>();

        String sql = "Select * from a  where id like ?";

        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, "%" + id + "%");
            // ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                i.add(new invoice(rs.getDate("end_date").toLocalDate(), rs.getString("id"), rs.getString("membership_name"), rs.getString("name"), rs.getString("nrc")));

            }
            return i;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<invoice> showall_ForCreateInvoice() {
        ArrayList<invoice> i = new ArrayList<>();

        String sql = "Select * from a  ";

        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                i.add(new invoice(rs.getDate("end_date").toLocalDate(), rs.getString("id"), rs.getString("membership_name"), rs.getString("name"), rs.getString("nrc")));

            }
            return i;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws SQLException {
        //  CreateTable();
        //   deletetable();
        // InsertMember(new Member("saasds", "adasas", LocalDate.now().toString(), true, 0, 0, "asda", "asjubud", "asdas","s" ,"asdsa"));
        //System.out.println(showall());
        //System.out.println(searchbyname("saasds"));
    }

}
