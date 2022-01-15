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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
public class InvoiceHistoryController implements Initializable {

    @FXML
    private TableView<db.invoice> memberSeeUpdateTable;

    @FXML
    private TableColumn<db.invoice, ?> invoiceId;

    @FXML
    private TableColumn<db.invoice, ?> memberId;

    @FXML
    private TableColumn<db.invoice, ?> memberName;

    @FXML
    private TableColumn<db.invoice, ?> email;

    @FXML
    private TableColumn<db.invoice, ?> packageMonth;

    @FXML
    private TableColumn<db.invoice, ?> purchaseDate;

    @FXML
    private TableColumn<db.invoice, ?> endDate;
    
    @FXML
    private TableColumn<db.invoice, ?> adminName;

    @FXML
    private JFXDrawer adminDrawer;

    @FXML
    private JFXHamburger adminMenu;

    @FXML
    private Rectangle titleTemplate;

    @FXML
    private Text titleRecordTrainerAtt;

    @FXML
    private JFXDatePicker startDateSearch;

    @FXML
    private JFXDatePicker endDateSearch;

//    @FXML
//    private JFXButton clearButton;

    @FXML
    private JFXTextField txtFieldSearchByName;

//    @FXML
//    void clearPressedAction(MouseEvent event) {
//        try {
//            Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("InvoiceHistoryScene.fxml"));
//            Scene scene = new Scene(trainerSignUpScene, 1360, 700);
//            scene.getStylesheets().add("CSS/TableDesign.css");
//            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            window.setResizable(false);
//            window.setScene(scene);
//            window.show();
//        } catch (IOException ex) {
//            Logger.getLogger(InvoiceHistoryController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    ObservableList<?> list = FXCollections.observableArrayList( //            new RecordAttendanceTrainer("T-00001", "Mg Mg", "nothing"),
            //            new RecordAttendanceTrainer("T-00002", "Ko Ko", "nothing"),
            //            new RecordAttendanceTrainer("T-00003", "Mg Mya", "nothing")
            //            new RecordAttendanceTrainer(4, "Aye Aye", "nothing"),
            //            new RecordAttendanceTrainer(5, "Aung Aung", "nothing")
            );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        txtFieldSearchByName.textProperty().addListener((observable, oldValue, newValue) -> {
            startDateSearch.setValue(null);
            endDateSearch.setValue(null);
            txtFieldSearchByName.setText(newValue);
            if (newValue != null) {
                ArrayList<db.invoice> i = db.invoice_table.ShowInvoices_ByNames(newValue);
                memberSeeUpdateTable.getItems().clear();
                memberSeeUpdateTable.getItems().addAll(i);
                memberSeeUpdateTable.refresh();
            } else {
                ArrayList<db.invoice> i = db.invoice_table.ShowInvoices();
                memberSeeUpdateTable.getItems().clear();
                memberSeeUpdateTable.getItems().addAll(i);
                memberSeeUpdateTable.refresh();
            }
        });

        endDateSearch.valueProperty().addListener((observable, oldValue, newValue) -> {
            txtFieldSearchByName.clear();
            endDateSearch.setValue(newValue);
            if (newValue != null) {
                ArrayList<db.invoice> i = db.invoice_table.ShowInvoices_ByDate(startDateSearch.getValue(), newValue);
                memberSeeUpdateTable.getItems().clear();
                memberSeeUpdateTable.getItems().addAll(i);
                memberSeeUpdateTable.refresh();
            } else {
                ArrayList<db.invoice> i = db.invoice_table.ShowInvoices();
                memberSeeUpdateTable.getItems().clear();
                memberSeeUpdateTable.getItems().addAll(i);
                memberSeeUpdateTable.refresh();
            }

        });

        startDateSearch.valueProperty().addListener((observable, oldValue, newValue) -> {
            txtFieldSearchByName.clear();
            startDateSearch.setValue(newValue);
            if (newValue != null) {
                ArrayList<db.invoice> i = db.invoice_table.ShowInvoices_ByDate(newValue, endDateSearch.getValue());
                memberSeeUpdateTable.getItems().clear();
                memberSeeUpdateTable.getItems().addAll(i);
                memberSeeUpdateTable.refresh();
            } else {
                ArrayList<db.invoice> i = db.invoice_table.ShowInvoices();
                memberSeeUpdateTable.getItems().clear();
                memberSeeUpdateTable.getItems().addAll(i);
                memberSeeUpdateTable.refresh();
            }

        });

        invoiceId.setCellValueFactory(new PropertyValueFactory<>("Iid"));

        memberId.setCellValueFactory(new PropertyValueFactory<>("id"));

        memberName.setCellValueFactory(new PropertyValueFactory<>("name"));

        email.setCellValueFactory(new PropertyValueFactory<>("email"));

        packageMonth.setCellValueFactory(new PropertyValueFactory<>("membership_name"));

        purchaseDate.setCellValueFactory(new PropertyValueFactory<>("invoice_date"));

        endDate.setCellValueFactory(new PropertyValueFactory<>("end"));
        
        adminName.setCellValueFactory(new PropertyValueFactory<>(""));

        ArrayList<db.invoice> a = db.invoice_table.ShowInvoices();

        memberSeeUpdateTable.getItems().addAll(a);

//        memberSeeUpdateTable.setItems(list);
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
