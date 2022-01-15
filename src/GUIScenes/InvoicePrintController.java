/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIScenes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class InvoicePrintController implements Initializable {
    
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private Label label;

    @FXML
    private JFXTextField name;

    @FXML
    private Label adminName;

    @FXML
    private JFXTextField fees;

    @FXML
    private JFXTextField pack;

    @FXML
    private JFXTextField expireddate;

    @FXML
    private JFXTextField date;
    
    @FXML
    private JFXButton printButton;

    @FXML
    void printPressedAction(MouseEvent event) {
        printButton.setOpacity(0);
        MonthlyReportPrintController.printNode(anchorPane);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ArrayList<db.invoice> a = db.invoice_table.showLastRow();
        
        name.setText(a.get(0).getName());
        adminName.setText(a.get(0).getAdmin_name());
        fees.setText(a.get(0).getPrice());
        pack.setText(a.get(0).getMembership_name());
        expireddate.setText(String.valueOf(a.get(0).getEnd()));
        date.setText(String.valueOf(a.get(0).getInvoice_date()));
    }    
    
}
