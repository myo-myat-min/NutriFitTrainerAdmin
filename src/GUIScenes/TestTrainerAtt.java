/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIScenes;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
/**
 *
 * @author ACER
 */
public class TestTrainerAtt {
    int id;
    String trainerName, reason;
    JFXComboBox att;
    JFXTimePicker lateHour;

    public TestTrainerAtt(int id, String trainerName, String reason) {
        this.id = id;
        this.trainerName = trainerName;
        this.att = new JFXComboBox(FXCollections.observableArrayList("X", "O", "Late"));
        this.att.getSelectionModel().selectFirst();
        this.lateHour = new JFXTimePicker();
        this.lateHour.setEditable(false);
        this.lateHour.setDisable(true);
        this.att.valueProperty().addListener((ob, ov, nv) -> {
            if (nv == "X") {
                this.lateHour.setDisable(true);
            } else {
                this.lateHour.setDisable(false);
            }
        });
        this.reason = reason;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public JFXComboBox getAtt() {
        return att;
    }

    public void setAtt(JFXComboBox att) {
        this.att = att;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public JFXTimePicker getLateHour() {
        return lateHour;
    }

    public void setLateHour(JFXTimePicker lateHour) {
        this.lateHour = lateHour;
    }

}
