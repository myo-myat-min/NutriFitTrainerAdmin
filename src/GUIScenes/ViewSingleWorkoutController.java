/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIScenes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import db.workout;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author ACER
 */
public class ViewSingleWorkoutController implements Initializable {

//    @FXML
//    private JFXDrawer adminDrawer;
//
//    @FXML
//    private JFXHamburger adminMenu;

    @FXML
    private Circle completeCircWorkoutName;

    @FXML
    private Circle completeCircEffectedMuscle;

    @FXML
    private Circle completeCircBurntCalorie;

    @FXML
    private Rectangle titleTemplate;

    @FXML
    private Text titleRecordTrainerAtt;

    @FXML
    private AnchorPane mediaAnchorPane;

    @FXML
    private JFXTextField txtFieldWorkoutName;

    @FXML
    private AnchorPane imageAnchorPane;

    @FXML
    private JFXTextField txtFieldEffectedMuscle;
    @FXML
    private JFXTextField txtFieldBurntCalorie;

    @FXML
    private Circle workoutNameCircle;

    @FXML
    private Circle effectedMuscleCircle;

    @FXML
    private Circle burntCalorieCircle;

    @FXML
    private Text emptyNoti;

    @FXML
    private Text invalidName;

    @FXML
    private Text invalidMuscle;

    @FXML
    private Text invalidCalorie;
    
    @FXML
    private SVGPath backArrowShape;

    @FXML
    private JFXButton deleteButton;
    
    private String dataAll = "Nothing";
    
    public void filterForAll(String all){
        dataAll = all;
    }

    @FXML
    private JFXComboBox<String> exerciseChoice = new JFXComboBox<>(FXCollections.observableArrayList("Loose Weight", "Gain Weight", "Get Fitter"));

    private static workout w;

    public void addingdata(workout t) {
        this.w = t;
        this.exerciseChoice.getSelectionModel().select(t.getWorkout_type());
        this.txtFieldWorkoutName.setText(t.getWorkout_name());
        this.txtFieldEffectedMuscle.setText(t.getEffectedMuscle());
        this.txtFieldBurntCalorie.setText(String.valueOf(t.getBurnt_calorie()));

        Media media = new Media(new File(w.getWorkout_video()).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaPlayer.setMute(true);
        mediaPlayer.setAutoPlay(true);
        mediaView.setFitHeight(mediaAnchorPane.getHeight());
        mediaView.setFitWidth(mediaAnchorPane.getWidth());
        mediaPlayer.setCycleCount(Timeline.INDEFINITE);
        mediaAnchorPane.getChildren().add(mediaView);
        mediaAnchorPane.setStyle("-fx-background-color : background;");

        Image image = new Image(new ByteArrayInputStream(t.getImage()));
        ImageView imgView = new ImageView(image);
        System.out.println(imageAnchorPane.getHeight() + "asa" + imageAnchorPane.getWidth());
        imgView.setFitHeight(imageAnchorPane.getHeight());
        imgView.setFitWidth(imageAnchorPane.getWidth());

        imageAnchorPane.setStyle("-fx-background-color : transparent;");
        imageAnchorPane.getChildren().add(imgView);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        exerciseChoice.setOpacity(1);
        exerciseChoice.getItems().addAll("Loose Weight", "Gain Weight", "Get Fitter");
        exerciseChoice.getSelectionModel().selectFirst();

    }
    
    @FXML
    void backArrowMouseEnteredAction(MouseEvent event) {
        backArrowShape.setFill(Color.web("#c4ffc4"));
    }

    @FXML
    void backArrowMouseExitedAction(MouseEvent event) {
        backArrowShape.setFill(Color.WHITE);
    }

    @FXML
    void backArrowMousePressedAction(MouseEvent event) {
        backArrowShape.setFill(Color.web("#35ae50"));
    }

    @FXML
    void backArrowMouseReleasedAction(MouseEvent event) {
        if (exerciseChoice.getValue().equals("Loose Weight") && !dataAll.equals("all")) {
            try {
                Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("LooseWeightWorkoutScene.fxml"));
                Scene scene = new Scene(trainerSignUpScene, 1360, 700);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setResizable(false);
                window.setScene(scene);
                window.show();
            } catch (IOException ex) {
                Logger.getLogger(AdminSignInController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (exerciseChoice.getValue().equals("Gain Weight") && !dataAll.equals("all")) {
            try {
                Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("GainWeightWorkoutScene.fxml"));
                Scene scene = new Scene(trainerSignUpScene, 1360, 700);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setResizable(false);
                window.setScene(scene);
                window.show();
            } catch (IOException ex) {
                Logger.getLogger(AdminSignInController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (exerciseChoice.getValue().equals("Get Fitter") && !dataAll.equals("all")) {
            try {
                Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("GetFitterWorkoutScene.fxml"));
                Scene scene = new Scene(trainerSignUpScene, 1360, 700);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setResizable(false);
                window.setScene(scene);
                window.show();
            } catch (IOException ex) {
                Logger.getLogger(AdminSignInController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("AllWorkoutScene.fxml"));
                Scene scene = new Scene(trainerSignUpScene, 1360, 700);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setResizable(false);
                window.setScene(scene);
                window.show();
            } catch (IOException ex) {
                Logger.getLogger(AdminSignInController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FXML
    void deletePressedAction(MouseEvent event) {
        Stage window1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {

            if (!w.getEmail().equals(window1.getTitle())) {
                new Alert(Alert.AlertType.ERROR, "You have no permission to delete this video.", ButtonType.OK).showAndWait();
            } else {
                try {
                    
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to update the video", ButtonType.YES, ButtonType.NO);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        db.workout_table.delete_video(w.getId());
                        if (exerciseChoice.getValue().equals("Loose Weight") && !dataAll.equals("all")) {
                            try {
                                Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("LooseWeightWorkoutScene.fxml"));
                                Scene scene = new Scene(trainerSignUpScene, 1360, 700);
                                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                window.setResizable(false);
                                window.setScene(scene);
                                new Alert(Alert.AlertType.INFORMATION, "Video is deleted", ButtonType.OK).showAndWait();
                                window.show();
                            } catch (IOException ex) {
                                Logger.getLogger(AdminSignInController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else if (exerciseChoice.getValue().equals("Gain Weight") && !dataAll.equals("all")) {
                            try {
                                Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("GainWeightWorkoutScene.fxml"));
                                Scene scene = new Scene(trainerSignUpScene, 1360, 700);
                                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                window.setResizable(false);
                                window.setScene(scene);
                                new Alert(Alert.AlertType.INFORMATION, "Video is deleted", ButtonType.OK).showAndWait();
                                window.show();
                            } catch (IOException ex) {
                                Logger.getLogger(AdminSignInController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else if (exerciseChoice.getValue().equals("Get Fitter") && !dataAll.equals("all")) {
                            try {
                                Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("GetFitterWorkoutScene.fxml"));
                                Scene scene = new Scene(trainerSignUpScene, 1360, 700);
                                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                window.setResizable(false);
                                window.setScene(scene);
                                new Alert(Alert.AlertType.INFORMATION, "Video is deleted", ButtonType.OK).showAndWait();
                                window.show();
                            } catch (IOException ex) {
                                Logger.getLogger(AdminSignInController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            try {
                                Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("AllWorkoutScene.fxml"));
                                Scene scene = new Scene(trainerSignUpScene, 1360, 700);
                                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                window.setResizable(false);
                                window.setScene(scene);
                                new Alert(Alert.AlertType.INFORMATION, "Video is deleted", ButtonType.OK).showAndWait();
                                window.show();
                            } catch (IOException ex) {
                                Logger.getLogger(AdminSignInController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } else {
                        event.consume();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (NullPointerException e) {
            new Alert(Alert.AlertType.ERROR, "Please select a row", ButtonType.OK).showAndWait();
        }

    }
}
//new Alert(Alert.AlertType.INFORMATION, "Video delete", ButtonType.OK).showAndWait();