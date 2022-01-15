/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIScenes;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTimePicker;
import java.time.LocalTime;
import javafx.collections.FXCollections;

/**
 *
 * @author DELL
 */
public class RecordAttendanceTrainer extends db.trainer {

    String Trainer_id;
    JFXTimePicker arrived_time = new JFXTimePicker();
    String trainerName;
    JFXComboBox att = new JFXComboBox(FXCollections.observableArrayList("X", "O", "Late"));
    String attend;
    LocalTime Arrived_Time;
    String note;

//    {
//    
//        this.att.getSelectionModel().selectFirst();
//        this.arrived_time.setEditable(false);
//        this.arrived_time.setDisable(true);
//
//        this.att.valueProperty().addListener((ob, ov, nv) -> {
//            if (nv == "X" || nv == "(X)L") {
//
//                this.arrived_time.setDisable(true);
//                this.arrived_time = new JFXTimePicker();
//
//            } else {
//                this.arrived_time = new JFXTimePicker();
//                this.arrived_time.setDisable(false);
//            }
//        });
//
//    }
//    this.arrived_time.setValue(LocalTime.of(9, 0));
    public RecordAttendanceTrainer(String attend, LocalTime Arrived_Time, String note, String id, String name) {
        super(id, name);

        this.attend = attend;
        this.Arrived_Time = Arrived_Time;
        this.note = note;
        att.setValue(this.attend);

        if (this.att.getValue().equals("O")) {
            
            this.arrived_time.setValue(LocalTime.of(9, 0));
            this.arrived_time.setDisable(true);

        } else if(this.att.getValue().equals("X")) {

            this.arrived_time.setValue(LocalTime.of(17, 0));
            this.arrived_time.setDisable(true);
            
        } else {

            this.arrived_time.setValue(LocalTime.of(9, 0));
            this.arrived_time.setDisable(false);
            
        }

        this.att.valueProperty().addListener((ob, ov, nv) -> {

            if (nv.equals("O")) {
                
                this.arrived_time.setValue(LocalTime.of(9, 0));
                this.arrived_time.setDisable(true);

            } else if (nv.equals("X")) {

                this.arrived_time.setValue(LocalTime.of(17, 0));
                this.arrived_time.setDisable(true);

            } else {

                this.arrived_time.setValue(LocalTime.of(9, 0));
                this.arrived_time.setDisable(false);
                
            }
        });

        if (Arrived_Time != null) {
            arrived_time.setValue(Arrived_Time);
        } else {
//            arrived_time = new JFXTimePicker();
            this.arrived_time.setValue(LocalTime.of(17, 0));
        }

    }

    public RecordAttendanceTrainer(String id, String name) {
        super(id, name);

        this.att.getSelectionModel().selectFirst();
//        this.arrived_time.setEditable(false);
//        this.arrived_time.setDisable(true);
//        this.arrived_time.setValue(LocalTime.of(9, 0));

        if (this.att.getValue().equals("O")) {
            
            this.arrived_time.setValue(LocalTime.of(9, 0));
            this.arrived_time.setDisable(true);

        } else if(this.att.getValue().equals("X")) {

            this.arrived_time.setValue(LocalTime.of(17, 0));
            this.arrived_time.setDisable(true);
            
        } else {

            this.arrived_time.setValue(LocalTime.of(9, 0));
            this.arrived_time.setDisable(false);
            
        }

        this.att.valueProperty().addListener((ob, ov, nv) -> {

            if (nv.equals("O")) {
                
                this.arrived_time.setValue(LocalTime.of(9, 0));
                this.arrived_time.setDisable(true);

            } else if (nv.equals("X")) {

                this.arrived_time.setValue(LocalTime.of(17, 0));
                this.arrived_time.setDisable(true);

            } else {

                this.arrived_time.setValue(LocalTime.of(9, 0));
                this.arrived_time.setDisable(false);
                
            }
        });
    }

    public String getTrainer_id() {
        return Trainer_id;
    }

    public void setTrainer_id(String Trainer_id) {
        this.Trainer_id = Trainer_id;
    }

    public JFXTimePicker getArrived_time() {
        return arrived_time;
    }

    public void setArrived_time(JFXTimePicker arrived_time) {
        this.arrived_time = arrived_time;
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

    public String getAttend() {
        return attend;
    }

    public void setAttend(String attend) {
        this.attend = attend;
    }

    public LocalTime getArrived_Time() {
        return Arrived_Time;
    }

    public void setArrived_Time(LocalTime Arrived_Time) {
        this.Arrived_Time = Arrived_Time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "RecordAttendanceTrainer{" + "Trainer_id=" + Trainer_id + ", arrived_time=" + arrived_time + ", trainerName=" + trainerName + ", att=" + att + ", attend=" + attend + ", Arrived_Time=" + Arrived_Time + ", note=" + note + '}';
    }

}
