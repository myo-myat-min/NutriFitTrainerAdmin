package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class CreateDB {

    private static final String url = "jdbc:mysql://localhost/";
    private static final String username = "root";
    private static final String password = "";

    public static Connection createConnection() throws SQLException, ClassNotFoundException {
        return DriverManager.getConnection(url, username, password);
    }

    public static void create_db() {
        String sql = "CREATE DATABASE Nutri_Fit";

        try (Connection con = createConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
      ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
       // create_db();
       
    }

}
