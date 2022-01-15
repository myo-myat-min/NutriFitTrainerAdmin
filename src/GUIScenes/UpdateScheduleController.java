package GUIScenes;

import db.schedule_table;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import db.ConnectDB;
import db.admin;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import db.schedule;

public class UpdateScheduleController implements Initializable {
    
    private FadeTransition fadeAnimationContent = new FadeTransition();
    private FadeTransition fadeAnimationDate = new FadeTransition();
    private FadeTransition fadeAnimationEmptyNoti = new FadeTransition();

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
    private JFXButton updateButton;

    @FXML
    private Text emptyNoti;

    @FXML
    private JFXTextArea txtAreaContent;

    @FXML
    private Circle contentCircle;

    @FXML
    private JFXDatePicker dateSchedule;

    @FXML
    private Circle dateCircle;

    private schedule selected;
    
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
            Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("ViewScheduleScene.fxml"));
            Scene scene = new Scene(trainerSignUpScene, 1360, 700);
            scene.getStylesheets().add("CSS/TableDesign.css");
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setResizable(false);
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(AdminSignInController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void rawData(schedule schedule) {
        selected = schedule;
        dateSchedule.setValue(selected.getSchedule_date());
        txtAreaContent.setText(selected.getContent());
    }
    
    @FXML
    void updateReleasedAction(MouseEvent event) {
        if (!txtAreaContent.getText().isEmpty() && dateSchedule.getValue() != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to update the data?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                try {
                       db.schedule_table.update_sche(selected.getSche_id(),dateSchedule.getValue(),txtAreaContent.getText());
                
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, e.getLocalizedMessage(), ButtonType.OK).showAndWait();
                }
           
                new Alert(Alert.AlertType.INFORMATION, "Changed data are updated.", ButtonType.OK).showAndWait();
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
            } else {
                event.consume();
            }

//            try {
//                Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("UpdateScheduleScene.fxml"));
//                Scene scene = new Scene(trainerSignUpScene, 1360, 700);
//                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//
//                if (ConnectDB.CreateConnection() != null) {
//                    String sql = String.format("SELECT id FROM trainer WHERE email = '%s'", window.getTitle());
//
//                    try (Connection con = ConnectDB.CreateConnection();
//                            PreparedStatement ps = con.prepareStatement(sql);) {
//
//                        ResultSet rs = ps.executeQuery();
//
//                        while (rs.next()) {
//                            System.err.println(rs.getString("id"));
//                            schedule sch = new schedule(dateSchedule.getValue(), txtAreaContent.getText(), rs.getString("id"));
//                            System.out.println(sch);
////                                schedule_table.insert_schedule(sch);
//                        }
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            } catch (IOException ex) {
//                Logger.getLogger(SetScheduleController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            new Alert(Alert.AlertType.INFORMATION, "Schedule is recorded", ButtonType.OK).showAndWait();
//            try {
//                Parent memberSignUpScene = FXMLLoader.load(getClass().getResource("SetScheduleScene.fxml"));
//                Scene scene = new Scene(memberSignUpScene, 1360, 700);
//                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                window.setResizable(false);
//                window.setScene(scene);
//                window.show();
//            } catch (IOException ex) {
//                Logger.getLogger(SetScheduleController.class.getName()).log(Level.SEVERE, null, ex);
//            }
        } else {
            redCircleAnimation();
            emptyNotiMethod();
            emptyControllThreads();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        TrainerMyAccountController trainerMyAccControl = new TrainerMyAccountController();
//        HamburgerBasicCloseTransition burgerTask = new HamburgerBasicCloseTransition(adminMenu);
//        burgerTask.setRate(-1);
//        adminMenu.setOnMousePressed((event) -> {
//            try {
//                adminDrawer.setSidePane(trainerMyAccControl.adminMenuScene(titleTrainerSignUp.getText()));
//            } catch (IOException ex) {
//                Logger.getLogger(TrainerAttController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        });
//        adminMenu.setOnMouseReleased((event) -> {
//            burgerTask.setRate(burgerTask.getRate() * -1);
//            burgerTask.play();
//
//            if (adminDrawer.isShown()) {
//                adminDrawer.close();
//            } else {
//                adminDrawer.open();
//            }
//        });
    }
    
    public void fadeAnimationItems(FadeTransition fadeAnimation) {
        fadeAnimation.setDuration(Duration.millis(4000));
        fadeAnimation.setCycleCount(1);
        fadeAnimation.setAutoReverse(false);
        fadeAnimation.setFromValue(1);
        fadeAnimation.setToValue(0);
    }
    
    public void redCircleAnimation() {
        fadeAnimationItems(fadeAnimationContent);
        fadeAnimationItems(fadeAnimationEmptyNoti);
        fadeAnimationItems(fadeAnimationDate);
    }
      
    public void contentCondition() {
        if (txtAreaContent.getText().isEmpty()) {
            fadeAnimationContent.setNode(contentCircle);
            fadeAnimationContent.play();
        }
    }
    
    public void dateCondition() {
        if (dateSchedule.getValue() == null) {
            fadeAnimationDate.setNode(dateCircle);
            fadeAnimationDate.play();
        }
    }
    
    public void emptyNotiMethod() {
        fadeAnimationEmptyNoti.setNode(emptyNoti);
        fadeAnimationEmptyNoti.play();
    }
    
    public void emptyControllThreads() {
        Thread tContent = new Thread() {
            @Override
            public void run() {
                contentCondition();
            }
        };
        Thread tDate = new Thread() {
            @Override
            public void run() {
                dateCondition();
            }
        };
        tContent.start();
        try {
            tContent.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(SetScheduleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tDate.start();
    }

}
