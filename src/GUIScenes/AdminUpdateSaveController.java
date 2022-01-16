package GUIScenes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import db.ConnectDB;
import db.admin;
import db.trainer;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AdminUpdateSaveController extends Stage implements Initializable {

    private FadeTransition fadeAnimationMember = new FadeTransition();
    private FadeTransition fadeAnimationEmail = new FadeTransition();
    private FadeTransition fadeAnimationPhone = new FadeTransition();
    private FadeTransition fadeAnimationEmptyNoti = new FadeTransition();
    private FadeTransition fadeAnimationInvalidName = new FadeTransition();
    private FadeTransition fadeAnimationInvalidEmail = new FadeTransition();
    private FadeTransition fadeAnimationInvalidPhone = new FadeTransition();
    
    @FXML
    private Circle nameCircle;

    @FXML
    private Circle emailCircle;

    @FXML
    private Circle phoneCircle;

    @FXML
    private Rectangle welcomeTemplate;

    @FXML
    private Text txtWelcome;

    @FXML
    private Text txtNutriFit;

    @FXML
    private Circle completeCircName;

    @FXML
    private Circle completeCircEmail;

    @FXML
    private Circle completeCircPhone;

    @FXML
    private Rectangle titleTemplate;

    @FXML
    private JFXTextField trainerName;

    @FXML
    private Text titleTrainerSignUp;

    @FXML
    private JFXButton signUp;

    @FXML
    private Text emptyNoti;

    @FXML
    private JFXTextField email;

    @FXML
    private Text invalidName;

    @FXML
    private Text invalidEmail;

    @FXML
    private JFXTextField phone;

    @FXML
    private Text invalidPhone;
    
    private admin selected;
    
    @FXML
    private JFXRadioButton onJob;

    @FXML
    private ToggleGroup jobStatus;

    @FXML
    private JFXRadioButton offJob;
    
    @FXML
    private Text idWatcher;
    
    @FXML
    private SVGPath backArrowShape;
    
    @FXML
    void backArrowMouseEnteredAction(MouseEvent event) {
        backArrowShape.setFill(Color.web("#c4ffc4"));
    }

    @FXML
    void backArrowMouseExitedAction(MouseEvent event) {
        backArrowShape.setFill(Color.WHITE);
    }

    @FXML
    void backArrowMousePressedAction(MouseEvent event) {
        backArrowShape.setFill(Color.web("#35ae50"));
    }

    @FXML
    void backArrowMouseReleasedAction(MouseEvent event) {
        if (ConnectDB.CreateConnection() != null) {
            String sql = "SELECT * FROM admin";

            try (Connection con = ConnectDB.CreateConnection();
                    PreparedStatement ps = con.prepareStatement(sql);) {

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
                        controller.addingData(rs.getString("id"), rs.getString("name"), rs.getString("password"), rs.getString("email"), rs.getString("phone"));
                        window.setResizable(false);
                        window.setScene(scene);
                        window.show();
                    }

                }

            } catch (Exception e) {

            }
        }
    }

    public void rawData(admin admin) {
        selected = admin;
        idWatcher.setText(selected.getId());
        trainerName.setText(selected.getName());
        email.setText(selected.getEmail());
        phone.setText(selected.getPhone());
        if (selected.getStatus().equals("On Job")) {
            onJob.setSelected(true);
        } else {
            offJob.setSelected(true);
        }
    }
    
    @FXML
    void signUpPressedAction(MouseEvent event) {
        if (!trainerName.getText().isEmpty() && !email.getText().isEmpty() && !phone.getText().isEmpty()) {
            if (completeCircName.getFill() == Color.RED || completeCircEmail.getFill() == Color.RED || completeCircPhone.getFill() == Color.RED) {
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
        if (!trainerName.getText().isEmpty() && !email.getText().isEmpty() && !phone.getText().isEmpty()) {
            if (completeCircName.getFill() == Color.GREEN && completeCircEmail.getFill() == Color.GREEN && completeCircPhone.getFill() == Color.GREEN) {

                String status;

                if (onJob.isSelected()) {
                    status = "On Job";
                } else {
                    status = "Off Job";
                }
                try {
                    admin a = new admin(idWatcher.getText(), trainerName.getText(), selected.getPassword(), email.getText(), phone.getText(), status);
                    db.admin_table.updateAdmin(a);

                    Alert alertRecorded = new Alert(Alert.AlertType.INFORMATION, "Changed data are recorded", ButtonType.OK);
                    alertRecorded.showAndWait();
                    if (alertRecorded.getResult() == ButtonType.OK) {
                        if (ConnectDB.CreateConnection() != null) {
                            String sql = "SELECT * FROM admin";

                            try (Connection con = ConnectDB.CreateConnection();
                                    PreparedStatement ps = con.prepareStatement(sql);) {

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
                                        controller.addingData(rs.getString("id"), rs.getString("name"), rs.getString("password"), rs.getString("email"), rs.getString("phone"));
                                        window.setResizable(false);
                                        window.setScene(scene);
                                        window.show();
                                    }

                                }

                            } catch (Exception e) {

                            }
                        }
                    }
                } catch (SQLException e) {

                }
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
        fadeAnimationItems(fadeAnimationPhone);
        fadeAnimationItems(fadeAnimationEmptyNoti);
    }

    public void invalidAnimation() {
        fadeAnimationItems(fadeAnimationInvalidName);
        fadeAnimationItems(fadeAnimationInvalidEmail);
        fadeAnimationItems(fadeAnimationInvalidPhone);
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

    public void phoneCondition() {
        if (phone.getText().isEmpty()) {
            fadeAnimationPhone.setNode(phoneCircle);
            fadeAnimationPhone.play();
        }
    }

    public void emptyNotiMethod() {
        fadeAnimationEmptyNoti.setNode(emptyNoti);
        fadeAnimationEmptyNoti.play();
    }

    @FXML
    void emailAction(ActionEvent event) {
        phone.requestFocus();
        phone.selectAll();
    }

    @FXML
    void memberAction(ActionEvent event) {
        email.requestFocus();
        email.selectAll();
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
        Thread tPhone = new Thread() {
            @Override
            public void run() {
                phoneCondition();
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
        tPhone.start();
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
        Thread tInvalidPhone = new Thread() {
            @Override
            public void run() {
                invalidPhoneCondition();
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
        tInvalidPhone.start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
        
}
