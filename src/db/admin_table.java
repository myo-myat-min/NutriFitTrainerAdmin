package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class admin_table {

	public static void create_table() {

		String sql = "create table admin(" + "id  varchar(50) primary key ," + "name varchar(50) not null,"
				+ "password TEXT not null,"
				+ "email varchar(50) not null Unique, phone varchar(15) not null, status varchar(10) not null);";

		try (Connection con = ConnectDB.CreateConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.execute();
			System.out.println("table created");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void delete_table() {

		String sql = "drop table admin";
		try (Connection con = ConnectDB.CreateConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.execute();
			System.out.println("admin table deleted");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void insert_admin(admin ms) throws SQLException {

		String id = "A-1";

		String sql = "select id from admin order by id desc limit 1";
		String sql1 = "INSERT INTO `admin`(`id`, `name`, `password`, `email`, `phone`, `status`) VALUES (?,?,?,?,?,?)";

		try (Connection con = ConnectDB.CreateConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				PreparedStatement ps1 = con.prepareStatement(sql1);) {

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String last_adminID = rs.getString(1);
				String s[] = last_adminID.split("-");
				int no = Integer.valueOf(s[1]);
				no++;
				id = String.format("A-%01d", no);
			} else {
				id = "A-1";
			}

			ps1.setString(1, id);
			ps1.setString(2, ms.getName());
			ps1.setString(3, ms.getPassword());
			ps1.setString(4, ms.getEmail());
			ps1.setString(5, ms.getPhone());
			ps1.setString(6, ms.getStatus());
			ps1.execute();
			System.out.println("admin added");
//        } catch (Exception e) {
//            e.printStackTrace();
		}

	}

//    public static void insert_admin(admin ms) {
//
//        String id = "A-1";
//
//        String sql = "INSERT INTO `admin`(`name`, `password`, `email`, `phone`, `status`) VALUES (?,?,?,?,?)";
//        try (Connection con = ConnectDB.CreateConnection();
//                PreparedStatement ps = con.prepareStatement(sql);) {
//            ps.setString(1, ms.getId());
//            ps.setString(2, ms.getName());
//            ps.setString(3, ms.getPassword());
//            ps.setString(4, ms.getEmail());
//            ps.setString(5, ms.getPhone());
//            ps.setString(6, ms.getStatus());
//            ps.execute();
//            System.out.println("admin added");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
	public static ArrayList<admin> showall() throws SQLException {
		ArrayList<admin> a = new ArrayList<>();
		String sql = "SELECT * FROM `admin`";

		try (Connection con = ConnectDB.CreateConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				a.add(new admin(rs.getString("id"), rs.getString("name"), rs.getString("password"),
						rs.getString("email"), rs.getString("phone"), rs.getString("status")));
			}
			return a;
		} catch (Exception e) {
		}
		return null;
	}

	public static String getAdminID_ByEmail(String email) {

		String Admin_id = "A-001";

		String sql = "SELECT * FROM `admin` where email=?";

		try (Connection con = ConnectDB.CreateConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, email);
			ps.execute();
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Admin_id = rs.getString(1);

			}
			return Admin_id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<admin> searchbyname(String name) {
		ArrayList<admin> m = new ArrayList<>();

		String sql = "Select * from  admin  where name like ?";

		try (Connection con = ConnectDB.CreateConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, "%" + name + "%");
			// ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				m.add(new admin(rs.getString("id"), rs.getString("name"), rs.getString("password"),
						rs.getString("email"), rs.getString("phone"), rs.getString("status")));
			}
			return m;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<admin> searchbyid(String id) {
		ArrayList<admin> m = new ArrayList<>();

		String sql = "Select * from  admin  where id like ?";

		try (Connection con = ConnectDB.CreateConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, "%" + id + "%");
			// ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				m.add(new admin(rs.getString("id"), rs.getString("name"), rs.getString("password"),
						rs.getString("email"), rs.getString("phone"), rs.getString("status")));
			}
			return m;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void DeleteAdminById(String id) {

		String sql = "delete  from admin where  id=?";
		try (Connection con = ConnectDB.CreateConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setString(1, id);
			ps.execute();
			System.out.println("Deleted admin " + id);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void updateAdmin(admin a) throws SQLException {

		String sql = "UPDATE `admin` SET  `name`=?,`password`=?,`email`=?,`phone`=?,`status`=? WHERE id=?";

		try (Connection con = ConnectDB.CreateConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, a.getName());
			ps.setString(2, a.getPassword());
			ps.setString(3, a.getEmail());
			ps.setString(4, a.getPhone());
			ps.setString(5, a.getStatus());
			ps.setString(6, a.getId());
			ps.execute();
			ps.executeUpdate();

//        } catch (Exception e) {
//            e.printStackTrace();
		}

	}

//    public static void updateOtherAdmin(admin a) {
//
//        String sql = "UPDATE `admin` SET  `name`=?,`email`=?,`phone`=?,`status`=? WHERE id=?";
//
//        try (Connection con = ConnectDB.CreateConnection();
//                PreparedStatement ps = con.prepareStatement(sql)) {
//
//            ps.setString(1, a.getName());
//            ps.setString(2, a.getPassword());
//            ps.setString(3, a.getEmail());
//            ps.setString(4, a.getPhone());
//            ps.setString(5, a.getStatus());
//            ps.setString(6, a.getId());
//            ps.execute();
//            ps.executeUpdate();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
	public static void main(String[] args) {
		// create_table();
		// delete_table();
//		 insert_admin(new admin("aaaa", "aaaa"));
		// System.out.println(showall());
		// DeleteAdminById(1);
	}

}
