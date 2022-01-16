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
import db.Member;
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
import javafx.scene.control.Alert.AlertType;
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
public class SeeAndUpdateMemberDataController implements Initializable {

    @FXML
    protected TableView<db.Member> memberSeeUpdateTable;

    @FXML
    private TableColumn<db.Member, Integer> id;

    @FXML
    private TableColumn<db.Member, String> dbMemberName;

    @FXML
    private TableColumn<db.Member, String> dbEmail;

    @FXML
    private TableColumn<db.Member, String> dbNrc;

    @FXML
    private TableColumn<db.Member, String> dbPhone;

    @FXML
    private TableColumn<db.Member, String> dbAddress;

    @FXML
    private TableColumn<db.Member, LocalDate> dob;

    @FXML
    private TableColumn<db.Member, String> gender;

    @FXML
    private TableColumn<db.Member, Float> height;

    @FXML
    private TableColumn<db.Member, Float> weight;

    @FXML
    private TableColumn<db.Member, String> medicalData;

    @FXML
    private TableColumn<db.Member, String> packageChoice;

    @FXML
    private TableColumn<db.Member, String> status;

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

//    ObservableList<TestMember> list = FXCollections.observableArrayList(
//            new TestMember(1, "Mg Myo", "33564myo@gmail.com", "12/UKM(N)777888", LocalDate.now(), "", "09123456789", "", 180.00, 45, "male", "1", "Active"),
//            new TestMember(2, "Mg Kyaw", "33564myo@gmail.com", "12/UKM(N)777888", LocalDate.now(), "", "09123456789", "", 180, 45, "male", "4", "Active"),
//            new TestMember(3, "Ma Hla", "33564myo@gmail.com", "12/UKM(N)777888", LocalDate.now(), "", "09123456789", "", 180, 45, "female", "8", "Active"),
//            new TestMember(4, "Ma Mya", "33564myo@gmail.com", "12/UKM(N)777888", LocalDate.now(), "", "09123456789", "", 180, 45, "felmale", "1", "Active"),
//            new TestMember(4, "Mg Moo", "33564myo@gmail.com", "12/UKM(N)777888", LocalDate.now(), "", "09123456789", "", 180, 45, "male", "1", "Inactive")
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

                ArrayList<Member> list = db.Membertable.searchbyname(newValue);

                memberSeeUpdateTable.getItems().clear();
                memberSeeUpdateTable.getItems().addAll(list);
                memberSeeUpdateTable.refresh();

            } else {
                
                try {
                    ArrayList<Member> list = db.Membertable.showall();
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

                ArrayList<Member> list = db.Membertable.searchbyid(newValue);

                memberSeeUpdateTable.getItems().clear();
                memberSeeUpdateTable.getItems().addAll(list);
                memberSeeUpdateTable.refresh();

            } else {
                
                try {
                    ArrayList<Member> list = db.Membertable.showall();
                    memberSeeUpdateTable.getItems().clear();
                    memberSeeUpdateTable.getItems().addAll(list);
                    memberSeeUpdateTable.refresh();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });

        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        dbMemberName.setCellValueFactory(new PropertyValueFactory<>("name"));

        dbEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        dbNrc.setCellValueFactory(new PropertyValueFactory<>("nrc"));

        dbAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        
        dob.setCellValueFactory(new PropertyValueFactory<>("dob"));

        dbPhone.setCellValueFactory(new PropertyValueFactory<>("ph"));

        medicalData.setCellValueFactory(new PropertyValueFactory<>("medical_data"));
        
        height.setCellValueFactory(new PropertyValueFactory<>("height"));

        weight.setCellValueFactory(new PropertyValueFactory<>("weight"));

        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        
//        List<Boolean> columnData = new ArrayList<>();
//        memberSeeUpdateTable.getItems().forEach((item) -> {
//            if(gender.getCellObservableValue(item).getValue()){
//                gender.setC
//            }
//        });

//        packageChoice.setCellValueFactory(new PropertyValueFactory<>("packageChoice"));
//        packageChoice.setCellFactory(TextFieldTableCell.forTableColumn());
//        packageChoice.setOnEditCommit((event) -> {
//            TestMember edited = event.getRowValue();
//            String nv = event.getNewValue();
//            if(Pattern.matches("[\\d]{1,2}", nv)){
//                edited.setPackageChoice(nv);
//            }else{
//                memberSeeUpdateTable.refresh();
//            }
//        });
//        status.setCellValueFactory(new PropertyValueFactory<>("status"));
//        status.setCellFactory(TextFieldTableCell.forTableColumn());
//        status.setOnEditCommit((event) -> {
//            TestMember edited = event.getRowValue();
//            String nv = event.getNewValue();
//            String toLowerCase = nv.toLowerCase();
//            if(Pattern.matches("active|inactive", toLowerCase)){
//                edited.setStatus(toLowerCase);
//            }else{
//                memberSeeUpdateTable.refresh();
//            }
//        });
        ArrayList<db.Member> list = null;
        try {
            list = db.Membertable.showall();
        } catch (SQLException ex) {
            Logger.getLogger(SeeAndUpdateMemberDataController.class.getName()).log(Level.SEVERE, null, ex);
        }
        memberSeeUpdateTable.getItems().addAll(list);

//        updateButton.setDisable(false);
        updateButton.setOnAction((event) -> {
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to update the data?", ButtonType.YES, ButtonType.NO);
//            alert.showAndWait();
//
//            if (alert.getResult() == ButtonType.YES) {
//     
//                ArrayList<Member> m=new ArrayList<>();
//           memberSeeUpdateTable.getItems().forEach((t) -> {
//               m.add(t);
//           });
//                
//                try {
//                     db.Membertable.batch_InsertMember(m);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            } else {
//                event.consume();
//            }

            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("MemberUpdateSaveScene.fxml"));
                Parent memberSignUpScene = loader.load();
                MemberUpdateSaveController memUpSaveControl = loader.getController();
                try {
                    memUpSaveControl.rawData(memberSeeUpdateTable.getSelectionModel().getSelectedItem());
                    Scene scene = new Scene(memberSignUpScene);
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
            System.out.println(memberSeeUpdateTable.getSelectionModel().getSelectedItem());

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
