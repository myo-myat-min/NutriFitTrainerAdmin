package GUIScenes;

import db.Member;
import db.Membertable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import db.ConnectDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MemberSignUpController implements Initializable {

	private FadeTransition fadeAnimationMember = new FadeTransition();
	private FadeTransition fadeAnimationEmail = new FadeTransition();
	private FadeTransition fadeAnimationNrc = new FadeTransition();
	private FadeTransition fadeAnimationPhone = new FadeTransition();
	private FadeTransition fadeAnimationAddress = new FadeTransition();
	private FadeTransition fadeAnimationDOB = new FadeTransition();
	private FadeTransition fadeAnimationHeight = new FadeTransition();
	private FadeTransition fadeAnimationWeight = new FadeTransition();
	private FadeTransition fadeAnimationEmptyNoti = new FadeTransition();
	private FadeTransition fadeAnimationInvalidName = new FadeTransition();
	private FadeTransition fadeAnimationInvalidEmail = new FadeTransition();
	private FadeTransition fadeAnimationInvalidNRC = new FadeTransition();
	private FadeTransition fadeAnimationInvalidPhone = new FadeTransition();
	private FadeTransition fadeAnimationInvalidHeight = new FadeTransition();
	private FadeTransition fadeAnimationInvalidWeight = new FadeTransition();
	private FadeTransition fadeAnimationInvalidDOB = new FadeTransition();

	protected JFXButton adminAccButton = new JFXButton("Create Admin Account");
	protected JFXButton memberAccButton = new JFXButton("Create Member Account");
	protected JFXButton trainerAccButton = new JFXButton("Create Trainer Accont");
	protected JFXButton seeUpdateAdminDataButton = new JFXButton("Admin Data");
	protected JFXButton seeUpdateMemberDataButton = new JFXButton("Member Data");
	protected JFXButton seeUpdateTrainerDataButton = new JFXButton("Trainer Data");
	protected JFXButton monthlyReportButton = new JFXButton("Create Monthly Report");
	protected JFXButton trainerAttButton = new JFXButton("Record Attendance");
	protected JFXButton seeTrainerAttButton = new JFXButton("Trainer Attendance");
	protected JFXButton membershipPlanButton = new JFXButton("Membership Plan");
	protected JFXButton createInvoiceButton = new JFXButton("Create Invoice");
	protected JFXButton invoiceHistoryButton = new JFXButton("Invoice History");
	protected JFXButton logOutButton = new JFXButton("Log Out");

	@FXML
	protected Circle nameCircle;

	@FXML
	protected Circle emailCircle;

	@FXML
	protected Circle nrcCircle;

	@FXML
	protected Circle dobCircle;

	@FXML
	protected Circle heightCircle;

	@FXML
	protected Circle weightCircle;

	@FXML
	protected Rectangle welcomeTemplate;

	@FXML
	protected Text txtWelcome;

	@FXML
	protected Text txtNutriFit;

	@FXML
	protected Rectangle titleTemplate;

	@FXML
	protected JFXTextField memberName;

	@FXML
	protected JFXTextField nrc;

	@FXML
	protected JFXTextField phone;

	@FXML
	protected JFXRadioButton male;

	@FXML
	protected ToggleGroup gender;

	@FXML
	protected JFXRadioButton female;

	@FXML
	protected JFXDatePicker dob;

	@FXML
	protected JFXSlider sliHeight;

	@FXML
	protected JFXTextField height;

	@FXML
	protected JFXSlider sliWeight;

	@FXML
	protected JFXTextField weight;

	@FXML
	protected JFXTextArea medicalData;

	@FXML
	protected Text title;

	@FXML
	protected JFXRadioButton firstPackage;

	@FXML
	protected JFXButton signUp;

	@FXML
	protected JFXHamburger adminMenu;

	@FXML
	protected Circle phoneCircle;

	@FXML
	protected JFXTextField email;

	@FXML
	protected Circle addressCircle;

	@FXML
	protected JFXTextArea address;

	@FXML
	protected Text emptyNoti;

	@FXML
	protected Text invalidName;

	@FXML
	protected Text invalidEmail;

	@FXML
	protected Text invalidPhone;

	@FXML
	protected Text invalidNrc;

	@FXML
	protected Text invalidWeight;

	@FXML
	protected Text invalidHeight;

	@FXML
	private Circle completeCircName;

	@FXML
	private Circle completeCircEmail;

	@FXML
	private Circle completeCircPhone;

	@FXML
	private Circle completeCircNRC;

	@FXML
	private Circle completeCircHeight;

	@FXML
	private Circle completeCircWeight;

	@FXML
	protected JFXDrawer adminDrawer;

	@FXML
	private Circle completeCircDOB;

	@FXML
	private Text invalidDOB;

	@FXML
	private JFXComboBox<String> cboPackage;

	private ArrayList<db.Membership> m = db.membership_table.showall();

	@FXML
	void signUpPressedAction(MouseEvent event) {
		if (!memberName.getText().isEmpty() && !email.getText().isEmpty() && !nrc.getText().isEmpty()
				&& !phone.getText().isEmpty() && !address.getText().isEmpty() && dob.getValue() != null
				&& !height.getText().isEmpty() && !weight.getText().isEmpty()) {
			if (completeCircName.getFill() == Color.RED || completeCircEmail.getFill() == Color.RED
					|| completeCircPhone.getFill() == Color.RED || completeCircNRC.getFill() == Color.RED
					|| completeCircHeight.getFill() == Color.RED || completeCircWeight.getFill() == Color.RED
					|| completeCircDOB.getFill() == Color.RED) {
				invalidAnimation();
				invalidControllThreads();
			}
		} else {
			redCircleAnimation();
			emptyNotiMethod();
			emptyControllThreads();
		}
	}

	public String randomAlphaNumeric(int count) {
		String alphaNumString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * alphaNumString.length());
			builder.append(alphaNumString.charAt(character));
		}
		System.out.println(builder.toString());
		return builder.toString();
	}

	@FXML
	void signUpReleasedAction(MouseEvent event) {
		if (!memberName.getText().isEmpty() && !email.getText().isEmpty() && !nrc.getText().isEmpty()
				&& !phone.getText().isEmpty() && !address.getText().isEmpty() && dob.getValue() != null
				&& !height.getText().isEmpty() && !weight.getText().isEmpty()) {
			if (completeCircName.getFill() == Color.GREEN && completeCircEmail.getFill() == Color.GREEN
					&& completeCircPhone.getFill() == Color.GREEN && completeCircNRC.getFill() == Color.GREEN
					&& completeCircHeight.getFill() == Color.GREEN && completeCircWeight.getFill() == Color.GREEN
					&& completeCircDOB.getFill() == Color.GREEN) {

				String maleOrFemale;
				if (male.isSelected()) {
					maleOrFemale = "Male";
				} else {
					maleOrFemale = "Female";
				}
				TrainerSignInController trainerSignInControl = new TrainerSignInController();
				String password = randomAlphaNumeric(11);
				String encodedPass = Base64.getEncoder().encodeToString(password.getBytes());

				Member m = new Member(memberName.getText(), encodedPass, dob.getValue(), maleOrFemale,
						(int) sliWeight.getValue(), (int) sliHeight.getValue(), email.getText(), nrc.getText(),
						phone.getText(), address.getText(), medicalData.getText());
				String ms = cboPackage.getSelectionModel().getSelectedItem();

				this.m.forEach((t) -> {
					if (t.getName().equals(ms)) {
						String[] s = t.getName().split("-");
						System.out.println(s[0]);
						System.out.println(t.getId());
						System.out.println(m);
						LocalDate enddate = LocalDate.now().plusMonths(Integer.parseInt(s[0]));
						if (ConnectDB.CreateConnection() != null) {
							String sql = "SELECT * FROM admin";

							try (Connection con = ConnectDB.CreateConnection();
									PreparedStatement ps = con.prepareStatement(sql);) {

								ResultSet rs = ps.executeQuery();
								Stage a = (Stage) ((Node) event.getSource()).getScene().getWindow();
								String admin_id = db.admin_table.getAdminID_ByEmail(a.getTitle());

								while (rs.next()) {

									if (admin_id.equals(rs.getString("id"))) {

										String decodedPass = new String(
												Base64.getDecoder().decode(rs.getString("password")));

										Membertable.add_member_invoice(m, admin_id, enddate, t.getId());
										trainerSignInControl.send("mstzsn01@gmail.com", "qazmlp123!", email.getText(),
												"NutriFit - Password", decodedPass);
										Alert alertRecorded = new Alert(Alert.AlertType.INFORMATION,
												"Member data are recorded", ButtonType.OK);
										alertRecorded.showAndWait();

										if (alertRecorded.getResult() == ButtonType.OK) {
											try {
												Parent trainerSignUpScene = FXMLLoader
														.load(getClass().getResource("InvoiceScene.fxml"));
												Scene scene = new Scene(trainerSignUpScene);
												Stage window = (Stage) ((Node) event.getSource()).getScene()
														.getWindow();
												window.setResizable(false);
												window.setScene(scene);
												window.show();
											} catch (IOException ex) {
												Logger.getLogger(MemberSignUpController.class.getName())
														.log(Level.SEVERE, null, ex);
											}
										}
									}
								}
							} catch (SQLException ex) {
								Alert alertNrc = new Alert(Alert.AlertType.ERROR,
										"Duplicate NRC or email is not allowed", ButtonType.OK);
								alertNrc.showAndWait();
//                               ex.printStackTrace();
								if (alertNrc.getResult() == ButtonType.OK) {
									event.consume();
								}
							}
						}
					}
				});

			} else {
				invalidAnimation();
				invalidControllThreads();
			}
		} else {
			redCircleAnimation();
			emptyNotiMethod();
			emptyControllThreads();
		}
	}

	public void redCircleAnimation() {
		fadeAnimationItems(fadeAnimationMember);
		fadeAnimationItems(fadeAnimationEmail);
		fadeAnimationItems(fadeAnimationNrc);
		fadeAnimationItems(fadeAnimationPhone);
		fadeAnimationItems(fadeAnimationAddress);
		fadeAnimationItems(fadeAnimationDOB);
		fadeAnimationItems(fadeAnimationHeight);
		fadeAnimationItems(fadeAnimationWeight);
		fadeAnimationItems(fadeAnimationEmptyNoti);
	}

	public void invalidAnimation() {
		fadeAnimationItems(fadeAnimationInvalidName);
		fadeAnimationItems(fadeAnimationInvalidEmail);
		fadeAnimationItems(fadeAnimationInvalidPhone);
		fadeAnimationItems(fadeAnimationInvalidNRC);
		fadeAnimationItems(fadeAnimationInvalidDOB);
		fadeAnimationItems(fadeAnimationInvalidHeight);
		fadeAnimationItems(fadeAnimationInvalidWeight);
	}

	public void fadeAnimationItems(FadeTransition fadeAnimation) {
		fadeAnimation.setDuration(Duration.millis(4000));
		fadeAnimation.setCycleCount(1);
		fadeAnimation.setAutoReverse(false);
		fadeAnimation.setFromValue(1);
		fadeAnimation.setToValue(0);
	}

	public void invalidNameCondition() {
		if (!Pattern.matches("[a-zA-Z\\s]+", memberName.getText())) {
			completeCircName.setFill(Color.RED);
			fadeAnimationInvalidName.setNode(invalidName);
			fadeAnimationInvalidName.play();
		} else {
			completeCircName.setFill(Color.GREEN);
		}
	}

	public void invalidEmailCondition() {
		if (!Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", email.getText())) {
			completeCircEmail.setFill(Color.RED);
			fadeAnimationInvalidEmail.setNode(invalidEmail);
			fadeAnimationInvalidEmail.play();
		} else {
			completeCircEmail.setFill(Color.GREEN);
		}
	}

	public void invalidPhoneCondition() {
		if (!phone.getText().matches("[0]{1}[9]{1}[\\d]{9}")) {
			completeCircPhone.setFill(Color.RED);
			fadeAnimationInvalidPhone.setNode(invalidPhone);
			fadeAnimationInvalidPhone.play();
		} else {
			completeCircPhone.setFill(Color.GREEN);
		}
	}

	public void invalidNRCCondition() {
		if (!Pattern.matches("[1-9]{1,3}/[A-Z]+[(NFPT)]{3}[\\d]{6}", nrc.getText())) {
			completeCircNRC.setFill(Color.RED);
			fadeAnimationInvalidNRC.setNode(invalidNrc);
			fadeAnimationInvalidNRC.play();
		} else {
			completeCircNRC.setFill(Color.GREEN);
		}
	}

	public void invalidDOBCondition() {
		if (LocalDate.now().getYear() - dob.getValue().getYear() < 14) {
			completeCircDOB.setFill(Color.RED);
			fadeAnimationInvalidDOB.setNode(invalidDOB);
			fadeAnimationInvalidDOB.play();
		} else {
			completeCircDOB.setFill(Color.GREEN);
		}
	}

	public void invalidHeightCondition() {
		if (!Pattern.matches("[0-9]{1,3}", height.getText())) {
			completeCircHeight.setFill(Color.RED);
			fadeAnimationInvalidHeight.setNode(invalidHeight);
			fadeAnimationInvalidHeight.play();
		} else {
			completeCircHeight.setFill(Color.GREEN);
		}
	}

	public void invalidWeightCondition() {
		if (!Pattern.matches("[0-9]{1,3}", weight.getText())) {
			completeCircWeight.setFill(Color.RED);
			fadeAnimationInvalidWeight.setNode(invalidWeight);
			fadeAnimationInvalidWeight.play();
		} else {
			completeCircWeight.setFill(Color.GREEN);
		}
	}

	public void memberCondition() {
		if (memberName.getText().isEmpty()) {
			fadeAnimationMember.setNode(nameCircle);
			fadeAnimationMember.play();
		}
	}

	public void emailCondition() {
		if (email.getText().isEmpty()) {
			fadeAnimationEmail.setNode(emailCircle);
			fadeAnimationEmail.play();
		}
	}

	public void nrcCondition() {
		if (nrc.getText().isEmpty()) {
			fadeAnimationNrc.setNode(nrcCircle);
			fadeAnimationNrc.play();
		}
	}

	public void phoneCondition() {
		if (phone.getText().isEmpty()) {
			fadeAnimationPhone.setNode(phoneCircle);
			fadeAnimationPhone.play();
		}
	}

	public void addressCondition() {
		if (address.getText().isEmpty()) {
			fadeAnimationAddress.setNode(addressCircle);
			fadeAnimationAddress.play();
		}
	}

	public void datePickerCondition() {
		if (dob.getValue() == null) {
			fadeAnimationDOB.setNode(dobCircle);
			fadeAnimationDOB.play();
		}
	}

	public void heightCondition() {
		if (height.getText().isEmpty()) {
			fadeAnimationHeight.setNode(heightCircle);
			fadeAnimationHeight.play();
		}
	}

	public void weightCondition() {
		if (weight.getText().isEmpty()) {
			fadeAnimationWeight.setNode(weightCircle);
			fadeAnimationWeight.play();
		}
	}

	public void emptyNotiMethod() {
		fadeAnimationEmptyNoti.setNode(emptyNoti);
		fadeAnimationEmptyNoti.play();
	}

	@FXML
	void emailAction(ActionEvent event) {
		nrc.requestFocus();
		nrc.selectAll();
	}

	@FXML
	void memberAction(ActionEvent event) {
		email.requestFocus();
		email.selectAll();
	}

	@FXML
	void nrcAction(ActionEvent event) {
		phone.requestFocus();
		phone.selectAll();
	}

	@FXML
	void phoneAction(ActionEvent event) {
		address.requestFocus();
		address.selectAll();
	}

	@FXML
	void heightScrollMouseMovedAction(MouseEvent event) {
		sliHeight.valueProperty().addListener((ob, ov, nv) -> {
			height.setText(String.format("%.0f", nv));
		});
	}

	@FXML
	void weightScrollMouseMovedAction(MouseEvent event) {
		sliWeight.valueProperty().addListener((ob, ov, nv) -> {
			weight.setText(String.format("%.0f", nv));
		});
	}

	@FXML
	void weightMousePressedAction(MouseEvent event) {
		weight.textProperty().addListener((ob, ov, nv) -> {
			if (!weight.getText().isEmpty()) {
				try {
					sliWeight.setValue(Double.parseDouble(nv));
				} catch (NumberFormatException e) {
				}
			}
		});
	}

	@FXML
	void heightMousePressedAction(MouseEvent event) {
		height.textProperty().addListener((ob, ov, nv) -> {
			if (!height.getText().isEmpty()) {
				try {
					sliHeight.setValue(Double.parseDouble(nv));
				} catch (NumberFormatException e) {
				}
			}
		});
	}

	public void emptyControllThreads() {
		Thread tMember = new Thread() {
			@Override
			public void run() {
				memberCondition();
			}
		};
		Thread tEmail = new Thread() {
			@Override
			public void run() {
				emailCondition();
			}
		};
		Thread tNrc = new Thread() {
			@Override
			public void run() {
				nrcCondition();
			}
		};
		Thread tPhone = new Thread() {
			@Override
			public void run() {
				phoneCondition();
			}
		};
		Thread tAddress = new Thread() {
			@Override
			public void run() {
				addressCondition();
			}
		};
		Thread tDatePicker = new Thread() {
			@Override
			public void run() {
				datePickerCondition();
			}
		};
		Thread tHeight = new Thread() {
			@Override
			public void run() {
				heightCondition();
			}
		};
		Thread tWeight = new Thread() {
			@Override
			public void run() {
				weightCondition();
			}
		};
		tMember.start();
		try {
			tMember.join();
		} catch (InterruptedException ex) {
			Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
		}
		tEmail.start();
		try {
			tEmail.join();
		} catch (InterruptedException ex) {
			Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
		}
		tNrc.start();
		try {
			tNrc.join();
		} catch (InterruptedException ex) {
			Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
		}
		tPhone.start();
		try {
			tPhone.join();
		} catch (InterruptedException ex) {
			Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
		}
		tAddress.start();
		try {
			tAddress.join();
		} catch (InterruptedException ex) {
			Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
		}
		tDatePicker.start();
		try {
			tDatePicker.join();
		} catch (InterruptedException ex) {
			Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
		}
		tHeight.start();
		try {
			tHeight.join();
		} catch (InterruptedException ex) {
			Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
		}
		tWeight.start();
	}

	public void invalidControllThreads() {
		Thread tInvalidName = new Thread() {
			@Override
			public void run() {
				invalidNameCondition();
			}
		};
		Thread tInvalidEmail = new Thread() {
			@Override
			public void run() {
				invalidEmailCondition();
			}
		};
		Thread tInvalidNRC = new Thread() {
			@Override
			public void run() {
				invalidNRCCondition();
			}
		};
		Thread tInvalidPhone = new Thread() {
			@Override
			public void run() {
				invalidPhoneCondition();
			}
		};
		Thread tInvalidHeight = new Thread() {
			@Override
			public void run() {
				invalidHeightCondition();
			}
		};
		Thread tInvalidWeight = new Thread() {
			@Override
			public void run() {
				invalidWeightCondition();
			}
		};
		Thread tInvalidDOB = new Thread() {
			@Override
			public void run() {
				invalidDOBCondition();
			}
		};
		tInvalidName.start();
		try {
			tInvalidName.join();
		} catch (InterruptedException ex) {
			Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
		}
		tInvalidEmail.start();
		try {
			tInvalidEmail.join();
		} catch (InterruptedException ex) {
			Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
		}
		tInvalidNRC.start();
		try {
			tInvalidNRC.join();
		} catch (InterruptedException ex) {
			Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
		}
		tInvalidPhone.start();
		try {
			tInvalidPhone.join();
		} catch (InterruptedException ex) {
			Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
		}
		tInvalidHeight.start();
		try {
			tInvalidHeight.join();
		} catch (InterruptedException ex) {
			Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
		}
		tInvalidWeight.start();
		try {
			tInvalidWeight.join();
		} catch (InterruptedException ex) {
			Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
		}
		tInvalidDOB.start();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		m.forEach((t) -> {
			cboPackage.getItems().add(t.getName());
		});
		cboPackage.getSelectionModel().selectFirst();

		HamburgerBasicCloseTransition burgerTask = new HamburgerBasicCloseTransition(adminMenu);
		burgerTask.setRate(-1);
		adminMenu.setOnMousePressed((event) -> {
			try {
				adminDrawer.setSidePane(adminMenuScene(title.getText()));
			} catch (IOException ex) {
				Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
			}

		});

		adminMenu.setOnMouseReleased((event) -> {
			burgerTask.setRate(burgerTask.getRate() * -1);
			burgerTask.play();

			if (adminDrawer.isShown()) {
				adminDrawer.close();
			} else {
				adminDrawer.open();
			}
		});

	}

	public AnchorPane adminMenuScene(String titleText) throws IOException {
		JFXButton[] buttons = { adminAccButton, seeUpdateAdminDataButton, memberAccButton, trainerAccButton,
				seeUpdateMemberDataButton, seeUpdateTrainerDataButton, monthlyReportButton, trainerAttButton,
				seeTrainerAttButton, membershipPlanButton, createInvoiceButton, invoiceHistoryButton, logOutButton };
		for (JFXButton button : buttons) {
			button.setPrefWidth(180);
			button.setPrefHeight(40);

			button.setRipplerFill(Color.web("#35ae05"));
			button.setTextFill(Color.web("#000"));
			button.setStyle("-fx-background-color : white;");
			button.setFont(Font.font("Calibri", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 15));
			button.setTextAlignment(TextAlignment.LEFT);
			button.setButtonType(JFXButton.ButtonType.RAISED);
			button.setOnMouseEntered((event) -> {
				button.setTextFill(Color.BLACK);
				button.setStyle("-fx-background-color : #c4ffc4");
				button.setFont(Font.font("Calibri", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 15));
			});
			button.setOnMouseExited((event) -> {
				button.setStyle("-fx-background-color : white");
			});
		}
		switch (titleText) {
		case "Admin Sign Up":
			adminAccButton.setStyle("-fx-background-color : #ffbb00");
			adminAccButton.setTextFill(Color.WHITE);
			adminAccButton.setOnMouseExited((event) -> {
				adminAccButton.setStyle("-fx-background-color : #ffbb00");
				adminAccButton.setTextFill(Color.WHITE);
			});
			break;
		case "Member Sign Up":
			memberAccButton.setStyle("-fx-background-color : #ffbb00");
			memberAccButton.setTextFill(Color.WHITE);
			memberAccButton.setOnMouseExited((event) -> {
				memberAccButton.setStyle("-fx-background-color : #ffbb00");
				memberAccButton.setTextFill(Color.WHITE);
			});
			break;
		case "Trainer Sign Up":
			trainerAccButton.setStyle("-fx-background-color : #ffbb00");
			trainerAccButton.setTextFill(Color.WHITE);
			trainerAccButton.setOnMouseExited((event) -> {
				trainerAccButton.setStyle("-fx-background-color : #ffbb00");
				trainerAccButton.setTextFill(Color.WHITE);
			});
			break;
		case "My Data":
			seeUpdateAdminDataButton.setStyle("-fx-background-color : #ffbb00");
			seeUpdateAdminDataButton.setTextFill(Color.WHITE);
			seeUpdateAdminDataButton.setOnMouseExited((event) -> {
				seeUpdateAdminDataButton.setStyle("-fx-background-color : #ffbb00");
				seeUpdateAdminDataButton.setTextFill(Color.WHITE);
			});
			break;
		case "Member Data":
			seeUpdateMemberDataButton.setStyle("-fx-background-color : #ffbb00");
			seeUpdateMemberDataButton.setTextFill(Color.WHITE);
			seeUpdateMemberDataButton.setOnMouseExited((event) -> {
				seeUpdateMemberDataButton.setStyle("-fx-background-color : #ffbb00");
				seeUpdateMemberDataButton.setTextFill(Color.WHITE);
			});
			break;
		case "Trainer Data":
			seeUpdateTrainerDataButton.setStyle("-fx-background-color : #ffbb00");
			seeUpdateTrainerDataButton.setTextFill(Color.WHITE);
			seeUpdateTrainerDataButton.setOnMouseExited((event) -> {
				seeUpdateTrainerDataButton.setStyle("-fx-background-color : #ffbb00");
				seeUpdateTrainerDataButton.setTextFill(Color.WHITE);
			});
			break;
		case "Monthly Report":
			monthlyReportButton.setStyle("-fx-background-color : #ffbb00");
			monthlyReportButton.setTextFill(Color.WHITE);
			monthlyReportButton.setOnMouseExited((event) -> {
				monthlyReportButton.setStyle("-fx-background-color : #ffbb00");
				monthlyReportButton.setTextFill(Color.WHITE);
			});
			break;
		case "Record Trainer Attendance":
			trainerAttButton.setStyle("-fx-background-color : #ffbb00");
			trainerAttButton.setTextFill(Color.WHITE);
			trainerAttButton.setOnMouseExited((event) -> {
				trainerAttButton.setStyle("-fx-background-color : #ffbb00");
				trainerAttButton.setTextFill(Color.WHITE);
			});
			break;
		case "Trainer Attendance Data":
			seeTrainerAttButton.setStyle("-fx-background-color : #ffbb00");
			seeTrainerAttButton.setTextFill(Color.WHITE);
			seeTrainerAttButton.setOnMouseExited((event) -> {
				seeTrainerAttButton.setStyle("-fx-background-color : #ffbb00");
				seeTrainerAttButton.setTextFill(Color.WHITE);
			});
			break;
		case "Membership Plan":
			membershipPlanButton.setStyle("-fx-background-color : #ffbb00");
			membershipPlanButton.setTextFill(Color.WHITE);
			membershipPlanButton.setOnMouseExited((event) -> {
				membershipPlanButton.setStyle("-fx-background-color : #ffbb00");
				membershipPlanButton.setTextFill(Color.WHITE);
			});
			break;
		case "Create Invoice":
			createInvoiceButton.setStyle("-fx-background-color : #ffbb00");
			createInvoiceButton.setTextFill(Color.WHITE);
			createInvoiceButton.setOnMouseExited((event) -> {
				createInvoiceButton.setStyle("-fx-background-color : #ffbb00");
				createInvoiceButton.setTextFill(Color.WHITE);
			});
			break;
		case "Invoice History":
			invoiceHistoryButton.setStyle("-fx-background-color : #ffbb00");
			invoiceHistoryButton.setTextFill(Color.WHITE);
			invoiceHistoryButton.setOnMouseExited((event) -> {
				invoiceHistoryButton.setStyle("-fx-background-color : #ffbb00");
				invoiceHistoryButton.setTextFill(Color.WHITE);
			});
			break;
		default:
			break;
		}

		adminAccButton.setLayoutX(0);
		adminAccButton.setLayoutY(0);

		memberAccButton.setLayoutX(0);
		memberAccButton.setLayoutY(46);

		trainerAccButton.setLayoutX(0);
		trainerAccButton.setLayoutY(92);

		seeUpdateAdminDataButton.setLayoutX(0);
		seeUpdateAdminDataButton.setLayoutY(138);

		seeUpdateMemberDataButton.setLayoutX(0);
		seeUpdateMemberDataButton.setLayoutY(184);

		seeUpdateTrainerDataButton.setLayoutX(0);
		seeUpdateTrainerDataButton.setLayoutY(230);

		monthlyReportButton.setLayoutX(0);
		monthlyReportButton.setLayoutY(276);

		trainerAttButton.setLayoutX(0);
		trainerAttButton.setLayoutY(322);

		seeTrainerAttButton.setLayoutX(0);
		seeTrainerAttButton.setLayoutY(368);

		membershipPlanButton.setLayoutX(0);
		membershipPlanButton.setLayoutY(414);

		createInvoiceButton.setLayoutX(0);
		createInvoiceButton.setLayoutY(460);

		invoiceHistoryButton.setLayoutX(0);
		invoiceHistoryButton.setLayoutY(506);

		logOutButton.setLayoutX(0);
		logOutButton.setLayoutY(552);

		AnchorPane adminMenuScene = new AnchorPane();
		adminMenuScene.setPrefWidth(180);
		adminMenuScene.setPrefHeight(425);
		adminMenuScene.setStyle("-fx-background-color : white");
		adminMenuScene.getChildren().addAll(adminAccButton, memberAccButton, trainerAccButton, seeUpdateAdminDataButton,
				seeUpdateMemberDataButton, seeUpdateTrainerDataButton, monthlyReportButton, trainerAttButton,
				seeTrainerAttButton, membershipPlanButton, createInvoiceButton, invoiceHistoryButton, logOutButton);

		adminAccButton.setOnAction((event) -> {
//            JFXButton[] buttonsForColor = {seeUpdateAdminDataButton, memberAccButton, trainerAccButton, seeUpdateMemberDataButton, seeUpdateTrainerDataButton, monthlyReportButton,
//                trainerAttButton, seeTrainerAttButton, membershipPlanButton, createInvoiceButton, invoiceHistoryButton, logOutButton};
//            adminAccButton.setStyle("-fx-background-color : #35ae50");
//            for (JFXButton button : buttonsForColor) {
//                button.setStyle("-fx-background-color : white");
//            }
			try {
				Parent memberSignUpScene = FXMLLoader.load(getClass().getResource("AdminSignUpScene.fxml"));
				Scene scene = new Scene(memberSignUpScene);
				Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
				window.setResizable(false);
				window.setScene(scene);
				window.show();
			} catch (IOException ex) {
				Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
			}

		});

		seeUpdateAdminDataButton.setOnAction((event) -> {
//            JFXButton[] buttonsForColor = {adminAccButton, seeUpdateAdminDataButton, memberAccButton, trainerAccButton, seeUpdateMemberDataButton, seeUpdateTrainerDataButton, monthlyReportButton,
//                trainerAttButton, seeTrainerAttButton, membershipPlanButton, createInvoiceButton, invoiceHistoryButton, logOutButton};
//            seeUpdateAdminDataButton.setStyle("-fx-background-color : #35ae50");
//            for (JFXButton button : buttonsForColor) {
//                button.setStyle("-fx-background-color : white");
//            }
			if (ConnectDB.CreateConnection() != null) {
				String sql = "SELECT * FROM admin";

				try (Connection con = ConnectDB.CreateConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

					ResultSet rs = ps.executeQuery();

					while (rs.next()) {

						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(getClass().getResource("SeeAndUpdateAdminDataScene.fxml"));
						Parent trainerMyAccountScene = loader.load();
						SeeAndUpdateAdminDataController controller = loader.getController();
						Scene scene = new Scene(trainerMyAccountScene);
						scene.getStylesheets().add("CSS/TableDesign.css");
						Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
						if (rs.getString("email").equals(window.getTitle())) {
							controller.addingData(rs.getString("id"), rs.getString("name"), rs.getString("password"),
									rs.getString("email"), rs.getString("phone"));
							window.setResizable(false);
							window.setScene(scene);
							window.show();
						}

					}

				} catch (Exception e) {

				}
			}

		});

		memberAccButton.setOnAction((event) -> {
			try {
				Parent memberSignUpScene = FXMLLoader.load(getClass().getResource("MemberSignUp.fxml"));
				Scene scene = new Scene(memberSignUpScene);
				Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
				window.setResizable(false);
				window.setScene(scene);
				window.show();
			} catch (IOException ex) {
				Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
			}

		});

		trainerAccButton.setOnAction((event) -> {
			try {
				Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("TrainerSignUp.fxml"));
				Scene scene = new Scene(trainerSignUpScene);
				Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
				window.setResizable(false);
				window.setScene(scene);
				window.show();
			} catch (IOException ex) {
				Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
			}

		});

		seeUpdateMemberDataButton.setOnAction((event) -> {
			try {
				Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("SeeAndUpdateMemberDataScene.fxml"));
				Scene scene = new Scene(trainerSignUpScene);
				scene.getStylesheets().add("CSS/TableDesign.css");
				Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
				window.setResizable(false);
				window.setScene(scene);
				window.show();
			} catch (IOException ex) {
				Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
			}

		});

		seeUpdateTrainerDataButton.setOnAction((event) -> {
			try {
				Parent trainerSignUpScene = FXMLLoader
						.load(getClass().getResource("SeeAndUpdateTrainerDataScene.fxml"));
				Scene scene = new Scene(trainerSignUpScene);
				scene.getStylesheets().add("CSS/TableDesign.css");
				Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
				window.setResizable(false);
				window.setScene(scene);
				window.show();
			} catch (IOException ex) {
				Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
			}

		});

		monthlyReportButton.setOnAction((event) -> {
			try {
				Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("MonthlyReportScene.fxml"));
				Scene scene = new Scene(trainerSignUpScene);
				scene.getStylesheets().add("CSS/TableDesign.css");
				Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
				window.setResizable(false);
				window.setScene(scene);
				window.show();
			} catch (IOException ex) {
				Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
			}

		});

		trainerAttButton.setOnAction((event) -> {
			try {
				Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("TrainerAttendanceScene.fxml"));
				Scene scene = new Scene(trainerSignUpScene);
				scene.getStylesheets().add("CSS/TableDesign.css");
				Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
				window.setResizable(false);
				window.setScene(scene);
				window.show();
			} catch (IOException ex) {
				Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
			}

		});

		seeTrainerAttButton.setOnAction((event) -> {
			try {
				Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("SeeAndUpdateTrainerAttScene.fxml"));
				Scene scene = new Scene(trainerSignUpScene);
				scene.getStylesheets().add("CSS/TableDesign.css");
				Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
				window.setResizable(false);
				window.setScene(scene);
				window.show();
			} catch (IOException ex) {
				Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
			}

		});

		membershipPlanButton.setOnAction((event) -> {
			try {
				Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("MembershipPlanScene.fxml"));
				Scene scene = new Scene(trainerSignUpScene);
				scene.getStylesheets().add("CSS/TableDesign.css");
				Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
				window.setResizable(false);
				window.setScene(scene);
				window.show();
			} catch (IOException ex) {
				Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
			}

		});

		createInvoiceButton.setOnAction((event) -> {
			try {
				Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("CreateInvoiceScene.fxml"));
				Scene scene = new Scene(trainerSignUpScene);
				scene.getStylesheets().add("CSS/TableDesign.css");
				Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
				window.setResizable(false);
				window.setScene(scene);
				window.show();
			} catch (IOException ex) {
				Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
			}

		});

		invoiceHistoryButton.setOnAction((event) -> {
			try {
				Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("InvoiceHistoryScene.fxml"));
				Scene scene = new Scene(trainerSignUpScene);
				scene.getStylesheets().add("CSS/TableDesign.css");
				Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
				window.setResizable(false);
				window.setScene(scene);
				window.show();
			} catch (IOException ex) {
				Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
			}

		});

		logOutButton.setOnAction((event) -> {
			try {
				Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure to log out?", ButtonType.YES,
						ButtonType.NO);
				alert.showAndWait();
				if (alert.getResult() == ButtonType.YES) {
					Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("TrainerAdminSignInScene.fxml"));
					Scene scene = new Scene(trainerSignUpScene);
					Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
					window.setTitle("NutriFit");
					window.setResizable(false);
					window.setScene(scene);
					window.show();
				} else {
					event.consume();
				}

			} catch (IOException ex) {
				Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
			}

		});

		return adminMenuScene;
	}

}
