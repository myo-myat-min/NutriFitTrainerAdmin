package db;

import java.sql.SQLException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

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
		String encryptedPass = "";

		try {
			Signature sign = Signature.getInstance("SHA256withRSA");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		KeyPairGenerator keyPairGen = null;
		try {
			keyPairGen = KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		keyPairGen.initialize(2048);
		KeyPair pair = keyPairGen.generateKeyPair();
		PublicKey publicKey = pair.getPublic();
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchPaddingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		} catch (InvalidKeyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		byte[] input = password.getBytes();
		cipher.update(input);
		byte[] cipherText = null;
		try {
			cipherText = cipher.doFinal();
		} catch (IllegalBlockSizeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (BadPaddingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			encryptedPass = new String(cipherText, "UTF8");
			System.out.println(encryptedPass);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			admin_table.insert_admin(new admin("Min Min", encryptedPass, "minmin123@gmail.com", "09123456789", "On Job"));
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
