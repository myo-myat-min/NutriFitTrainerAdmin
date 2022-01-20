/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIScenes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import db.ConnectDB;
import db.Membership;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
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
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.Printer.MarginType;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author ACER
 */
public class InvoiceController implements Initializable {

    private FadeTransition fadeAnimationMember = new FadeTransition();
    private FadeTransition fadeAnimationInvalidName = new FadeTransition();
    private FadeTransition fadeAnimationEmptyNoti = new FadeTransition();

    @FXML
    private AnchorPane combobox;

    @FXML
    private Label label;

    @FXML
    private Text txtWelcome;

    @FXML
    private Text txtNutriFit;

    @FXML
    private JFXHamburger adminMenu;

    @FXML
    private JFXDrawer adminDrawer;

    @FXML
    private Rectangle titleTemplate;

    @FXML
    private Text titleInvoice;

    @FXML
    private Text dateInvoice;

    @FXML
    private JFXTextField memberName;

    @FXML
    private JFXComboBox<String> packageChoice;

    @FXML
    private Text packageFees;

    @FXML
    private Text admin;

    @FXML
    private Text gymPhone;

    @FXML
    private Text gymAddress;

    @FXML
    private JFXButton printButton;

    @FXML
    private Circle completeCircName;

    @FXML
    void adminMenuMousePressedAction(MouseEvent event) {

    }

//    @FXML
//    void recordPressedAction(MouseEvent event) {
//        if (!memberName.getText().isEmpty()) {
//            if (completeCircName.getFill() == Color.RED) {
//                invalidAnimation();
//                invalidControllThreads();
//            }
//        } else {
//            redCircleAnimation();
//            emptyNotiMethod();
//            emptyControllThreads();
//        }
//    }


//    @FXML
//    void printPressedAction(MouseEvent event) {
//        if (!memberName.getText().isEmpty()) {
//            if (completeCircName.getFill() == Color.RED) {
//                invalidAnimation();
//                invalidControllThreads();
//            }
//        } else {
//            redCircleAnimation();
//            emptyNotiMethod();
//            emptyControllThreads();
//        }
//    }
    @FXML
    void printReleasedAction(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to print the data?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            try {
                Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("InvoicePrintScene.fxml"));
                Scene scene = new Scene(trainerSignUpScene);
                Stage window = new Stage();
                window.setResizable(false);
                window.centerOnScreen();
                window.setScene(scene);
                window.show();
            } catch (IOException ex) {
                Logger.getLogger(InvoicePrintController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            event.consume();
        }

    }
//
//    public void redCircleAnimation() {
//        fadeAnimationItems(fadeAnimationMember);
//        fadeAnimationItems(fadeAnimationEmptyNoti);
//    }
//
//    public void invalidAnimation() {
//        fadeAnimationItems(fadeAnimationInvalidName);
//    }
//
//    public void fadeAnimationItems(FadeTransition fadeAnimation) {
//        fadeAnimation.setDuration(Duration.millis(4000));
//        fadeAnimation.setCycleCount(1);
//        fadeAnimation.setAutoReverse(false);
//        fadeAnimation.setFromValue(1);
//        fadeAnimation.setToValue(0);
//    }

//    public void invalidNameCondition() {
//        if (!Pattern.matches("[a-zA-Z\\s]+", memberName.getText())) {
//            completeCircName.setFill(Color.RED);
//            fadeAnimationInvalidName.setNode(invalidName);
//            fadeAnimationInvalidName.play();
//        } else {
//            completeCircName.setFill(Color.GREEN);
//        }
//    }
//
//    public void memberCondition() {
//        if (memberName.getText().isEmpty()) {
//            fadeAnimationMember.setNode(nameCircle);
//            fadeAnimationMember.play();
//        }
//    }
//
//    public void emptyNotiMethod() {
//        fadeAnimationEmptyNoti.setNode(emptyNoti);
//        fadeAnimationEmptyNoti.play();
//    }

//    public void emptyControllThreads() {
//        Thread tMember = new Thread() {
//            @Override
//            public void run() {
//                memberCondition();
//            }
//        };
//        tMember.start();
//    }
//
//    public void invalidControllThreads() {
//        Thread tInvalidName = new Thread() {
//            @Override
//            public void run() {
//                invalidNameCondition();
//            }
//        };
//        tInvalidName.start();
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        LocalDateTime now = LocalDateTime.now();
        dateInvoice.setText(dtf.format(now));
        
        String[] s = db.invoice_table.show_lastrow();

        memberName.setText(s[1]);
        System.out.println(s[1]);
        packageChoice.getItems().add(s[0]);
        packageChoice.getSelectionModel().select(0);

        if (ConnectDB.CreateConnection() != null) {
            String sql = String.format("SELECT price FROM membership WHERE membership_name = '%s'", packageChoice.getValue());

            try (Connection con = ConnectDB.CreateConnection();
                    PreparedStatement ps = con.prepareStatement(sql);) {

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    packageFees.setText("Fees   : " + String.valueOf(rs.getInt("price")));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        MemberSignUpController memSignUpControll = new MemberSignUpController();
        HamburgerBasicCloseTransition burgerTask = new HamburgerBasicCloseTransition(adminMenu);
        burgerTask.setRate(-1);
        adminMenu.setOnMousePressed((event) -> {
            try {
                adminDrawer.setSidePane(memSignUpControll.adminMenuScene(titleInvoice.getText()));
            } catch (IOException ex) {
                Logger.getLogger(MonthlyReportController.class.getName()).log(Level.SEVERE, null, ex);
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
