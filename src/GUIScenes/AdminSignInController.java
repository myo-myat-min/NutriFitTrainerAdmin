package GUIScenes;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import db.ConnectDB;
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
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AdminSignInController implements Initializable {
    
    private FadeTransition fadeAnimationName = new FadeTransition();
    private FadeTransition fadeAnimationPass = new FadeTransition();
    private FadeTransition fadeAnimationEmptyNoti = new FadeTransition();
    private FadeTransition fadeAnimationWrongName = new FadeTransition();
    private FadeTransition fadeAnimationWrongPass = new FadeTransition();

    @FXML
    private Rectangle welcomeTemplate;

    @FXML
    private Text txtWelcome;

    @FXML
    private Text txtNutriFit;

    @FXML
    private Rectangle titleTemplate;

    @FXML
    private Text titleTrainerSignUp;

    @FXML
    private JFXTextField txtFieldEmail;

    @FXML
    private JFXButton signInButton;

    @FXML
    protected Text txtForgetPass;

    @FXML
    private JFXPasswordField pass;

    @FXML
    private Circle nameCircle;

    @FXML
    private Circle passCircle;

    @FXML
    private Circle completeCircPass;

    @FXML
    private Circle completeCircName;

    @FXML
    private Text emptyNoti;
    
    @FXML
    private Text wrongName;

    @FXML
    private Text wrongPass;
    
    @FXML
    private SVGPath backArrowShape;

    @FXML
    void forgetPassMouseEnteredAction(MouseEvent event) {

    }

    @FXML
    void nameAction(ActionEvent event) {
        pass.requestFocus();
        pass.selectAll();
    }

    @FXML
    void signInPressedAction(MouseEvent event) {
        if (!txtFieldEmail.getText().isEmpty() && !pass.getText().isEmpty()) {
            if (completeCircName.getFill() == Color.GREEN && completeCircPass.getFill() == Color.GREEN) {
                completeCircName.setFill(Color.RED);
                completeCircPass.setFill(Color.RED);
            } else {
                wrongAnimation();
                wrongControllThreads();
            }
        } else {
            redCircleAnimation();
            emptyNotiMethod();
            emptyControllThreads();
        }
    }

    @FXML
    void signInReleasedAction(MouseEvent event) {
        if (!txtFieldEmail.getText().isEmpty() && !pass.getText().isEmpty()) {
            if (completeCircName.getFill() == Color.GREEN && completeCircPass.getFill() == Color.GREEN) {

                if (ConnectDB.CreateConnection() != null) {
                    String sql = "SELECT * FROM admin";
                    int countForAlert = 0;

                    try (Connection con = ConnectDB.CreateConnection();
                            PreparedStatement ps = con.prepareStatement(sql);) {

                        ResultSet rs = ps.executeQuery();

                        while (rs.next()) {
                            if (rs.getString("email").equals(txtFieldEmail.getText()) && rs.getString("password").equals(pass.getText())) {
                                if (rs.getString("status").equals("On Job")) {
                                    FXMLLoader loader = new FXMLLoader();
                                    loader.setLocation(getClass().getResource("SeeAndUpdateAdminDataScene.fxml"));
                                    Parent trainerMyAccountScene = loader.load();
                                    SeeAndUpdateAdminDataController controller = loader.getController();
                                    Scene scene = new Scene(trainerMyAccountScene);
                                    scene.getStylesheets().add("CSS/TableDesign.css");
                                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                    window.setResizable(false);
                                    window.setScene(scene);
                                    controller.addingData(rs.getString("id"), rs.getString("name"), rs.getString("password"), rs.getString("email"), rs.getString("phone"));
                                    window.setTitle(rs.getString("email"));
                                    window.show();
                                    countForAlert = 0;
                                    break;
                                } else {
                                    new Alert(Alert.AlertType.ERROR, "You are Off Job.", ButtonType.OK).showAndWait();
                                    countForAlert = 0;
                                    break;
                                }
                            } else {
                                countForAlert++;
                            }
                        }
                        if (countForAlert > 0) {
                            new Alert(Alert.AlertType.ERROR, "Please check your email or password again. Forgot password?", ButtonType.OK).showAndWait();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                completeCircName.setFill(Color.RED);
                completeCircPass.setFill(Color.RED);
            } else {
                wrongAnimation();
                wrongControllThreads();
            }
        } else {
            redCircleAnimation();
            emptyNotiMethod();
            emptyControllThreads();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        forgetPassHover();
        txtForgetPass.setOnMouseClicked((event) -> {
            if (ConnectDB.CreateConnection() != null) {
                String sql = "SELECT email, password FROM admin";
                int countBothWrong = 0;
                int countBothRight = 0;

                try (Connection con = ConnectDB.CreateConnection();
                        PreparedStatement ps = con.prepareStatement(sql);) {

                    ResultSet rs = ps.executeQuery();
                    TrainerSignInController trainerSignInControl = new TrainerSignInController();

                    while (rs.next()) {
                        if (rs.getString("email").equals(txtFieldEmail.getText()) && !rs.getString("password").equals(pass.getText())) {
//                            Decrypt password
                            trainerSignInControl.send("mstzsn01@gmail.com", "qazmlp123!", txtFieldEmail.getText(), "NutriFit - Password", rs.getString("password"));
                            new Alert(AlertType.INFORMATION, "Password is sent to your email").showAndWait();
                            countBothWrong = 0;
                            countBothRight = 0;
                            break;
                        } else if (rs.getString("email").equals(txtFieldEmail.getText()) && rs.getString("password").equals(pass.getText())) {
                            countBothRight++;
                            countBothWrong = 0;
                            break;
                        } else {
                            countBothWrong++;
                        }
                    }
                    if (countBothWrong > 0) {
                        new Alert(AlertType.ERROR, "Your email has not registered yet. Please check your email again.").showAndWait();
                    }
                    if (countBothRight > 0) {
                        new Alert(AlertType.INFORMATION, "Please click SignIn button").showAndWait();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
    }
    
    public void fadeAnimationItems(FadeTransition fadeAnimation) {
        fadeAnimation.setDuration(Duration.millis(4000));
        fadeAnimation.setCycleCount(1);
        fadeAnimation.setAutoReverse(false);
        fadeAnimation.setFromValue(1);
        fadeAnimation.setToValue(0);
    }
    
    public void redCircleAnimation() {
        fadeAnimationItems(fadeAnimationName);
        fadeAnimationItems(fadeAnimationPass);
        fadeAnimationItems(fadeAnimationEmptyNoti);
    }
    
    public void wrongAnimation() {
        fadeAnimationItems(fadeAnimationWrongName);
        fadeAnimationItems(fadeAnimationWrongPass);
    }
    
    public void nameCondition() {
        if (txtFieldEmail.getText().isEmpty()) {
            fadeAnimationName.setNode(nameCircle);
            fadeAnimationName.play();
        }
    }
    
    public void passCondition() {
        if (pass.getText().isEmpty()) {
            fadeAnimationPass.setNode(passCircle);
            fadeAnimationPass.play();
        }
    }
    
    public void emptyNotiMethod() {
        fadeAnimationEmptyNoti.setNode(emptyNoti);
        fadeAnimationEmptyNoti.play();
    }
    
    public void wrongEmailCondition() {
//        Plz insert wrong name SQL code in if condition
        if (!Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", txtFieldEmail.getText())) {
            completeCircName.setFill(Color.RED);
            fadeAnimationWrongName.setNode(wrongName);
            fadeAnimationWrongName.play();
        } else {
            completeCircName.setFill(Color.GREEN);
        }
    }
    
    public void wrongPassCondition() {
//        Plz insert wrong password SQL code in if condition
        if (!Pattern.matches("[\\w\\S]{8,20}", pass.getText())) {
            completeCircPass.setFill(Color.RED);
            fadeAnimationWrongPass.setNode(wrongPass);
            fadeAnimationWrongPass.play();
        } else {
            completeCircPass.setFill(Color.GREEN);
        }
    }

    public void emptyControllThreads() {
        Thread tEmail = new Thread() {
            @Override
            public void run() {
                nameCondition();
            }
        };
        Thread tPass = new Thread() {
            @Override
            public void run() {
                passCondition();
            }
        };
        tEmail.start();
        try {
            tEmail.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(AdminSignInController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tPass.start();
    }
    
    public void wrongControllThreads() {
        Thread tWrongEmail = new Thread() {
            @Override
            public void run() {
                wrongEmailCondition();
            }
        };
        Thread tWrongPass = new Thread() {
            @Override
            public void run() {
                wrongPassCondition();
            }
        };
        tWrongEmail.start();
        try {
            tWrongEmail.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(AdminSignInController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tWrongPass.start();
    }
    
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
            Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("TrainerAdminSignInScene.fxml"));
            Scene scene = new Scene(trainerSignUpScene);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setResizable(false);
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(AdminSignInController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void forgetPassHover(){
        Stop[] stop = {new Stop(0, Color.BLACK),  
                       new Stop(1, Color.web("#fb0"))}; 
  
        LinearGradient linear_gradient = new LinearGradient(0, 0, 
                          1, 0, true, CycleMethod.NO_CYCLE, stop);
        txtForgetPass.setOnMouseEntered((event) -> {
            txtForgetPass.setFill(Color.web("#000"));
        });
        txtForgetPass.setOnMouseExited((event) -> {
            txtForgetPass.setFill(linear_gradient);
        });
    }
}
