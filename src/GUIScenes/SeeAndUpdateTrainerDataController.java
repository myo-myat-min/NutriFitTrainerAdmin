/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIScenes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import db.trainer;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 *
 * @author ACER
 */
public class SeeAndUpdateTrainerDataController implements Initializable {
    
    @FXML
    protected TableView<db.trainer> memberSeeUpdateTable;

    @FXML
    private TableColumn<db.trainer, Integer> id;

    @FXML
    private TableColumn<db.trainer, String> trainerName;

    @FXML
    private TableColumn<db.trainer, String> email;

    @FXML
    private TableColumn<db.trainer, String> nrc;

    @FXML
    private TableColumn<db.trainer, String> phone;

    @FXML
    private TableColumn<db.trainer, String> address;

    @FXML
    private TableColumn<db.trainer, LocalDate> dob;

    @FXML
    private TableColumn<db.trainer, Boolean> gender;
    
    @FXML
    private TableColumn<db.trainer, Integer> feesCol;

//    @FXML
//    private TableColumn<TestTrainer, Double> height;
//
//    @FXML
//    private TableColumn<TestTrainer, Double> weight;
    
    @FXML
    private TableColumn<db.trainer, Boolean> status;

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
    
//    ObservableList<TestTrainer> list = FXCollections.observableArrayList(
//            new TestTrainer(1, "Mg Lin", "33564myo@gmail.com", "12/UKM(N)777888", LocalDate.now(), "09123456789", "", 180.00, 45, "male", "Active"),
//            new TestTrainer(2, "Mg Nyi", "33564myo@gmail.com", "12/UKM(N)777888", LocalDate.now(), "09123456789", "", 180.00, 45, "male", "Inactive"),
//            new TestTrainer(3, "Mg Win", "33564myo@gmail.com", "12/UKM(N)777888", LocalDate.now(), "09123456789", "", 180.00, 45, "male", "Active")
//    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        txtFieldSearchById.textProperty().addListener((ob, ov, nv) -> {
            txtFieldSearchByName.clear();
            txtFieldSearchById.setText(nv);
        });
        
        txtFieldSearchByName.textProperty().addListener((ob, ov, nv) -> {
            txtFieldSearchById.clear();
            txtFieldSearchByName.setText(nv);
        });
        
        txtFieldSearchByName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {

                try {
                    ArrayList<trainer> list = db.trainer_table.searchByname(newValue);
                    
                    memberSeeUpdateTable.getItems().clear();
                    memberSeeUpdateTable.getItems().addAll(list);
                    memberSeeUpdateTable.refresh();
                } catch (SQLException ex) {
              ex.printStackTrace();
                }

            } else {

                try {
                    ArrayList<trainer> list = db.trainer_table.showall();
                    memberSeeUpdateTable.getItems().clear();
                    memberSeeUpdateTable.getItems().addAll(list);
                    memberSeeUpdateTable.refresh();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
        
        txtFieldSearchById.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {

                try {
                    ArrayList<trainer> list = db.trainer_table.searchByid(newValue);
                    
                    memberSeeUpdateTable.getItems().clear();
                    memberSeeUpdateTable.getItems().addAll(list);
                    memberSeeUpdateTable.refresh();
                } catch (SQLException ex) {
              ex.printStackTrace();
                }

            } else {

                try {
                    ArrayList<trainer> list = db.trainer_table.showall();
                    memberSeeUpdateTable.getItems().clear();
                    memberSeeUpdateTable.getItems().addAll(list);
                    memberSeeUpdateTable.refresh();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        trainerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        nrc.setCellValueFactory(new PropertyValueFactory<>("nrc"));
        
        dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        
        phone.setCellValueFactory(new PropertyValueFactory<>("ph"));
        
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        feesCol.setCellValueFactory(new PropertyValueFactory<>("fees"));
        
        ArrayList<db.trainer> list = new ArrayList<>();
        try {
            list = db.trainer_table.showall();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        memberSeeUpdateTable.getItems().addAll(list);

//        updateButton.setDisable(false);
        updateButton.setOnAction((event) -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("TrainerUpdateSaveScene.fxml"));
                Parent memberSignUpScene = loader.load();
                TrainerUpdateSaveController memUpSaveControl = loader.getController();
                try {
                    memUpSaveControl.rawData(memberSeeUpdateTable.getSelectionModel().getSelectedItem());
                    Scene scene = new Scene(memberSignUpScene, 1360, 700);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setResizable(false);
                    window.setScene(scene);
                    window.show();
                } catch (NullPointerException e) {
                    new Alert(Alert.AlertType.ERROR, "Please select a row", ButtonType.OK).showAndWait();
                }
            } catch (IOException ex) {
                Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
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
