/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIScenes;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.Proxy;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import db.ConnectDB;
import db.CreateDB;
import db.CreateDatabaseAndTables;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
//import static newpackage.a.FILE_TO_SEND;

/**
 *
 * @author ACER
 */
public class Main extends Application {

	@Override
	public void start(Stage stage) throws Exception {

		try {
			ServerSocket sc;
			try {
				sc = new ServerSocket(9090);
				System.out.println(sc.toString());
				System.err.println("server opened");

				new Thread(() -> {

					while (true) {
						try {
							Socket sock = sc.accept();
							InputStream is = sock.getInputStream();

							BufferedReader bs = new BufferedReader(new InputStreamReader(is));
							String s = new String("ssasas");

//                            System.out.println(bs.readLine());
							System.out.println("Accepted connection : " + sock);
							// send file
							File myFile = new File(bs.readLine());
							byte[] mybytearray = new byte[(int) myFile.length()];
							FileInputStream fis = new FileInputStream(myFile);
							BufferedInputStream bis = new BufferedInputStream(fis);
							bis.read(mybytearray, 0, mybytearray.length);
							OutputStream os = sock.getOutputStream();
//                            System.out.println("Sending " + FILE_TO_SEND + "(" + mybytearray.length + "bytes)");
							os.write(mybytearray, 0, mybytearray.length);
							os.flush();
							System.out.println("Done.");
							sock.close();
							bis.close();
						} catch (IOException ex) {

						}

					}

				}).start();
			} catch (IOException ex) {

			}

		} catch (Exception e) {

		}
//		CREATE DATABASE IF NOT EXISTS DBName;
//		String checkDB = "CREATE DATABASE IF NOT EXISTS Nutri_Fit_Trainer_Admin;";
//		
//		try (Connection con = CreateDB.createConnection();
//                PreparedStatement ps = con.prepareStatement(checkDB);
//                		) {
//			System.out.println(ps.execute());
//			if(!ps.execute()) {
//				CreateDatabaseAndTables create = new CreateDatabaseAndTables();
//				create.createDBAndTables();
//			} 

//        } catch (Exception e) {
//            e.printStackTrace();
//        }

		ArrayList<String> databaseNames = new ArrayList<>();
		CreateDatabaseAndTables createDBAndTables = new CreateDatabaseAndTables();
		Connection con = CreateDB.createConnection();
		try {
			ResultSet rs = con.getMetaData().getCatalogs();
			while (rs.next()) {

				String catalogs = rs.getString(1);
				databaseNames.add(catalogs);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		if (!databaseNames.contains("nutri_fit")) {
			createDBAndTables.createDBAndTables();
		}

		Parent memberSignUpScene = FXMLLoader.load(getClass().getResource("TrainerAdminSignInScene.fxml"));
		Scene scene = new Scene(memberSignUpScene);
		stage.setOnCloseRequest((event) -> {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to exit?", ButtonType.YES,
					ButtonType.NO);
			alert.showAndWait();

			if (alert.getResult() == ButtonType.YES) {
				System.exit(0);
			} else {
				event.consume();
			}
		});
		stage.setTitle("NutriFit");

		stage.setResizable(false);
		stage.centerOnScreen();
		stage.setScene(scene);
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}
}
