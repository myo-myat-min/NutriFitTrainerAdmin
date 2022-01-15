/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIScenes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import db.schedule;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author ACER
 */
public class ViewScheduleController implements Initializable {

//    @FXML
//    private TableView<db.trainer> memberSeeUpdateTable;
    @FXML
    private TableView<schedule> memberSeeUpdateTable;

    @FXML
    private TableColumn<schedule, ?> id;

    @FXML
    private TableColumn<schedule, ?> content;

    @FXML
    private TableColumn<schedule, ?> date;

    @FXML
    private TableColumn<schedule, String> trainerid;

    @FXML
    private TableColumn<schedule, ?> trainername;

    @FXML
    private JFXDrawer adminDrawer;

    @FXML
    private JFXHamburger adminMenu;

    @FXML
    private Rectangle titleTemplate;

    @FXML
    private Text titleRecordTrainerAtt;

    @FXML
    private JFXButton deleteButton;

    @FXML
    private JFXTextField txtFieldSearchByTrainerName;

    @FXML
    private JFXButton updateButton;

    @FXML
    private Circle dateCircle;

    @FXML
    private JFXDatePicker dateForData;

    @FXML
    private Text emptyNoti;

    @FXML
    private Text idWatcher;

    public void addingData(String id) {
        idWatcher.setText(id);
    }

    @FXML
    void deletePressedAction(ActionEvent event) {
        try {
            schedule s = memberSeeUpdateTable.getSelectionModel().getSelectedItem();

            if (!memberSeeUpdateTable.getSelectionModel().getSelectedItem().getId().equals(idWatcher.getText())) {
                new Alert(Alert.AlertType.ERROR, "You have no permission to delete this schedule.", ButtonType.OK).showAndWait();
            } else {

                db.schedule_table.delete_sche(s.getSche_id());
                new Alert(Alert.AlertType.INFORMATION, "Schedule Deleted", ButtonType.OK).showAndWait();
                ArrayList<db.schedule> list = db.schedule_table.showall();
                memberSeeUpdateTable.getItems().clear();
                memberSeeUpdateTable.getItems().addAll(list);
                memberSeeUpdateTable.refresh();
            }
        } catch (NullPointerException e) {
            new Alert(Alert.AlertType.ERROR, "Please select a row", ButtonType.OK).showAndWait();
        }
    }

    @FXML
    void updatePressedAction(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("UpdateScheduleScene.fxml"));
            Parent memberSignUpScene = loader.load();
            UpdateScheduleController memUpSaveControl = loader.getController();
            try {
                memUpSaveControl.rawData(memberSeeUpdateTable.getSelectionModel().getSelectedItem());
                Scene scene = new Scene(memberSignUpScene, 1360, 700);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                if (!memberSeeUpdateTable.getSelectionModel().getSelectedItem().getId().equals(idWatcher.getText())) {
                    new Alert(Alert.AlertType.ERROR, "You have no permission to update this schedule.", ButtonType.OK).showAndWait();
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
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dateForData.valueProperty().addListener((observable, oldValue, newValue) -> {
            txtFieldSearchByTrainerName.setText(null);
            dateForData.setValue(newValue);
            if (newValue != null) {

                ArrayList<db.schedule> list = db.schedule_table.searchbydate_ForViewSchedule(newValue);

                memberSeeUpdateTable.getItems().clear();
                memberSeeUpdateTable.getItems().addAll(list);
                memberSeeUpdateTable.refresh();

            } else {

                ArrayList<db.schedule> list = db.schedule_table.showall();
                memberSeeUpdateTable.getItems().clear();
                memberSeeUpdateTable.getItems().addAll(list);
                memberSeeUpdateTable.refresh();

            }
        });

        txtFieldSearchByTrainerName.textProperty().addListener((observable, oldValue, newValue) -> {
            dateForData.setValue(null);
            txtFieldSearchByTrainerName.setText(newValue);
            if (newValue != null) {
                ArrayList<db.schedule> list = db.schedule_table.searchbyname_ForViewSchedule(newValue);

                memberSeeUpdateTable.getItems().clear();
                memberSeeUpdateTable.getItems().addAll(list);
                memberSeeUpdateTable.refresh();

            } else {
                ArrayList<db.schedule> list = db.schedule_table.showall();
                memberSeeUpdateTable.getItems().clear();
                memberSeeUpdateTable.getItems().addAll(list);
                memberSeeUpdateTable.refresh();

            }

        });

        ArrayList<schedule> a = db.schedule_table.showall();

        id.setCellValueFactory(new PropertyValueFactory<>("sche_id"));
        content.setCellValueFactory(new PropertyValueFactory<>("content"));
        date.setCellValueFactory(new PropertyValueFactory<>("schedule_date"));
        trainerid.setCellValueFactory(new PropertyValueFactory<>("id"));
        trainername.setCellValueFactory(new PropertyValueFactory<>("name"));

        memberSeeUpdateTable.getItems().addAll(a);

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
}
