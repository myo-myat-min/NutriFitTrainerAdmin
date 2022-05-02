package db;

import java.sql.SQLException;
import java.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class CreateDatabaseAndTables {

	public void createDBAndTables() {
		CreateDB.create_db();
		Membertable.CreateTable();
		trainer_table.create_table();
		membership_table.create_membership_table();
		membership_table.insert_membership(new Membership("MS-01", "1-month", 20000));
		membership_table.insert_membership(new Membership("MS-02", "2-month", 30000));
		membership_table.insert_membership(new Membership("MS-03", "3-month", 40000));
		membership_table.insert_membership(new Membership("MS-04", "4-month", 50000));

		admin_table.create_table();

		String password = "123minmin";
		String encodedPass = Base64.getEncoder().encodeToString(password.getBytes());

		try {
			admin_table
					.insert_admin(new admin("Min Min", encodedPass, "minmin123@gmail.com", "09123456789", "On Job"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		invoice_table.create_table();

		attendance_table.create_table();
		DailyAttendancetable.create_table();

		schedule_table.create_table();
		workout_table.create_table();
		monthly_progress_table.create_table();
		viewtable.createview();
	}
}
