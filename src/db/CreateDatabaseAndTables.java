package db;

import java.sql.SQLException;

public class CreateDatabaseAndTables {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		CreateDB.create_db();
		Membertable.CreateTable();
		trainer_table.create_table();
		membership_table.create_membership_table();
		membership_table.insert_membership(new Membership("MS-01", "1-month", 20000));
		membership_table.insert_membership(new Membership("MS-02", "2-month", 30000));
		membership_table.insert_membership(new Membership("MS-03", "3-month", 40000));
		membership_table.insert_membership(new Membership("MS-04", "4-month", 50000));
		
		admin_table.create_table();
		admin_table.insert_admin(new admin("Min Min", "123minmin", "minmin123@gmail.com", "09123456789", "On Job"));
		invoice_table.create_table();

		attendance_table.create_table();
		DailyAttendancetable.create_table();

		schedule_table.create_table();
		workout_table.create_table();
		monthly_progress_table.create_table();
		viewtable.createview();

	}
	
	public void createDBAndTables() {
		CreateDB.create_db();
		Membertable.CreateTable();
		trainer_table.create_table();
		membership_table.create_membership_table();
		admin_table.create_table();
		invoice_table.create_table();

		attendance_table.create_table();
		DailyAttendancetable.create_table();

		schedule_table.create_table();
		workout_table.create_table();
		monthly_progress_table.create_table();
		viewtable.createview();
	}
}
