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
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class TrainerAttController implements Initializable {

    /**
     * Initializes the controller class.
     *
     */
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
    private JFXButton recordAttButton;

    @FXML
    private TableView<RecordAttendanceTrainer> attTable;

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
    private Text currentDate;

    ObservableList<RecordAttendanceTrainer> list = FXCollections.observableArrayList(
//            new RecordAttendanceTrainer("T-00001", "Mg Mg", "nothing"),
//            new RecordAttendanceTrainer("T-00002", "Ko Ko", "nothing"),
//            new RecordAttendanceTrainer("T-00003", "Mg Mya", "nothing")
//            new RecordAttendanceTrainer(4, "Aye Aye", "nothing"),
//            new RecordAttendanceTrainer(5, "Aung Aung", "nothing")
    );

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        LocalDate now = LocalDate.now();
        
        currentDate.setText(dtf.format(now));
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        trainerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        att.setCellValueFactory(new PropertyValueFactory<>("att"));

        try {
            ArrayList<db.trainer> a = db.trainer_table.showall();
            a.forEach((t) -> {
                list.add(new RecordAttendanceTrainer(t.getId(), t.getName()));
            });

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        lateHour.setCellValueFactory(new PropertyValueFactory<>("arrived_time"));
        
        reason.setCellValueFactory(new PropertyValueFactory<>("note"));
        reason.setCellFactory(TextFieldTableCell.forTableColumn());
        reason.setOnEditCommit((event) -> {
            RecordAttendanceTrainer edited = event.getRowValue();
            String nv = event.getNewValue();
            edited.setNote(nv);
        });
        
        attTable.setItems(list);

        MemberSignUpController memSignUpControll = new MemberSignUpController();
        HamburgerBasicCloseTransition burgerTask = new HamburgerBasicCloseTransition(adminMenu);
        burgerTask.setRate(-1);
        adminMenu.setOnMousePressed((event) -> {
            try {
                adminDrawer.setSidePane(memSignUpControll.adminMenuScene(titleRecordTrainerAtt.getText()));
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
    void recordAttPressedAction(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to record the data?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            ArrayList<RecordAttendanceTrainer> a = new ArrayList<>();

            attTable.getItems().forEach((t) -> {

                String attend = t.att.getSelectionModel().getSelectedItem().toString();
                LocalTime arrived_time = t.arrived_time.getValue();
                if (t.getAtt().equals("X")) {
                    arrived_time = null;
                }

                a.add(new RecordAttendanceTrainer(attend, arrived_time, t.getNote(), t.getId(), t.getName()));
            });
            try {
                db.DailyAttendancetable.insert_DA(a);
                Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("TrainerAttendanceScene.fxml"));
                Scene scene = new Scene(trainerSignUpScene);
                scene.getStylesheets().add("CSS/TableDesign.css");
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setResizable(false);
                window.setScene(scene);
                window.show();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "You have to record trainers' attendance only once a day. If you want to update "
                        + "attendance data, please click \"Trainer Attendance\" in menu.", ButtonType.OK).showAndWait();
            }

        } else {
            event.consume();
        }
    }

}
