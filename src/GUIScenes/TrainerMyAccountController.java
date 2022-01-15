package GUIScenes;

//import database.Member;
//import database.Membertable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
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
import db.trainer;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

public class TrainerMyAccountController extends Stage implements Initializable {

    private FadeTransition fadeAnimationMember = new FadeTransition();
    private FadeTransition fadeAnimationEmail = new FadeTransition();
    private FadeTransition fadeAnimationPass = new FadeTransition();
    private FadeTransition fadeAnimationPassComfirm = new FadeTransition();
    private FadeTransition fadeAnimationNrc = new FadeTransition();
    private FadeTransition fadeAnimationPhone = new FadeTransition();
    private FadeTransition fadeAnimationAddress = new FadeTransition();
    private FadeTransition fadeAnimationDOB = new FadeTransition();
    private FadeTransition fadeAnimationEmptyNoti = new FadeTransition();
    private FadeTransition fadeAnimationPassWrongNoti = new FadeTransition();
    private FadeTransition fadeAnimationInvalidName = new FadeTransition();
    private FadeTransition fadeAnimationInvalidEmail = new FadeTransition();
    private FadeTransition fadeAnimationInvalidNRC = new FadeTransition();
    private FadeTransition fadeAnimationInvalidPhone = new FadeTransition();
    private FadeTransition fadeAnimationWeakPassNoti = new FadeTransition();
    private FadeTransition fadeAnimationInvalidPassNoti = new FadeTransition();
    private FadeTransition fadeAnimationInvalidDOB = new FadeTransition();

    JFXButton myAccountButton = new JFXButton("My Account");
    JFXButton setWorkoutButton = new JFXButton("Set Workout");
    JFXButton viewWorkoutButton = new JFXButton("View Workout");
    JFXButton setScheduleButton = new JFXButton("Set Schedule");
    JFXButton viewScheduleButton = new JFXButton("View Schedule");
    JFXButton logOutButton = new JFXButton("Log Out");

    @FXML
    protected Circle nameCircle;

    @FXML
    protected Circle emailCircle;

    @FXML
    protected Circle passCircle;

    @FXML
    protected Circle confirmCircle;

    @FXML
    protected Circle nrcCircle;

    @FXML
    protected Circle dobCircle;

    @FXML
    protected Rectangle welcomeTemplate;

    @FXML
    protected Text txtWelcome;

    @FXML
    protected Text txtNutriFit;

    @FXML
    protected Rectangle titleTemplate;

    @FXML
    protected JFXTextField trainerName;

    @FXML
    protected JFXPasswordField pass;

    @FXML
    protected JFXPasswordField confirmPass;

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
    protected Text titleTrainerSignUp;

    @FXML
    protected JFXRadioButton firstPackage;

    @FXML
    protected ToggleGroup pack;

    @FXML
    protected JFXRadioButton secondPackage;

    @FXML
    protected JFXRadioButton thirdPackage;

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
    protected Text passWrongNoti;

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
    private Circle completeCircPass;

    @FXML
    private Circle completeCircPhone;

    @FXML
    private Circle completeCircNRC;

    @FXML
    private Text tooWeakPass;

    @FXML
    private Circle completeCircWeakPass;

    @FXML
    private Text invalidPass;

    @FXML
    private Circle completeCircInvalidPass;

    @FXML
    protected JFXDrawer adminDrawer;

    @FXML
    private Text invalidDOB;

    @FXML
    private Circle completeCircDOB;

    @FXML
    private JFXCheckBox editableMode;

    public void addingData(String name, String email, String pass, String nrc, String phone, LocalDate dob, String gender, String address) {
        trainerName.setText(name);
        this.email.setText(email);
        this.pass.setText(pass);
        confirmPass.setText(pass);
        this.nrc.setText(nrc);
        this.phone.setText(phone);
        this.dob.setValue(dob);
        if (gender.equals("Male")) {
            male.setSelected(true);
        } else {
            female.setSelected(true);
        }
        this.address.setText(address);
    }

    @FXML
    void signUpPressedAction(MouseEvent event) {
        if (!trainerName.getText().isEmpty() && !email.getText().isEmpty() && !pass.getText().isEmpty()
                && !confirmPass.getText().isEmpty() && !nrc.getText().isEmpty() && !phone.getText().isEmpty()
                && !address.getText().isEmpty() && dob.getValue() != null) {
            if (completeCircName.getFill() == Color.RED || completeCircEmail.getFill() == Color.RED || completeCircPass.getFill() == Color.RED
                    || completeCircPhone.getFill() == Color.RED || completeCircNRC.getFill() == Color.RED
                    || completeCircInvalidPass.getFill() == Color.RED || completeCircWeakPass.getFill() == Color.RED
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

    @FXML
    void signUpReleasedAction(MouseEvent event) {
        if (!trainerName.getText().isEmpty() && !email.getText().isEmpty() && !pass.getText().isEmpty()
                && !confirmPass.getText().isEmpty() && !nrc.getText().isEmpty() && !phone.getText().isEmpty()
                && !address.getText().isEmpty() && dob.getValue() != null) {
            if (completeCircName.getFill() == Color.GREEN && completeCircEmail.getFill() == Color.GREEN && completeCircPass.getFill() == Color.GREEN
                    && completeCircPhone.getFill() == Color.GREEN && completeCircNRC.getFill() == Color.GREEN
                    && completeCircInvalidPass.getFill() == Color.GREEN && completeCircWeakPass.getFill() == Color.GREEN
                    && completeCircDOB.getFill() == Color.GREEN) {

                String maleOrFemale;
                if (male.isSelected()) {
                    maleOrFemale = "Male";
                } else {
                    maleOrFemale = "Female";
                }
                try {
                    Stage a = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    String trainer_id = db.trainer_table.GetTrainerId_ByEmail(a.getTitle());

                    db.trainer_table.updatetrainer_ByTrainer(trainer_id, trainerName.getText(), pass.getText(), dob.getValue(), maleOrFemale, email.getText(), nrc.getText(), phone.getText(), address.getText());

                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Trainer data are recorded", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        try {
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("TrainerMyAccountScene.fxml"));
                            Parent trainerMyAccountScene = loader.load();
                            TrainerMyAccountController controller = loader.getController();
                            Scene scene = new Scene(trainerMyAccountScene, 1360, 700);
                            controller.addingData(trainerName.getText(), email.getText(), pass.getText(), nrc.getText(), phone.getText(), dob.getValue(), maleOrFemale, address.getText());
                            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            window.setResizable(false);
                            window.setScene(scene);

                            window.show();
                        } catch (IOException ex) {
                            Logger.getLogger(TrainerSignUpController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, "Duplicate NRC or email is not allowed", ButtonType.OK).showAndWait();
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
        fadeAnimationItems(fadeAnimationNrc);
        fadeAnimationItems(fadeAnimationPhone);
        fadeAnimationItems(fadeAnimationAddress);
        fadeAnimationItems(fadeAnimationDOB);
        fadeAnimationItems(fadeAnimationEmptyNoti);
    }

    public void invalidAnimation() {
        fadeAnimationItems(fadeAnimationInvalidName);
        fadeAnimationItems(fadeAnimationInvalidEmail);
        fadeAnimationItems(fadeAnimationInvalidPhone);
        fadeAnimationItems(fadeAnimationInvalidNRC);
        fadeAnimationItems(fadeAnimationInvalidDOB);
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

    public void emptyNotiMethod() {
        fadeAnimationEmptyNoti.setNode(emptyNoti);
        fadeAnimationEmptyNoti.play();
    }

    @FXML
    void confirmAction(ActionEvent event) {
        nrc.requestFocus();
        nrc.selectAll();
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
    void nrcAction(ActionEvent event) {
        phone.requestFocus();
        phone.selectAll();
    }

    @FXML
    void passAction(ActionEvent event) {
        confirmPass.requestFocus();
        confirmPass.selectAll();
    }

    @FXML
    void phoneAction(ActionEvent event) {
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
        tInvalidDOB.start();
        try {
            tInvalidDOB.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(TrainerSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dob.setOpacity(1);
        dob.getEditor().setOpacity(1);

        male.setOpacity(1);
        female.setOpacity(1);

        HamburgerBasicCloseTransition burgerTask = new HamburgerBasicCloseTransition(adminMenu);
        burgerTask.setRate(-1);
        adminMenu.setOnMousePressed((event) -> {
            try {
                adminDrawer.setSidePane(adminMenuScene(titleTrainerSignUp.getText()));
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

        editableMode.selectedProperty().addListener((ob, ov, nv) -> {
            if (nv) {
                trainerName.setEditable(true);
                email.setEditable(true);
                pass.setEditable(true);
                confirmPass.setEditable(true);
                nrc.setEditable(true);
                phone.setEditable(true);
                dob.setDisable(false);
                male.setDisable(false);
                female.setDisable(false);
                address.setEditable(true);
            } else {
                trainerName.setEditable(false);
                email.setEditable(false);
                pass.setEditable(false);
                confirmPass.setEditable(false);
                nrc.setEditable(false);
                phone.setEditable(false);
                dob.setDisable(true);
                male.setDisable(true);
                female.setDisable(true);
                address.setEditable(false);
            }
        });

    }

    public AnchorPane adminMenuScene(String titleText) throws IOException {
        JFXButton[] buttons = {myAccountButton, setWorkoutButton, viewWorkoutButton, setScheduleButton, viewScheduleButton, logOutButton};
        for (JFXButton button : buttons) {
            button.setPrefWidth(180);
            button.setPrefHeight(44);

            button.setRipplerFill(Color.web("#35ae05"));
            button.setTextFill(Color.web("#000"));
            button.setStyle("-fx-background-color : white");
            button.setFont(Font.font("Calibri", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 15));
            button.setTextAlignment(TextAlignment.LEFT);
            button.setButtonType(JFXButton.ButtonType.RAISED);
            button.setOnMouseEntered((event) -> {
                button.setStyle("-fx-background-color : #c4ffc4");
            });
            button.setOnMouseExited((event) -> {
                button.setStyle("-fx-background-color : white");
            });
        }
        
        switch (titleText) {
            case "My Account":
                myAccountButton.setStyle("-fx-background-color : #ffbb00");
                myAccountButton.setTextFill(Color.WHITE);
                myAccountButton.setOnMouseExited((event) -> {
                    myAccountButton.setStyle("-fx-background-color : #ffbb00");
                    myAccountButton.setTextFill(Color.WHITE);
                }); break;
            case "Set Workout":
                setWorkoutButton.setStyle("-fx-background-color : #ffbb00");
                setWorkoutButton.setTextFill(Color.WHITE);
                setWorkoutButton.setOnMouseExited((event) -> {
                    setWorkoutButton.setStyle("-fx-background-color : #ffbb00");
                    setWorkoutButton.setTextFill(Color.WHITE);
                }); break;
            case "View Workout":
                viewWorkoutButton.setStyle("-fx-background-color : #ffbb00");
                viewWorkoutButton.setTextFill(Color.WHITE);
                viewWorkoutButton.setOnMouseExited((event) -> {
                    viewWorkoutButton.setStyle("-fx-background-color : #ffbb00");
                    viewWorkoutButton.setTextFill(Color.WHITE);
                }); break;
            case "Set Schedule":
                setScheduleButton.setStyle("-fx-background-color : #ffbb00");
                setScheduleButton.setTextFill(Color.WHITE);
                setScheduleButton.setOnMouseExited((event) -> {
                    setScheduleButton.setStyle("-fx-background-color : #ffbb00");
                    setScheduleButton.setTextFill(Color.WHITE);
                }); break;
            case "View Schedule":
                viewScheduleButton.setStyle("-fx-background-color : #ffbb00");
                viewScheduleButton.setTextFill(Color.WHITE);
                viewScheduleButton.setOnMouseExited((event) -> {
                    viewScheduleButton.setStyle("-fx-background-color : #ffbb00");
                    viewScheduleButton.setTextFill(Color.WHITE);
                }); break;
            default:
                break;
        }
        
        myAccountButton.setLayoutX(0);
        myAccountButton.setLayoutY(0);

        setWorkoutButton.setLayoutX(0);
        setWorkoutButton.setLayoutY(52);

        viewWorkoutButton.setLayoutX(0);
        viewWorkoutButton.setLayoutY(104);

        setScheduleButton.setLayoutX(0);
        setScheduleButton.setLayoutY(156);

        viewScheduleButton.setLayoutX(0);
        viewScheduleButton.setLayoutY(208);

        logOutButton.setLayoutX(0);
        logOutButton.setLayoutY(260);

        AnchorPane adminMenuScene = new AnchorPane();
        adminMenuScene.setPrefWidth(180);
        adminMenuScene.setPrefHeight(0);
        adminMenuScene.setStyle("-fx-background-color : white");
        adminMenuScene.getChildren().addAll(myAccountButton, setWorkoutButton, viewWorkoutButton, setScheduleButton, viewScheduleButton, logOutButton);

        myAccountButton.setOnAction((event) -> {
            if (ConnectDB.CreateConnection() != null) {
                String sql = "SELECT * FROM trainer";

                try (Connection con = ConnectDB.CreateConnection();
                        PreparedStatement ps = con.prepareStatement(sql);) {

                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {

                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("TrainerMyAccountScene.fxml"));
                        Parent trainerMyAccountScene = loader.load();
                        TrainerMyAccountController controller = loader.getController();
                        Scene scene = new Scene(trainerMyAccountScene, 1360, 700);
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        if (rs.getString("email").equals(window.getTitle())) {
                            controller.addingData(rs.getString("name"), rs.getString("email"), rs.getString("password"), rs.getString("nrc"), rs.getString("phone_no"),
                                    rs.getDate("dob").toLocalDate(), rs.getString("gender"), rs.getString("address"));
                            window.setResizable(false);
                            window.setScene(scene);
                            window.show();
                        }

                    }

                } catch (Exception e) {

                }
            }
        });

        setWorkoutButton.setOnAction((event) -> {
            try {
                Parent memberSignUpScene = FXMLLoader.load(getClass().getResource("SetWorkoutScene.fxml"));
                Scene scene = new Scene(memberSignUpScene, 1360, 700);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setResizable(false);
                window.setScene(scene);
                window.show();
            } catch (IOException ex) {
                Logger.getLogger(TrainerMyAccountController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        viewWorkoutButton.setOnAction((event) -> {
            try {
                Parent memberSignUpScene = FXMLLoader.load(getClass().getResource("MainWorkoutScene.fxml"));
                Scene scene = new Scene(memberSignUpScene, 1360, 700);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setResizable(false);
                window.setScene(scene);
                window.show();
            } catch (IOException ex) {
                Logger.getLogger(TrainerMyAccountController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        setScheduleButton.setOnAction((event) -> {
            try {
                Parent memberSignUpScene = FXMLLoader.load(getClass().getResource("SetScheduleScene.fxml"));
                Scene scene = new Scene(memberSignUpScene, 1360, 700);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setResizable(false);
                window.setScene(scene);
                window.show();
            } catch (IOException ex) {
                Logger.getLogger(TrainerMyAccountController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        viewScheduleButton.setOnAction((event) -> {
            if (ConnectDB.CreateConnection() != null) {
                String sql = "SELECT * FROM trainer";

                try (Connection con = ConnectDB.CreateConnection();
                        PreparedStatement ps = con.prepareStatement(sql);) {

                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {

                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("ViewScheduleScene.fxml"));
                        Parent trainerMyAccountScene = loader.load();
                        ViewScheduleController controller = loader.getController();
                        Scene scene = new Scene(trainerMyAccountScene, 1360, 700);
                        scene.getStylesheets().add("CSS/TableDesign.css");
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        if (rs.getString("email").equals(window.getTitle())) {
                            controller.addingData(rs.getString("id"));
                            window.setResizable(false);
                            window.setScene(scene);
                            window.show();
                        }

                    }

                } catch (Exception e) {

                }
            }

        });

        logOutButton.setOnAction((event) -> {
            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to log out?", ButtonType.YES, ButtonType.NO);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    Parent memberSignUpScene = FXMLLoader.load(getClass().getResource("TrainerAdminSignInScene.fxml"));
                    Scene scene = new Scene(memberSignUpScene, 1360, 700);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setTitle("NutriFit");
                    window.setResizable(false);
                    window.setScene(scene);
                    window.show();
                } else {
                    event.consume();
                }

            } catch (IOException ex) {
                Logger.getLogger(TrainerMyAccountController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        return adminMenuScene;
    }

}
