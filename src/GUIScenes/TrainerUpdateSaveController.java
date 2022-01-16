/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIScenes;

import db.trainer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
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
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author ACER
 */
public class TrainerUpdateSaveController implements Initializable{
    
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
    
    private trainer selected;
    
    @FXML
    private Circle nameCircle;

    @FXML
    private Circle emailCircle;

    @FXML
    private Circle nrcCircle;

    @FXML
    private Circle dobCircle;
    
    @FXML
    private Circle feesCircle;

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
    private Circle completeCircNRC;

    @FXML
    private Circle completeCircDOB;
    
    @FXML
    private Circle completeCircFees;

    @FXML
    private Rectangle titleTemplate;

    @FXML
    protected JFXTextField memberName;

    @FXML
    protected JFXTextField nrc;

    @FXML
    protected JFXTextField phone;
    
    @FXML
    protected JFXTextField fees;

    @FXML
    protected JFXRadioButton male;

    @FXML
    protected ToggleGroup gender;

    @FXML
    protected JFXRadioButton female;

    @FXML
    protected JFXDatePicker dob;

    @FXML
    private Text titleUpdateMemberData;

    @FXML
    private JFXButton saveButton;

    @FXML
    private Text emptyNoti;

    @FXML
    private Circle phoneCircle;

    @FXML
    protected JFXTextField email;

    @FXML
    private Circle addressCircle;

    @FXML
    protected JFXTextArea address;

    @FXML
    private Text invalidName;

    @FXML
    private Text invalidEmail;

    @FXML
    private Text invalidPhone;

    @FXML
    private Text invalidNrc;

    @FXML
    private Text invalidDOB;
    
    @FXML
    private Text invalidFees;
    
    @FXML
    private JFXRadioButton onJob;

    @FXML
    private ToggleGroup statusJob;

    @FXML
    private JFXRadioButton offJob;
    
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
        try {
            Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("SeeAndUpdateTrainerDataScene.fxml"));
            Scene scene = new Scene(trainerSignUpScene);
            scene.getStylesheets().add("CSS/TableDesign.css");
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setResizable(false);
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(AdminSignInController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void rawData(trainer trainer) {
        selected = trainer;
        memberName.setText(selected.getName());
        email.setText(selected.getEmail());
        nrc.setText(selected.getNrc());
        phone.setText(selected.getPh());
        fees.setText(String.valueOf(selected.getFees()));
        address.setText(selected.getAddress());
        dob.setValue(selected.getDob());
        if (selected.getGender().equals("Male")) {
            male.setSelected(true);
        } else {
            female.setSelected(true);
        }
        if (selected.isStatus().equals("On Job")) {
            onJob.setSelected(true);
        } else {
            offJob.setSelected(true);
        }
    }

    @FXML
    void savePressedAction(MouseEvent event) {
        //        if (!memberName.getText().isEmpty() && !email.getText().isEmpty() && !nrc.getText().isEmpty() && !phone.getText().isEmpty()
//                && !address.getText().isEmpty() && dob.getValue() != null && !txtFieldFees.getText().isEmpty()) {
        if (!memberName.getText().isEmpty() && !email.getText().isEmpty() && !nrc.getText().isEmpty() && !phone.getText().isEmpty() && !fees.getText().isEmpty()
                && !address.getText().isEmpty() && dob.getValue() != null) {
//            if (completeCircName.getFill() == Color.RED || completeCircEmail.getFill() == Color.RED
//                    || completeCircDOB.getFill() == Color.RED || completeCircFees.getFill() == Color.RED) {
            if (completeCircName.getFill() == Color.RED || completeCircEmail.getFill() == Color.RED || completeCircPhone.getFill() == Color.RED
                   || completeCircNRC.getFill() == Color.RED || completeCircFees.getFill() == Color.RED || completeCircDOB.getFill() == Color.RED) {
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
    void saveReleasedAction(MouseEvent event) {
        //        if (!name.getText().isEmpty() && !email.getText().isEmpty() && !nrc.getText().isEmpty() && !phone.getText().isEmpty()
//                && !address.getText().isEmpty() && dob.getValue() != null && !txtFieldFees.getText().isEmpty()) {
        if (!memberName.getText().isEmpty() && !email.getText().isEmpty() && !nrc.getText().isEmpty() && !phone.getText().isEmpty() && !fees.getText().isEmpty()
                && !address.getText().isEmpty() && dob.getValue() != null) {
//            if (completeCircName.getFill() == Color.GREEN && completeCircEmail.getFill() == Color.GREEN
//                    && completeCircPhone.getFill() == Color.GREEN && completeCircNRC.getFill() == Color.GREEN
//                    && completeCircDOB.getFill() == Color.GREEN && completeCircFees.getFill() == Color.GREEN) {
            if (completeCircName.getFill() == Color.GREEN && completeCircEmail.getFill() == Color.GREEN
                    && completeCircPhone.getFill() == Color.GREEN && completeCircNRC.getFill() == Color.GREEN
                    && completeCircDOB.getFill() == Color.GREEN && completeCircFees.getFill() == Color.GREEN) {

                String maleOrFemale;
                String status;

                if (male.isSelected()) {
                    maleOrFemale = "Male";
                } else {
                    maleOrFemale = "Female";
                }

                if (onJob.isSelected()) {
                    status = "On Job";
                } else {
                    status = "Off Job";
                }
                try {
                    trainer t = new trainer(selected.getId(), memberName.getText(), selected.getPassword(), dob.getValue(), maleOrFemale, email.getText(), nrc.getText(),
                            phone.getText(), address.getText(), status, Integer.parseInt(fees.getText()));
                    db.trainer_table.updatetrainer(t);
                    Alert alertRecorded = new Alert(Alert.AlertType.INFORMATION, "Changed data are recorded", ButtonType.OK);
                    alertRecorded.showAndWait();
                    if (alertRecorded.getResult() == ButtonType.OK) {
                        try {
                            Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("SeeAndUpdateTrainerDataScene.fxml"));
                            Scene scene = new Scene(trainerSignUpScene);
                            scene.getStylesheets().add("CSS/TableDesign.css");
                            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            window.setResizable(false);
                            window.setScene(scene);
                            window.show();
                        } catch (IOException ex) {
                            Logger.getLogger(MemberUpdateSaveController.class.getName()).log(Level.SEVERE, null, ex);
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
//        SeeAndUpdateMemberDataController seeUp = new SeeAndUpdateMemberDataController();
//        
//        seeUp.memberSeeUpdateTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
//                    @Override
//                    public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
//                        //Check whether item is selected and set value of selected item to Label
//                        if (seeUp.memberSeeUpdateTable.getSelectionModel().getSelectedItem() != null) {
//
//                            TableView.TableViewSelectionModel selectionModel = seeUp.memberSeeUpdateTable.getSelectionModel();
//                            ObservableList selectedCells = selectionModel.getSelectedCells();
//                            TablePosition tablePosition = (TablePosition) selectedCells.get(0);
//                            Object val = tablePosition.getTableColumn().getCellData(newValue);
//                            System.out.println("Selected Value " + val);
//                            memberName.setText(val.toString());
//                        }
//                    }
//                });
        
    }
    
}
