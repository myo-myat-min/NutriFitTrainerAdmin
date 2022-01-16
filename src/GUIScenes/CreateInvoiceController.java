/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIScenes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import db.ConnectDB;
import db.Member;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
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
import javafx.scene.paint.Color;
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
public class CreateInvoiceController implements Initializable {
    
    private FadeTransition fadeAnimationMember = new FadeTransition();
    private FadeTransition fadeAnimationEmptyNoti = new FadeTransition();
    
    @FXML
    private TableView<db.invoice> memberSeeUpdateTable;

    @FXML
    private TableColumn<db.invoice, String> id;

    @FXML
    private TableColumn<db.invoice, String> memberNameCol;
    
    @FXML
    private TableColumn<db.invoice, String> packageMonthCol;
    
    @FXML
    private TableColumn<db.invoice, String> nrcCol;

    @FXML
    private TableColumn<db.invoice, LocalDate> endDateCol;

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
    private JFXButton insertDataButton;

    @FXML
    private Text dateInvoice;

    @FXML
    private Circle nameCircle;

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
    private Text emptyNoti;
    
//    @FXML
//    private JFXButton printButton;
    
    private db.invoice selected;
    private ArrayList<db.Membership> ms = db.membership_table.showall();
    private Member m = new Member();
    private LocalDate enddate = LocalDate.now();
    ObservableList list = FXCollections.observableArrayList(
//            new CreateInvoiceTestMember(1, "Mg Soe", "2 month package", "13/UKM(N)256954", LocalDate.now()),
//            new CreateInvoiceTestMember(1, "Mg Mya", "1 month package", "13/UKM(N)726954", LocalDate.now()),
//            new CreateInvoiceTestMember(1, "Mg Hla", "4 month package", "13/UKM(N)645954", LocalDate.now())
    );
    
//    @FXML
//    void printPressedAction(MouseEvent event) {
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to print the data?", ButtonType.YES, ButtonType.NO);
//        alert.showAndWait();
//
//        if (alert.getResult() == ButtonType.YES) {
//            try {
//                Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("InvoicePrintScene.fxml"));
//                Scene scene = new Scene(trainerSignUpScene, 1360, 700);
//                Stage window = new Stage();
//                window.setWidth(700);
//                window.setHeight(690);
//                window.setResizable(false);
//                window.setScene(scene);
//                window.show();
//            } catch (IOException ex) {
//                Logger.getLogger(InvoicePrintController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } else {
//            event.consume();
//        }
//    }

    @FXML
    void insertPressedAction(MouseEvent event) {
        selected = memberSeeUpdateTable.getSelectionModel().getSelectedItem();
        if(selected != null){
            memberName.setText(selected.getName());
            m.setId(selected.getMember_id());
        } else {
            new Alert(AlertType.ERROR, "Please select a row", ButtonType.OK).showAndWait();
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        memberName.setText("Hello");
        ms.forEach((t) -> {
            packageChoice.getItems().add(t.getName());
        });
        packageChoice.getSelectionModel().selectFirst();

        txtFieldSearchById.textProperty().addListener((ob, ov, nv) -> {
            txtFieldSearchByName.clear();
            txtFieldSearchById.setText(nv);
        });
        
        txtFieldSearchByName.textProperty().addListener((ob, ov, nv) -> {
            txtFieldSearchById.clear();
            txtFieldSearchByName.setText(nv);
        });
        
        txtFieldSearchById.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.isEmpty()) {

                ArrayList<db.invoice> list = db.Membertable.searchbyid_ForCreateInvoice(newValue);

                memberSeeUpdateTable.getItems().clear();
                memberSeeUpdateTable.getItems().addAll(list);
                memberSeeUpdateTable.refresh();

            } else {

                ArrayList<db.invoice> list = db.Membertable.showall_ForCreateInvoice();
                memberSeeUpdateTable.getItems().clear();
                memberSeeUpdateTable.getItems().addAll(list);
                memberSeeUpdateTable.refresh();

            }
        });
        
        txtFieldSearchByName.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.isEmpty()) {

                ArrayList<db.invoice> list = db.Membertable.searchbyname_ForCreateInvoice(newValue);

                memberSeeUpdateTable.getItems().clear();
                memberSeeUpdateTable.getItems().addAll(list);
                memberSeeUpdateTable.refresh();

            } else {

                ArrayList<db.invoice> list = db.Membertable.showall_ForCreateInvoice();
                memberSeeUpdateTable.getItems().clear();
                memberSeeUpdateTable.getItems().addAll(list);
                memberSeeUpdateTable.refresh();

            }
        });

        id.setCellValueFactory(new PropertyValueFactory<>("member_id"));
        
        memberNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        packageMonthCol.setCellValueFactory(new PropertyValueFactory<>("membership_name"));
        
        nrcCol.setCellValueFactory(new PropertyValueFactory<>("nrc"));
        
        endDateCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        
//        memberSeeUpdateTable.setItems(list);
        
//        updateButton.setOnAction((event) -> {
//            
//            if (!memberName.getText().isEmpty()) {
//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to update the data?", ButtonType.YES, ButtonType.NO);
//                alert.showAndWait();
//
//                if (alert.getResult() == ButtonType.YES) {
//
//                } else {
//                    event.consume();
//                }
//            } else {
//                redCircleAnimation();
//                emptyNotiMethod();
//                emptyControllThreads();
//            }
//        });
        if (ConnectDB.CreateConnection() != null) {
            String initialSql = String.format("SELECT price FROM membership WHERE membership_name = '%s'", packageChoice.getValue());

            try (Connection con = ConnectDB.CreateConnection();
                    PreparedStatement ps = con.prepareStatement(initialSql);) {

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    packageFees.setText("Fees   : " + String.valueOf(rs.getInt("price")));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        packageChoice.valueProperty().addListener((ob, ov, nv) -> {

            if (ConnectDB.CreateConnection() != null) {
                String sql = String.format("SELECT price FROM membership WHERE membership_name = '%s'", nv);

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
        
        ArrayList<db.invoice> m = db.Membertable.Member_ForCreatingInvoices();
        memberSeeUpdateTable.getItems().addAll(m);
        
    }
    
    @FXML
    void updatePressedAction(MouseEvent event) {
        if (!memberName.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to update the membership plan?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                ms.forEach((t) -> {
                    
                    if (t.getName().equals(packageChoice.getSelectionModel().getSelectedItem())) {
                        String[] s = t.getName().split("-");
                        System.out.println(s[0]);
                        System.out.println(t.getId());

                        if (selected.getEnd().isAfter(LocalDate.now())) {
                            enddate = selected.getEnd();
                        } else {
                            enddate = LocalDate.now();
                        }
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        String admin_id = db.admin_table.getAdminID_ByEmail(stage.getTitle());
                        System.out.println(t);
                        System.out.println(admin_id);
                        db.invoice_table.insert_invoice(m, t, admin_id, enddate.plusMonths(Integer.parseInt(s[0])));
                        new Alert(Alert.AlertType.INFORMATION, "Change data are recorded.", ButtonType.OK).showAndWait();
                        try {
                            Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("InvoicePrintScene.fxml"));
                            Scene scene = new Scene(trainerSignUpScene);
                            Stage window = new Stage();
                            window.setWidth(495);
                            window.setHeight(690);
                            window.setResizable(false);
                            window.centerOnScreen();
                            window.setScene(scene);
                            window.show();
                        } catch (IOException ex) {
                            Logger.getLogger(InvoicePrintController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                memberSeeUpdateTable.getItems().clear();
                ArrayList<db.invoice> m = db.Membertable.Member_ForCreatingInvoices();
                memberSeeUpdateTable.getItems().addAll(m);

            } else {
                event.consume();
            }
//            System.out.println(memberName.getText());
        } else {
            redCircleAnimation();
            emptyNotiMethod();
            emptyControllThreads();
        }
    }

    public void redCircleAnimation() {
        fadeAnimationItems(fadeAnimationMember);
        fadeAnimationItems(fadeAnimationEmptyNoti);
    }
    
    public void fadeAnimationItems(FadeTransition fadeAnimation) {
        fadeAnimation.setDuration(Duration.millis(4000));
        fadeAnimation.setCycleCount(1);
        fadeAnimation.setAutoReverse(false);
        fadeAnimation.setFromValue(1);
        fadeAnimation.setToValue(0);
    }
    
    public void memberCondition() {
        if (memberName.getText().isEmpty()) {
            fadeAnimationMember.setNode(nameCircle);
            fadeAnimationMember.play();
        }
    }
    
    public void emptyNotiMethod() {
        fadeAnimationEmptyNoti.setNode(emptyNoti);
        fadeAnimationEmptyNoti.play();
    }

    public void emptyControllThreads() {
        Thread tMember = new Thread() {
            @Override
            public void run() {
                memberCondition();
            }
        };
        tMember.start();
    }
}