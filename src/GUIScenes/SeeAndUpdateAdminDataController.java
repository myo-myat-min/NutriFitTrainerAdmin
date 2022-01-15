package GUIScenes;

//import database.Member;
//import database.Membertable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import db.ConnectDB;
import db.trainer;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import db.admin;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SeeAndUpdateAdminDataController extends Stage implements Initializable {

    private FadeTransition fadeAnimationMember = new FadeTransition();
    private FadeTransition fadeAnimationEmail = new FadeTransition();
    private FadeTransition fadeAnimationPass = new FadeTransition();
    private FadeTransition fadeAnimationPassComfirm = new FadeTransition();
    private FadeTransition fadeAnimationPhone = new FadeTransition();
    private FadeTransition fadeAnimationEmptyNoti = new FadeTransition();
    private FadeTransition fadeAnimationPassWrongNoti = new FadeTransition();
    private FadeTransition fadeAnimationInvalidName = new FadeTransition();
    private FadeTransition fadeAnimationInvalidEmail = new FadeTransition();
    private FadeTransition fadeAnimationWeakPassNoti = new FadeTransition();
    private FadeTransition fadeAnimationInvalidPassNoti = new FadeTransition();
    private FadeTransition fadeAnimationInvalidPhone = new FadeTransition();

    @FXML
    private Circle nameCircle;

    @FXML
    private Circle emailCircle;

    @FXML
    private Circle passCircle;

    @FXML
    private Circle confirmCircle;

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
    private Circle completeCircPass;

    @FXML
    private Circle completeCircWeakPass;

    @FXML
    private Circle completeCircInvalidPass;

    @FXML
    private Rectangle titleTemplate;

    @FXML
    private JFXTextField trainerName;

    @FXML
    private JFXPasswordField pass;

    @FXML
    private JFXPasswordField confirmPass;

    @FXML
    private Text titleTrainerSignUp;

    @FXML
    private JFXButton signUp;

    @FXML
    private JFXHamburger adminMenu;

    @FXML
    private Text emptyNoti;

    @FXML
    private JFXTextField email;

    @FXML
    private Text passWrongNoti;

    @FXML
    private Text invalidName;

    @FXML
    private Text invalidEmail;

    @FXML
    private Text invalidPass;

    @FXML
    private JFXDrawer adminDrawer;

    @FXML
    private Text tooWeakPass;

    @FXML
    private JFXCheckBox editableMode;

    @FXML
    private Circle phoneCircle;

    @FXML
    private JFXTextField phone;

    @FXML
    private Text invalidPhone;

    @FXML
    private Circle completeCircPhone;

    private admin selected;

    @FXML
    private Text idWatcher;

    @FXML
    private TableView<admin> adminTable;

    @FXML
    private TableColumn<admin, String> idCol;

    @FXML
    private TableColumn<admin, String> nameCol;

    @FXML
    private TableColumn<admin, String> emailCol;

    @FXML
    private TableColumn<admin, String> phoneCol;

    @FXML
    private TableColumn<admin, String> statusCol;

    @FXML
    private JFXTextField txtFieldSearchById;

    @FXML
    private JFXTextField txtFieldSearchByName;

    @FXML
    private JFXButton updateTableButton;

    public void addingData(admin a) {
        selected = a;
        trainerName.setText(selected.getName());
        this.email.setText(selected.getEmail());
        this.pass.setText(selected.getPassword());
        confirmPass.setText(selected.getPassword());
    }

    public void addingData(String id, String name, String pass, String email, String phone) {
        idWatcher.setText(id);
        trainerName.setText(name);
        this.email.setText(email);
        this.pass.setText(pass);
        confirmPass.setText(pass);
        this.phone.setText(phone);
    }

    @FXML
    void signUpPressedAction(MouseEvent event) {
        if (!trainerName.getText().isEmpty() && !email.getText().isEmpty() && !pass.getText().isEmpty()
                && !confirmPass.getText().isEmpty() && !phone.getText().isEmpty()) {
            if (completeCircName.getFill() == Color.RED || completeCircEmail.getFill() == Color.RED || completeCircPass.getFill() == Color.RED
                    || completeCircInvalidPass.getFill() == Color.RED || completeCircWeakPass.getFill() == Color.RED || completeCircPhone.getFill() == Color.RED) {
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
        if (!trainerName.getText().isEmpty() && !email.getText().isEmpty() && !pass.getText().isEmpty()
                && !confirmPass.getText().isEmpty() && !phone.getText().isEmpty()) {
            if (completeCircName.getFill() == Color.GREEN && completeCircEmail.getFill() == Color.GREEN && completeCircPass.getFill() == Color.GREEN
                    && completeCircInvalidPass.getFill() == Color.GREEN && completeCircWeakPass.getFill() == Color.GREEN && completeCircPhone.getFill() == Color.GREEN) {

                try {
                    admin a = new admin(idWatcher.getText(), trainerName.getText(), pass.getText(), email.getText(), phone.getText(), "On Job");
                    db.admin_table.updateAdmin(a);

                    Alert alertRecorded = new Alert(Alert.AlertType.INFORMATION, "Changed data are recorded", ButtonType.OK);
                    alertRecorded.showAndWait();
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setTitle(email.getText());

                    adminTable.getItems().clear();
                    ArrayList<admin> a1 = db.admin_table.showall();
                    adminTable.getItems().addAll(a1);
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, "Duplicate email is not allowed", ButtonType.OK).showAndWait();
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
        fadeAnimationItems(fadeAnimationPass);
        fadeAnimationItems(fadeAnimationPassComfirm);
        fadeAnimationItems(fadeAnimationEmptyNoti);
        fadeAnimationItems(fadeAnimationPhone);
    }

    public void invalidAnimation() {
        fadeAnimationItems(fadeAnimationInvalidName);
        fadeAnimationItems(fadeAnimationInvalidEmail);
        fadeAnimationItems(fadeAnimationInvalidPhone);
        fadeAnimationItems(fadeAnimationPassWrongNoti);
        fadeAnimationItems(fadeAnimationWeakPassNoti);
        fadeAnimationItems(fadeAnimationInvalidPassNoti);
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

    public void differentPassCondition() {
        if (!pass.getText().equals(confirmPass.getText())) {
            completeCircPass.setFill(Color.RED);
            fadeAnimationPassWrongNoti.setNode(passWrongNoti);
            fadeAnimationPassWrongNoti.play();
        } else {
            completeCircPass.setFill(Color.GREEN);
        }
    }

    public void weakPassCondition() {
        if (!Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{1,}$", pass.getText())) {
            completeCircWeakPass.setFill(Color.RED);
            fadeAnimationPassWrongNoti.setNode(tooWeakPass);
            fadeAnimationPassWrongNoti.play();
        } else {
            completeCircWeakPass.setFill(Color.GREEN);
        }
    }

    public void invalidPassCondition() {
        int count = 0;
        for (int i = 1; i <= pass.getText().length(); i++) {
            count++;
        }
        if (count > 20 || count < 8) {
            completeCircInvalidPass.setFill(Color.RED);
            fadeAnimationInvalidPassNoti.setNode(invalidPass);
            fadeAnimationInvalidPassNoti.play();
        } else {
            completeCircInvalidPass.setFill(Color.GREEN);
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

    public void passCondition() {
        if (pass.getText().isEmpty()) {
            fadeAnimationPass.setNode(passCircle);
            fadeAnimationPass.play();
        }
    }

    public void passConfirmCondition() {
        if (confirmPass.getText().isEmpty()) {
            fadeAnimationPassComfirm.setNode(confirmCircle);
            fadeAnimationPassComfirm.play();
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
        pass.requestFocus();
        pass.selectAll();
    }

    @FXML
    void memberAction(ActionEvent event) {
        email.requestFocus();
        email.selectAll();
    }

    @FXML
    void passAction(ActionEvent event) {
        confirmPass.requestFocus();
        confirmPass.selectAll();
    }

    @FXML
    void confirmPassAction(ActionEvent event) {
        phone.requestFocus();
        phone.selectAll();
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
        Thread tPass = new Thread() {
            @Override
            public void run() {
                passCondition();
            }
        };
        Thread tPassComfirm = new Thread() {
            @Override
            public void run() {
                passConfirmCondition();
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
        tPass.start();
        try {
            tPass.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(TrainerSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tPassComfirm.start();
        try {
            tPassComfirm.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(TrainerSignUpController.class.getName()).log(Level.SEVERE, null, ex);
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
        Thread tDifferentPass = new Thread() {
            @Override
            public void run() {
                differentPassCondition();
            }
        };
        Thread tWeakPass = new Thread() {
            @Override
            public void run() {
                weakPassCondition();
            }
        };
        Thread tInvalidPass = new Thread() {
            @Override
            public void run() {
                invalidPassCondition();
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
        tDifferentPass.start();
        try {
            tDifferentPass.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(TrainerSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tWeakPass.start();
        try {
            tWeakPass.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(TrainerSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tInvalidPass.start();
        try {
            tInvalidPass.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(TrainerSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tInvalidPhone.start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        System.out.println(windowTitleWatcher.getText() + " window title");

        
        txtFieldSearchByName.textProperty().addListener((observable, oldValue, newValue) -> {
            txtFieldSearchById.clear();
            txtFieldSearchByName.setText(newValue);
            if (!newValue.isEmpty()) {

                ArrayList<admin> list = db.admin_table.searchbyname(newValue);

                adminTable.getItems().clear();
                adminTable.getItems().addAll(list);
                adminTable.refresh();

            } else {
                
                try {
                    ArrayList<admin> list = db.admin_table.showall();
                    adminTable.getItems().clear();
                    adminTable.getItems().addAll(list);
                    adminTable.refresh();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
        
        txtFieldSearchById.textProperty().addListener((observable, oldValue, newValue) -> {
            txtFieldSearchByName.clear();
            txtFieldSearchById.setText(newValue);
            if (!newValue.isEmpty()) {

                ArrayList<admin> list = db.admin_table.searchbyid(newValue);

                adminTable.getItems().clear();
                adminTable.getItems().addAll(list);
                adminTable.refresh();

            } else {
                
                try {
                    ArrayList<admin> list = db.admin_table.showall();
                    adminTable.getItems().clear();
                    adminTable.getItems().addAll(list);
                    adminTable.refresh();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));

        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

//        ArrayList<admin> a;
        try {
            ArrayList<admin> a = db.admin_table.showall();
            adminTable.getItems().addAll(a);
        } catch (SQLException ex) {
            Logger.getLogger(SeeAndUpdateAdminDataController.class.getName()).log(Level.SEVERE, null, ex);
        }

        updateTableButton.setOnAction((event) -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("AdminUpdateSaveScene.fxml"));
                Parent memberSignUpScene = loader.load();
                AdminUpdateSaveController memUpSaveControl = loader.getController();
                try {
                    memUpSaveControl.rawData(adminTable.getSelectionModel().getSelectedItem());
                    Scene scene = new Scene(memberSignUpScene, 1360, 700);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    if (adminTable.getSelectionModel().getSelectedItem().getEmail().equals(window.getTitle())) {
                        new Alert(Alert.AlertType.INFORMATION, "Your data have to be updated in this scene.", ButtonType.OK).showAndWait();
                    } else {
                        window.setResizable(false);
                        window.setScene(scene);
                        window.show();
                    }
                } catch (NullPointerException e) {
                    new Alert(Alert.AlertType.ERROR, "Please select a row", ButtonType.OK).showAndWait();
                }
            } catch (IOException ex) {
                Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        editableMode.selectedProperty().addListener((ob, ov, nv) -> {
            if (nv) {
                trainerName.setEditable(true);
                email.setEditable(true);
                pass.setEditable(true);
                confirmPass.setEditable(true);
                phone.setEditable(true);
            } else {
                trainerName.setEditable(false);
                email.setEditable(false);
                pass.setEditable(false);
                confirmPass.setEditable(false);
                phone.setEditable(false);
            }
        });

        MemberSignUpController memSignUpControll = new MemberSignUpController();
        HamburgerBasicCloseTransition burgerTask = new HamburgerBasicCloseTransition(adminMenu);
        burgerTask.setRate(-1);
        adminMenu.setOnMousePressed((event1) -> {
            try {
                adminDrawer.setSidePane(memSignUpControll.adminMenuScene(titleTrainerSignUp.getText()));
            } catch (IOException ex) {
                Logger.getLogger(SeeAndUpdateMemberDataController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        adminMenu.setOnMouseReleased((event1) -> {
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
