/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIScenes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import db.Member;
import db.MonthlyReportTrainer;
import db.trainer;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.Printer.MarginType;
import javafx.print.PrinterJob;
import javafx.scene.Node;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

/**
 *
 * @author U Kyi
 */
public class MonthlyReportPrintController implements Initializable {

    private FadeTransition fadeAnimationDate = new FadeTransition();
    private FadeTransition fadeAnimationEmptyNoti = new FadeTransition();

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Pane linegraph;

    @FXML
    private TableView<MonthlyReportTrainer> TMRtable;

    @FXML
    private TableColumn<MonthlyReportTrainer, String> tName;

    @FXML
    private TableColumn<MonthlyReportTrainer, Integer> lTime;

    @FXML
    private TableColumn<MonthlyReportTrainer, Integer> lDay;

    @FXML
    private TableColumn<MonthlyReportTrainer, Double> fee;
    
    @FXML
    private Rectangle titleTemplate;

    @FXML
    private Text titleMonthlyReport;

    @FXML
    private JFXDatePicker dateforData;

    @FXML
    private Circle dateCircle;

    @FXML
    private Text emptyNoti;

    @FXML
    private JFXButton printButton;
    
    @FXML
    private JFXButton clearButton;
    
    private int fineForDay;
    
    private int fineForLateTime;
    
    public void addFineData(int fineDay, int fineLateTime, String d){
        fineForDay = fineDay;
        fineForLateTime = fineLateTime;
        dateforData.setValue(LocalDate.parse(d));
    }
    
    CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis(0, 50, 10);
    LineChart<String, Number> mChart = new LineChart(xAxis, yAxis);

    @FXML
    void adminMenuMousePressedAction(MouseEvent event) {

    }
    
    public static void printNode(Node nodeToPrint) {
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            job.showPrintDialog(null);
            Printer printer = Printer.getDefaultPrinter();
            PageLayout layout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, MarginType.DEFAULT);

            if (job.printPage(nodeToPrint)) {
                job.endJob();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        dateforData.valueProperty().addListener((observable, oldValue, newValue) -> {
            printButton.setOpacity(1);
            clearButton.setOpacity(1);
        });
        
        lineGraph();
        tName.setCellValueFactory(new PropertyValueFactory<>("trainerName"));
        lTime.setCellValueFactory(new PropertyValueFactory<>("lateTime"));
        lDay.setCellValueFactory(new PropertyValueFactory<>("leaveDay"));
        fee.setCellValueFactory(new PropertyValueFactory<>("fees"));
        
//        trainerMonthlyReportTable.setItems(list);

//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM yyyy");
//        LocalDateTime now = LocalDateTime.now();
//        dateMonthlyReport.setText(dtf.format(now));
       
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
        mChart.getData().add(series);
        mChart.setMaxWidth(681);
        mChart.setMaxHeight(303);
        xAxis.setTickLabelFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 14));
        yAxis.setTickLabelFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 14));
        linegraph.getChildren().add(mChart);
    }

    @FXML
    void printPressedAction(MouseEvent event) {
        if (dateforData.getValue() != null) {
            
            printButton.setOpacity(0);
            clearButton.setOpacity(0);
            
            mChart.getData().remove(this);
            mChart.getData().clear();
            int year = dateforData.getValue().getYear();
            int month = dateforData.getValue().getMonthValue();
            int end_day = dateforData.getValue().lengthOfMonth();
            LocalDate s = LocalDate.now();
            XYChart.Series series = new XYChart.Series();
            series.setName("New Member");

            series.getData().add(new XYChart.Data<>("January", db.Membertable.new_member(1, year, s.withMonth(1).lengthOfMonth())));
            series.getData().add(new XYChart.Data<>("February", db.Membertable.new_member(2, year, s.withMonth(2).lengthOfMonth())));
            series.getData().add(new XYChart.Data<>("March", db.Membertable.new_member(3, year, s.withMonth(3).lengthOfMonth())));
            series.getData().add(new XYChart.Data<>("April", db.Membertable.new_member(4, year, s.withMonth(4).lengthOfMonth())));
            series.getData().add(new XYChart.Data<>("May", db.Membertable.new_member(5, year, s.withMonth(5).lengthOfMonth())));
            series.getData().add(new XYChart.Data<>("June", db.Membertable.new_member(6, year, s.withMonth(6).lengthOfMonth())));
            series.getData().add(new XYChart.Data<>("July", db.Membertable.new_member(7, year,s.withMonth(7).lengthOfMonth())));
            series.getData().add(new XYChart.Data<>("August", db.Membertable.new_member(8, year,s.withMonth(8).lengthOfMonth())));
            series.getData().add(new XYChart.Data<>("September", db.Membertable.new_member(9, year, s.withMonth(9).lengthOfMonth())));
            series.getData().add(new XYChart.Data<>("October", db.Membertable.new_member(10, year, s.withMonth(10).lengthOfMonth())));
            series.getData().add(new XYChart.Data<>("November", db.Membertable.new_member(11, year, s.withMonth(11).lengthOfMonth())));
            series.getData().add(new XYChart.Data<>("Decomber", db.Membertable.new_member(12, year, s.withMonth(12).lengthOfMonth())));

            mChart.getData().add(series);
            /*dateMonthlyReport.setText(String.format("%s %s", dateForData.getValue().getMonth(), dateForData.getValue().getYear()));
            numberOfNewMember.setText("New Member : " + db.Membertable.new_member(month, year, end_day));*/

            ArrayList<db.MonthlyReportTrainer> m = db.DailyAttendancetable.showAllTrainerMonthlyAtt(dateforData.getValue(), fineForLateTime, fineForDay);
            TMRtable.getItems().addAll(m);
            
            printNode(anchorPane);
            
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
    }

    public void dateCondition() {
        if (dateforData.getValue() == null) {
            fadeAnimationDate.setNode(dateCircle);
            fadeAnimationDate.play();
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
        tDate.start();
    }
    
    @FXML
    void clearPressedAction(MouseEvent event) {
        TMRtable.getItems().clear();
    }

}
