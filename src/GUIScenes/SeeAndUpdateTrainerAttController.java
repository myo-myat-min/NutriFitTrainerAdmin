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
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;

/**
 *
 * @author ACER
 */
public class SeeAndUpdateTrainerAttController implements Initializable {
    
    @FXML
    protected TableView<RecordAttendanceTrainer> memberSeeUpdateTable;

    @FXML
    private TableColumn<RecordAttendanceTrainer, Integer> id;

    @FXML
    private TableColumn<RecordAttendanceTrainer, String> trainerName;

    @FXML
    private TableColumn<RecordAttendanceTrainer, String> att;

    @FXML
    private TableColumn<RecordAttendanceTrainer, String> lateHour;

    @FXML
    private TableColumn<RecordAttendanceTrainer, String> reason;

    @FXML
    private JFXDrawer adminDrawer;

    @FXML
    private JFXHamburger adminMenu;

    @FXML
    private Rectangle titleTemplate;

    @FXML
    private Text titleRecordTrainerAtt;
    
    @FXML
    private JFXButton updateButton;
    
    @FXML
    private JFXTextField txtFieldSearchById;

    @FXML
    private JFXTextField txtFieldSearchByName;
    
    @FXML
    private Circle dateCircle;

    @FXML
    private JFXDatePicker dateForData;

    @FXML
    private Text emptyNoti;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dateForData.setValue(LocalDate.now());
        if (dateForData.getValue() != null) {
            memberSeeUpdateTable.getItems().clear();
            memberSeeUpdateTable.getItems().addAll(db.DailyAttendancetable.showda(dateForData.getValue()));
        }
        
        dateForData.valueProperty().addListener((ob, ov, nv) -> {
            memberSeeUpdateTable.getItems().clear();
            memberSeeUpdateTable.getItems().addAll(db.DailyAttendancetable.showda(nv));
        });
        
        txtFieldSearchById.textProperty().addListener((ob, ov, nv) -> {
            txtFieldSearchByName.clear();
            txtFieldSearchById.setText(nv);
        });

        txtFieldSearchByName.textProperty().addListener((ob, ov, nv) -> {
            txtFieldSearchById.clear();
            txtFieldSearchByName.setText(nv);
        });

        txtFieldSearchById.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {

                ArrayList<RecordAttendanceTrainer> list = db.DailyAttendancetable.searchbyid(newValue,dateForData.getValue());

                memberSeeUpdateTable.getItems().clear();
                memberSeeUpdateTable.getItems().addAll(list);
                memberSeeUpdateTable.refresh();

            } else {
                    ArrayList<RecordAttendanceTrainer> list = db.DailyAttendancetable.showda(dateForData.getValue());
                    memberSeeUpdateTable.getItems().clear();
                    memberSeeUpdateTable.getItems().addAll(list);
                    memberSeeUpdateTable.refresh();
            }
        });
        
        txtFieldSearchByName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {

                ArrayList<RecordAttendanceTrainer> list = db.DailyAttendancetable.searchbyname(newValue, dateForData.getValue());

                memberSeeUpdateTable.getItems().clear();
                memberSeeUpdateTable.getItems().addAll(list);
                memberSeeUpdateTable.refresh();

            } else {
                ArrayList<RecordAttendanceTrainer> list = db.DailyAttendancetable.showda(dateForData.getValue());
                memberSeeUpdateTable.getItems().clear();
                memberSeeUpdateTable.getItems().addAll(list);
                memberSeeUpdateTable.refresh();
            }
        }
        );
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        trainerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        att.setCellValueFactory(new PropertyValueFactory<>("att"));
        
        lateHour.setCellValueFactory(new PropertyValueFactory<>("arrived_time"));
        
        reason.setCellValueFactory(new PropertyValueFactory<>("note"));
        reason.setCellFactory(TextFieldTableCell.forTableColumn());
        reason.setOnEditCommit((event) -> {
            RecordAttendanceTrainer edited = event.getRowValue();
            String nv = event.getNewValue();
            edited.setNote(nv);
        });
                     
        
        updateButton.setOnAction((event) -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to update the data?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                ArrayList<RecordAttendanceTrainer> a = new ArrayList<>();

                memberSeeUpdateTable.getItems().forEach((t) -> {

                    String attend = t.att.getSelectionModel().getSelectedItem().toString();
                    LocalTime arrived_time = t.arrived_time.getValue();
                    if (attend.equals("X")) {
                        arrived_time = null;
                        System.out.println(arrived_time);
                    }

                    a.add(new RecordAttendanceTrainer(attend, arrived_time, t.getNote(), t.getId(), t.getName()));
                    System.out.println(a);
                    
                });
                
                db.DailyAttendancetable.Update_Da(a, dateForData.getValue());
                new Alert(Alert.AlertType.INFORMATION, "Change data are recorded.", ButtonType.OK).showAndWait();

            } else {
                event.consume();
            }
        });

        MemberSignUpController memSignUpControll = new MemberSignUpController();
        HamburgerBasicCloseTransition burgerTask = new HamburgerBasicCloseTransition(adminMenu);
        burgerTask.setRate(-1);
        adminMenu.setOnMousePressed((event1) -> {
            try {
                adminDrawer.setSidePane(memSignUpControll.adminMenuScene(titleRecordTrainerAtt.getText()));
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
