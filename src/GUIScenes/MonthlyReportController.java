/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIScenes;

import db.MonthlyReportTrainer;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author ACER
 */
public class MonthlyReportController implements Initializable {

    private FadeTransition fadeAnimationDate = new FadeTransition();
    private FadeTransition fadeAnimationEmptyNoti = new FadeTransition();
    private FadeTransition fadeAnimationFineDay = new FadeTransition();
    private FadeTransition fadeAnimationFineMinute = new FadeTransition();
    private FadeTransition fadeAnimationInvalidFineDay = new FadeTransition();
    private FadeTransition fadeAnimationInvalidFineMinute = new FadeTransition();

    @FXML
    private Rectangle titleTemplate;

    @FXML
    private Text titleMonthlyReport;

    @FXML
    private Pane lineGraphPane;

    @FXML
    private TableView<MonthlyReportTrainer> trainerMonthlyReportTable;

    @FXML
    private TableColumn<MonthlyReportTrainer, String> trainerName;

    @FXML
    private TableColumn<MonthlyReportTrainer, Integer> lateTime;

    @FXML
    private TableColumn<MonthlyReportTrainer, Integer> leaveDay;

    @FXML
    private TableColumn<MonthlyReportTrainer, Double> fees;

//    ObservableList<MonthlyReportTrainer> list = FXCollections.observableArrayList(
//            new MonthlyReportTrainer("Mg Mg", 5, 3, 90000),
//            new MonthlyReportTrainer("Mg Mg1", 5, 3, 90000),
//            new MonthlyReportTrainer("Mg Mg2", 5, 3, 90000),
//            new MonthlyReportTrainer("Mg Mg3", 5, 3, 90000)
//    );

    @FXML
    private Text dateMonthlyReport;

    @FXML
    private Text numberOfNewMember;

    @FXML
    private JFXHamburger adminMenu;

    @FXML
    private JFXDrawer adminDrawer;

    @FXML
    private JFXDatePicker dateForData;

    @FXML
    private JFXButton insertDataButton;

    @FXML
    private Circle dateCircle;

    @FXML
    private Text emptyNoti;

    @FXML
    private JFXButton printButton;
    
    @FXML
    private JFXTextField txtfieldFinePerMinute;

    @FXML
    private JFXTextField txtfieldFinePerDay;
    
    CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis(0, 50, 10);
    LineChart<String, Number> marksChart = new LineChart(xAxis, yAxis);
    
    @FXML
    private Circle completeCircFineDay;

    @FXML
    private Circle completeCircFineMinute;
    
    @FXML
    private Circle fineDayCircle;

    @FXML
    private Circle fineMinuteCircle;
    
    @FXML
    private Text invalidFineMinute;

    @FXML
    private Text invalidFineDay;

    @FXML
    void adminMenuMousePressedAction(MouseEvent event) {

    }
    
    @FXML
    void finePerMinuteAction(ActionEvent event) {
        txtfieldFinePerDay.requestFocus();
        txtfieldFinePerDay.selectAll();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lineGraph();
        trainerName.setCellValueFactory(new PropertyValueFactory<>("trainerName"));
        lateTime.setCellValueFactory(new PropertyValueFactory<>("lateTime"));
        leaveDay.setCellValueFactory(new PropertyValueFactory<>("leaveDay"));
        fees.setCellValueFactory(new PropertyValueFactory<>("fees"));
        
        txtfieldFinePerMinute.setText(String.valueOf(50));
        txtfieldFinePerDay.setText(String.valueOf(3000));

//        trainerMonthlyReportTable.setItems(list);

//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM yyyy");
//        LocalDateTime now = LocalDateTime.now();
//        dateMonthlyReport.setText(dtf.format(now));
       
        MemberSignUpController memSignUpControll = new MemberSignUpController();
        HamburgerBasicCloseTransition burgerTask = new HamburgerBasicCloseTransition(adminMenu);
        burgerTask.setRate(-1);
        adminMenu.setOnMousePressed((event) -> {
            try {
                adminDrawer.setSidePane(memSignUpControll.adminMenuScene(titleMonthlyReport.getText()));
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

    private void lineGraph() {
        xAxis.setLabel("Months");
        yAxis.setLabel("Number of members");

        XYChart.Series series = new XYChart.Series();
        series.setName("New Member");
        series.getData().add(new XYChart.Data<>("January", 0));
        series.getData().add(new XYChart.Data<>("February", 0));
        series.getData().add(new XYChart.Data<>("March", 0));
        series.getData().add(new XYChart.Data<>("April", 0));
        series.getData().add(new XYChart.Data<>("May", 0));
        series.getData().add(new XYChart.Data<>("June", 0));
        series.getData().add(new XYChart.Data<>("July", 0));
        series.getData().add(new XYChart.Data<>("August", 0));
        series.getData().add(new XYChart.Data<>("September", 0));
        series.getData().add(new XYChart.Data<>("October", 0));
        series.getData().add(new XYChart.Data<>("November", 0));
        series.getData().add(new XYChart.Data<>("Decomber", 0));
        marksChart.getData().add(series);
        marksChart.setMaxWidth(681);
        marksChart.setMaxHeight(403);
        xAxis.setTickLabelFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 14));
        yAxis.setTickLabelFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 14));
        lineGraphPane.getChildren().add(marksChart);
    }

    @FXML
    void insertPressedAction(MouseEvent event) {
        if (dateForData.getValue() != null && !txtfieldFinePerMinute.getText().isEmpty() && !txtfieldFinePerDay.getText().isEmpty()) {
            if (completeCircFineDay.getFill() == Color.RED || completeCircFineMinute.getFill() == Color.RED){
                invalidAnimation();
                invalidControllThreads();
            }
        } else {
            redCircleAnimation();
            emptyNotiMethod();
            emptyControllThreads();
        }
    }
    
    @FXML
    void insertReleasedAction(MouseEvent event) {
        if (dateForData.getValue() != null && !txtfieldFinePerMinute.getText().isEmpty() && !txtfieldFinePerDay.getText().isEmpty()) {
            if (completeCircFineDay.getFill() == Color.GREEN && completeCircFineMinute.getFill() == Color.GREEN) {

                marksChart.getData().remove(this);
                marksChart.getData().clear();
                int year = dateForData.getValue().getYear();
                int month = dateForData.getValue().getMonthValue();
                int end_day = dateForData.getValue().lengthOfMonth();
                LocalDate s = LocalDate.now();
                XYChart.Series series = new XYChart.Series();
                series.setName("New Member");

                series.getData().add(new XYChart.Data<>("January", db.Membertable.new_member(1, year, s.withMonth(1).lengthOfMonth())));
                series.getData().add(new XYChart.Data<>("February", db.Membertable.new_member(2, year, s.withMonth(2).lengthOfMonth())));
                series.getData().add(new XYChart.Data<>("March", db.Membertable.new_member(3, year, s.withMonth(3).lengthOfMonth())));
                series.getData().add(new XYChart.Data<>("April", db.Membertable.new_member(4, year, s.withMonth(4).lengthOfMonth())));
                series.getData().add(new XYChart.Data<>("May", db.Membertable.new_member(5, year, s.withMonth(5).lengthOfMonth())));
                series.getData().add(new XYChart.Data<>("June", db.Membertable.new_member(6, year, s.withMonth(6).lengthOfMonth())));
                series.getData().add(new XYChart.Data<>("July", db.Membertable.new_member(7, year, s.withMonth(7).lengthOfMonth())));
                series.getData().add(new XYChart.Data<>("August", db.Membertable.new_member(8, year, s.withMonth(8).lengthOfMonth())));
                series.getData().add(new XYChart.Data<>("September", db.Membertable.new_member(9, year, s.withMonth(9).lengthOfMonth())));
                series.getData().add(new XYChart.Data<>("October", db.Membertable.new_member(10, year, s.withMonth(10).lengthOfMonth())));
                series.getData().add(new XYChart.Data<>("November", db.Membertable.new_member(11, year, s.withMonth(11).lengthOfMonth())));
                series.getData().add(new XYChart.Data<>("Decomber", db.Membertable.new_member(12, year, s.withMonth(12).lengthOfMonth())));

                marksChart.getData().add(series);
                dateMonthlyReport.setText(String.format("%s %s", dateForData.getValue().getMonth(), dateForData.getValue().getYear()));
                numberOfNewMember.setText("New Member : " + db.Membertable.new_member(month, year, end_day));
                System.out.println(txtfieldFinePerMinute.getText() + " fine per minute");
                System.out.println(txtfieldFinePerDay.getText() + " fine per day");
                ArrayList<db.MonthlyReportTrainer> m = db.DailyAttendancetable.showAllTrainerMonthlyAtt(dateForData.getValue(),
                        Integer.parseInt(txtfieldFinePerMinute.getText()), Integer.parseInt(txtfieldFinePerDay.getText()));
                trainerMonthlyReportTable.getItems().addAll(m);
            } else {
                invalidAnimation();
                invalidControllThreads();
            }
        } else {
            redCircleAnimation();
            emptyNotiMethod();
            emptyControllThreads();
        }
    }
    
    public void fadeAnimationItems(FadeTransition fadeAnimation) {
        fadeAnimation.setDuration(Duration.millis(4000));
        fadeAnimation.setCycleCount(1);
        fadeAnimation.setAutoReverse(false);
        fadeAnimation.setFromValue(1);
        fadeAnimation.setToValue(0);
    }

    public void redCircleAnimation() {
        fadeAnimationItems(fadeAnimationDate);
        fadeAnimationItems(fadeAnimationEmptyNoti);
        fadeAnimationItems(fadeAnimationFineDay);
        fadeAnimationItems(fadeAnimationFineMinute);
    }
    
    public void invalidAnimation() {
        fadeAnimationItems(fadeAnimationInvalidFineDay);
        fadeAnimationItems(fadeAnimationInvalidFineMinute);
    }
    
    public void invalidFineDayCondition() {
        if (!Pattern.matches("\\d{1,}", txtfieldFinePerDay.getText())) {
            completeCircFineDay.setFill(Color.RED);
            fadeAnimationInvalidFineDay.setNode(invalidFineDay);
            fadeAnimationInvalidFineDay.play();
        } else {
            completeCircFineDay.setFill(Color.GREEN);
        }
    }
    
    public void invalidFineMinuteCondition() {
        if (!Pattern.matches("\\d{1,}", txtfieldFinePerMinute.getText())) {
            completeCircFineMinute.setFill(Color.RED);
            fadeAnimationInvalidFineMinute.setNode(invalidFineMinute);
            fadeAnimationInvalidFineMinute.play();
        } else {
            completeCircFineMinute.setFill(Color.GREEN);
        }
    }

    public void dateCondition() {
        if (dateForData.getValue() == null) {
            fadeAnimationDate.setNode(dateCircle);
            fadeAnimationDate.play();
        }
    }
    
    public void fineDayCondition() {
        if (txtfieldFinePerDay.getText().isEmpty()) {
            fadeAnimationFineDay.setNode(fineDayCircle);
            fadeAnimationFineDay.play();
        }
    }
    
    public void fineMinuteCondition() {
        if (txtfieldFinePerMinute.getText().isEmpty()) {
            fadeAnimationFineMinute.setNode(fineMinuteCircle);
            fadeAnimationFineMinute.play();
        }
    }

    public void emptyNotiMethod() {
        fadeAnimationEmptyNoti.setNode(emptyNoti);
        fadeAnimationEmptyNoti.play();
    }

    public void emptyControllThreads() {
        Thread tDate = new Thread() {
            @Override
            public void run() {
                dateCondition();
            }
        };
        Thread tFineDay = new Thread() {
            @Override
            public void run() {
                fineDayCondition();
            }
        };
        Thread tFineMinute = new Thread() {
            @Override
            public void run() {
                fineMinuteCondition();
            }
        };
        tDate.start();
        try {
            tDate.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tFineDay.start();
        try {
            tFineDay.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tFineMinute.start();
    }

    public void invalidControllThreads() {
        Thread tInvalidFineDay = new Thread() {
            @Override
            public void run() {
                invalidFineDayCondition();
            }
        };
        Thread tInvalidFineMinute = new Thread() {
            @Override
            public void run() {
                invalidFineMinuteCondition();
            }
        };
        tInvalidFineDay.start();
        try {
            tInvalidFineDay.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tInvalidFineMinute.start();
    }

    @FXML
    void printPressedAction(MouseEvent event) {
        if (dateForData.getValue() != null && !txtfieldFinePerMinute.getText().isEmpty() && !txtfieldFinePerDay.getText().isEmpty()) {
            if (completeCircFineDay.getFill() == Color.RED || completeCircFineMinute.getFill() == Color.RED) {
                invalidAnimation();
                invalidControllThreads();
            }
        } else {
            redCircleAnimation();
            emptyNotiMethod();
            emptyControllThreads();
        }

    }

    @FXML
    void printReleasedAction(MouseEvent event) {

        if (dateForData.getValue() != null && !txtfieldFinePerMinute.getText().isEmpty() && !txtfieldFinePerDay.getText().isEmpty()) {
            if (completeCircFineDay.getFill() == Color.GREEN && completeCircFineMinute.getFill() == Color.GREEN) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to print the data?", ButtonType.YES, ButtonType.NO);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("MonthlyReportPrintScene.fxml"));
                        Parent printScene = loader.load();
                        MonthlyReportPrintController controller = loader.getController();
                        controller.addFineData(Integer.parseInt(txtfieldFinePerDay.getText()), 
                                Integer.parseInt(txtfieldFinePerMinute.getText()), String.valueOf(dateForData.getValue()));
                        Scene scene = new Scene(printScene, 1360, 700);
                        scene.getStylesheets().add("CSS/TableDesign.css");
                        Stage window = new Stage();
                        window.setWidth(598);
                        window.setHeight(710);
                        window.setResizable(false);
                        window.centerOnScreen();
                        window.setScene(scene);
                        window.show();
                    } catch (IOException e) {
                        Logger.getLogger(MonthlyReportPrintController.class.getName()).log(Level.SEVERE, null, e);
                    }
                } else {
                    event.consume();
                }

            } else {
                invalidAnimation();
                invalidControllThreads();
            }
        } else {
            redCircleAnimation();
            emptyNotiMethod();
            emptyControllThreads();
        }

    }

    @FXML
    void clearPressedAction(MouseEvent event) {
        trainerMonthlyReportTable.getItems().clear();
    }

}
