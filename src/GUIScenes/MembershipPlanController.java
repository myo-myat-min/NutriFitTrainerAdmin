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
import db.Membership;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class MembershipPlanController implements Initializable {
    
    /**
     * Initializes the controller class.
     *
     */

    @FXML
    private TableView<db.Membership> membershipTable;

    @FXML
    private TableColumn<db.Membership, Integer> id;

    @FXML
    private TableColumn<db.Membership, String> packageMonth;

    @FXML
    private TableColumn<db.Membership, Integer> price;

    @FXML
    private Text txtWelcome;

    @FXML
    private Text txtNutriFit;

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
    
//    ObservableList<db.Membership> list = FXCollections.observableArrayList(
//            new Membership(1, "1", 500),
//            new Membership(2, "4", 500),
//            new Membership(3, "8", 500)
//    );
    
    @FXML
    void updatePressedAction(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to record the data?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            ArrayList<db.Membership> ms = new ArrayList<>();

            membershipTable.getItems().forEach((t) -> {
                ms.add(new Membership(t.getId(), t.getName(), t.getPrice()));

            });

            db.membership_table.update_membership(ms);
            new Alert(Alert.AlertType.INFORMATION, "Changed data are recorded", ButtonType.OK).showAndWait();
        } else {
            event.consume();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        ArrayList<db.Membership> ms = db.membership_table.showall();

        membershipTable.getItems().addAll(ms);
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        packageMonth.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        price.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
            @Override
            public String toString(Integer object) {
                  return String.format("%s", object);
            }

            @Override
            public Integer fromString(String string) {
                try {
                    return Integer.parseInt(string);
                } catch (NumberFormatException e) {
                    return 0;
                }
            }
        }));
        price.setOnEditCommit((event) -> {
            Membership edited = event.getRowValue();
            Integer nv = event.getNewValue();
            edited.setPrice(nv);
        });
        
//        membershipTable.setItems(list);
        
        MemberSignUpController memSignUpControll = new MemberSignUpController();
        HamburgerBasicCloseTransition burgerTask = new HamburgerBasicCloseTransition(adminMenu);
        burgerTask.setRate(-1);
        adminMenu.setOnMousePressed((event) -> {
            try {
                adminDrawer.setSidePane(memSignUpControll.adminMenuScene(titleRecordTrainerAtt.getText()));
            } catch (IOException ex) {
                Logger.getLogger(TrainerSignUpController.class.getName()).log(Level.SEVERE, null, ex);
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
