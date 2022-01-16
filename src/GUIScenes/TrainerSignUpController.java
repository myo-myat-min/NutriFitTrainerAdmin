package GUIScenes;

//import database.Member;
//import database.Membertable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import db.ConnectDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TrainerSignUpController extends Stage implements Initializable {

    private FadeTransition fadeAnimationMember = new FadeTransition();
    private FadeTransition fadeAnimationEmail = new FadeTransition();
    private FadeTransition fadeAnimationNrc = new FadeTransition();
    private FadeTransition fadeAnimationPhone = new FadeTransition();
    private FadeTransition fadeAnimationFees = new FadeTransition();
    private FadeTransition fadeAnimationAddress = new FadeTransition();
    private FadeTransition fadeAnimationDOB = new FadeTransition();
    private FadeTransition fadeAnimationEmptyNoti = new FadeTransition();
    private FadeTransition fadeAnimationInvalidName = new FadeTransition();
    private FadeTransition fadeAnimationInvalidEmail = new FadeTransition();
    private FadeTransition fadeAnimationInvalidNRC = new FadeTransition();
    private FadeTransition fadeAnimationInvalidPhone = new FadeTransition();
    private FadeTransition fadeAnimationInvalidFees = new FadeTransition();
    private FadeTransition fadeAnimationInvalidDOB = new FadeTransition();

    @FXML
    protected Circle nameCircle;

    @FXML
    protected Circle emailCircle;

    @FXML
    protected Circle nrcCircle;

    @FXML
    protected Circle dobCircle;
    
    @FXML
    private Circle feesCircle;

    @FXML
    protected Rectangle welcomeTemplate;

    @FXML
    protected Text txtWelcome;

    @FXML
    protected Text txtNutriFit;

    @FXML
    protected Rectangle titleTemplate;

    @FXML
    public JFXTextField trainerName;

    @FXML
    public JFXTextField nrc;

    @FXML
    public JFXTextField phone;

    @FXML
    protected JFXRadioButton male;

    @FXML
    protected ToggleGroup gender;

    @FXML
    protected JFXRadioButton female;

    @FXML
    public JFXDatePicker dob;

    @FXML
    protected Text titleTrainerSignUp;

    @FXML
    public JFXButton signUp;

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
    protected JFXTextField fees;

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
    private Circle completeCircName;

    @FXML
    private Circle completeCircEmail;
    
    @FXML
    private Circle completeCircPhone;
    
    @FXML
    private Circle completeCircFees;

    @FXML
    private Circle completeCircNRC;

    @FXML
    protected JFXDrawer adminDrawer;

    @FXML
    private Text invalidDOB;
    
    @FXML
    private Text invalidFees;

    @FXML
    private Circle completeCircDOB;
    
//    public void clearTextField(TrainerSignUpController trainerSignUp){
//        trainerSignUp.trainerName.setText(this.trainerName.getText());
//        System.out.println(trainerSignUp.trainerName.getText());
//    }

    @FXML
    void signUpPressedAction(MouseEvent event) {
        if (!trainerName.getText().isEmpty() && !email.getText().isEmpty() && !nrc.getText().isEmpty() && !phone.getText().isEmpty() && !fees.getText().isEmpty()
                && !address.getText().isEmpty() && dob.getValue() != null) {
            if (completeCircName.getFill() == Color.RED || completeCircEmail.getFill() == Color.RED
                    || completeCircPhone.getFill() == Color.RED || completeCircNRC.getFill() == Color.RED
                    || completeCircDOB.getFill() == Color.RED || completeCircFees.getFill() == Color.RED) {
                invalidAnimation();
                invalidControllThreads();
            }
        } else {
            redCircleAnimation();
            emptyNotiMethod();
            emptyControllThreads();
        }
    }

    @FXML
    void signUpReleasedAction(MouseEvent event) {
        if (!trainerName.getText().isEmpty() && !email.getText().isEmpty() && !nrc.getText().isEmpty() && !phone.getText().isEmpty() && !fees.getText().isEmpty()
                && !address.getText().isEmpty() && dob.getValue() != null) {
            if (completeCircName.getFill() == Color.GREEN && completeCircEmail.getFill() == Color.GREEN
                    && completeCircPhone.getFill() == Color.GREEN && completeCircNRC.getFill() == Color.GREEN
                    && completeCircDOB.getFill() == Color.GREEN && completeCircFees.getFill() == Color.GREEN) {

                String maleOrFemale;
                String status = "On Job";
                int count = 0;
                
                if (male.isSelected()) {
                    maleOrFemale = "Male";
                } else {
                    maleOrFemale = "Female";
                }
                MemberSignUpController memberSignUpControl = new MemberSignUpController();
                String password = memberSignUpControl.randomAlphaNumeric(11);
                db.trainer_table trainerTable = new db.trainer_table();
                try {
                    trainerTable.insert_trainer(new db.trainer(trainerName.getText(), password, dob.getValue(), maleOrFemale, email.getText(),
                            nrc.getText(), phone.getText(), address.getText(), status, Integer.parseInt(fees.getText())));

                    TrainerSignInController trainerSignInControl = new TrainerSignInController();
                    trainerSignInControl.send("mstzsn01@gmail.com", "qazmlp123!", email.getText(), "NutriFit - Password", password);

                    new Alert(Alert.AlertType.INFORMATION, "Trainer data are recorded", ButtonType.OK).showAndWait();
                    try {
                        Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("TrainerSignUp.fxml"));
                        Scene scene = new Scene(trainerSignUpScene);
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setResizable(false);
                        window.setScene(scene);
                        window.show();
                    } catch (IOException e) {
                        Logger.getLogger(TrainerSignUpController.class.getName()).log(Level.SEVERE, null, e);
                    }
                } catch (SQLException ex) {
                    Alert alertNrc = new Alert(Alert.AlertType.ERROR, "Duplicate NRC or email is not allowed", ButtonType.OK);
                    alertNrc.showAndWait();
//                               ex.printStackTrace();
                    if (alertNrc.getResult() == ButtonType.OK) {
                        event.consume();
                    }
                }

//                    String sql = "SELECT nrc FROM trainer ORDER BY id DESC LIMIT 1";
//
//                    try (Connection con = ConnectDB.CreateConnection();
//                            PreparedStatement ps = con.prepareStatement(sql);) {
//
//                        ResultSet rs = ps.executeQuery();
//
//                        while (rs.next()) {
//                            System.out.println(rs.getString("nrc"));
//                            System.out.println(count++);
//                            if (!rs.getString("nrc").equals(nrc.getText())) {
//                                if (!rs.getString("nrc").equals(nrc.getText())) {
//                try {
                
//                    try {
//                        Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("TrainerSignUp.fxml"));
//                        Scene scene = new Scene(trainerSignUpScene, 1360, 700);
//                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                        window.setResizable(false);
//                        window.setScene(scene);
//                        window.show();
//                    } catch (IOException ex) {
//                        Logger.getLogger(TrainerSignUpController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                    
//                } catch (SQLException ex) {
//                    new Alert(AlertType.ERROR, "Duplicated NRC is not allowed", ButtonType.OK).showAndWait();
//                }
                                    
//                                }

//                            } else {
//                                Alert alertNrc = new Alert(Alert.AlertType.ERROR, "Duplicated NRC is not allowed", ButtonType.OK);
//                                alertNrc.showAndWait();
//                                if (alertNrc.getResult() == ButtonType.OK) {
//                                    event.consume();
//                                }
//                                break;
//                            }
//                        }
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    new Alert(Alert.AlertType.ERROR, "Please connect MYSQL server", ButtonType.OK).showAndWait();
//                }

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
        fadeAnimationItems(fadeAnimationFees);
        fadeAnimationItems(fadeAnimationAddress);
        fadeAnimationItems(fadeAnimationDOB);
        fadeAnimationItems(fadeAnimationEmptyNoti);
    }

    public void invalidAnimation() {
        fadeAnimationItems(fadeAnimationInvalidName);
        fadeAnimationItems(fadeAnimationInvalidEmail);
        fadeAnimationItems(fadeAnimationInvalidPhone);
        fadeAnimationItems(fadeAnimationInvalidFees);
        fadeAnimationItems(fadeAnimationInvalidNRC);
        fadeAnimationItems(fadeAnimationInvalidDOB);
    }

    public void fadeAnimationItems(FadeTransition fadeAnimation) {
        fadeAnimation.setDuration(Duration.millis(4000));
        fadeAnimation.setCycleCount(1);
        fadeAnimation.setAutoReverse(false);
        fadeAnimation.setFromValue(1);
        fadeAnimation.setToValue(0);
    }

    public void invalidNameCondition() {
        if (!Pattern.matches("[a-zA-Z\\s]+", trainerName.getText())) {
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
    
    public void invalidFeesCondition() {
        if (!fees.getText().matches("[\\d]{1,}")) {
            completeCircFees.setFill(Color.RED);
            fadeAnimationInvalidFees.setNode(invalidFees);
            fadeAnimationInvalidFees.play();
        } else {
            completeCircFees.setFill(Color.GREEN);
        }
    }

    public void invalidNRCCondition() {
        if (!Pattern.matches("[\\d]{1,3}/[A-Z]+[(NFPT)]{3}[\\d]{6}", nrc.getText())) {
            completeCircNRC.setFill(Color.RED);
            fadeAnimationInvalidNRC.setNode(invalidNrc);
            fadeAnimationInvalidNRC.play();
        } else {
            completeCircNRC.setFill(Color.GREEN);
        }
    }

    public void invalidDOBCondition() {
        if (LocalDate.now().getYear() - dob.getValue().getYear() < 18) {
            completeCircDOB.setFill(Color.RED);
            fadeAnimationInvalidDOB.setNode(invalidDOB);
            fadeAnimationInvalidDOB.play();
        } else {
            completeCircDOB.setFill(Color.GREEN);
        }
    }

    public void memberCondition() {
        if (trainerName.getText().isEmpty()) {
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
    
    public void feesCondition() {
        if (fees.getText().isEmpty()) {
            fadeAnimationFees.setNode(feesCircle);
            fadeAnimationFees.play();
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
        fees.requestFocus();
        fees.selectAll();
    }
    
    @FXML
    void feesAction(ActionEvent event) {
        address.requestFocus();
        address.selectAll();
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
        Thread tFees = new Thread() {
            @Override
            public void run() {
                feesCondition();
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
        tMember.start();
        try {
            tMember.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(TrainerSignUpController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(TrainerSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tPhone.start();
        try {
            tPhone.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(TrainerSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tAddress.start();
        try {
            tAddress.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(TrainerSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tDatePicker.start();
        try {
            tDatePicker.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tFees.start();
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
        Thread tInvalidFees = new Thread() {
            @Override
            public void run() {
                invalidFeesCondition();
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
            Logger.getLogger(TrainerSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tInvalidEmail.start();
        try {
            tInvalidEmail.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(TrainerSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tInvalidNRC.start();
        try {
            tInvalidNRC.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(TrainerSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tInvalidPhone.start();
        try {
            tInvalidPhone.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(TrainerSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tInvalidDOB.start();
        try {
            tInvalidDOB.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tInvalidFees.start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fees.setText("100000");
        MemberSignUpController memSignUpControll = new MemberSignUpController();
        HamburgerBasicCloseTransition burgerTask = new HamburgerBasicCloseTransition(adminMenu);
        burgerTask.setRate(-1);
        adminMenu.setOnMousePressed((event) -> {
            try {
                adminDrawer.setSidePane(memSignUpControll.adminMenuScene(titleTrainerSignUp.getText()));
            } catch (IOException ex) {
                Logger.getLogger(TrainerSignUpController.class.getName()).log(Level.SEVERE, null, ex);
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
}
