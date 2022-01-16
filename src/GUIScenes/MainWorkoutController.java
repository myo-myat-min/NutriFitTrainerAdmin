/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIScenes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author ACER
 */
public class MainWorkoutController implements Initializable {
    
    @FXML
    private Label label;

    @FXML
    private Rectangle titleTemplate;

    @FXML
    private Text titleRecordTrainerAtt;

    @FXML
    private JFXButton bmical;

    @FXML
    private JFXButton bmrcal;

    @FXML
    private JFXButton caloriecal;

    @FXML
    private JFXButton idealweightcal;

    @FXML
    private JFXDrawer adminDrawer;

    @FXML
    private JFXHamburger adminMenu;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TrainerMyAccountController trainerMyAccControl = new TrainerMyAccountController();
        HamburgerBasicCloseTransition burgerTask = new HamburgerBasicCloseTransition(adminMenu);
        burgerTask.setRate(-1);
        adminMenu.setOnMousePressed((event) -> {
            try {
                adminDrawer.setSidePane(trainerMyAccControl.adminMenuScene(titleRecordTrainerAtt.getText()));
            } catch (IOException ex) {
                Logger.getLogger(TrainerAttController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    void handleButtonAction(ActionEvent event) {
        try {
            Parent memberSignUpScene = FXMLLoader.load(getClass().getResource("LooseWeightWorkoutScene.fxml"));
            Scene scene = new Scene(memberSignUpScene);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setResizable(false);
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(MainWorkoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void handleButtonAction1(ActionEvent event) {
        try {
            Parent memberSignUpScene = FXMLLoader.load(getClass().getResource("AllWorkoutScene.fxml"));
            Scene scene = new Scene(memberSignUpScene);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setResizable(false);
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(MainWorkoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void handleButtonAction3(ActionEvent event) {
        try {
            Parent memberSignUpScene = FXMLLoader.load(getClass().getResource("GainWeightWorkoutScene.fxml"));
            Scene scene = new Scene(memberSignUpScene);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setResizable(false);
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(MainWorkoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void handleButtonAction4(ActionEvent event) {
        try {
            Parent memberSignUpScene = FXMLLoader.load(getClass().getResource("GetFitterWorkoutScene.fxml"));
            Scene scene = new Scene(memberSignUpScene);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setResizable(false);
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(MainWorkoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
