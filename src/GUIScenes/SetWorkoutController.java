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
import db.Member;
import db.Membertable;
import db.workout;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class SetWorkoutController implements Initializable {

    private FadeTransition fadeAnimationWorkoutName = new FadeTransition();
    private FadeTransition fadeAnimationEffectedMuscle = new FadeTransition();
    private FadeTransition fadeAnimationBurntCalorie = new FadeTransition();
    private FadeTransition fadeAnimationEmptyNoti = new FadeTransition();
    private FadeTransition fadeAnimationInvalidWorkoutName = new FadeTransition();
    private FadeTransition fadeAnimationInvalidEffectedMuscle = new FadeTransition();
    private FadeTransition fadeAnimationInvalidBurntCalorie = new FadeTransition();

    @FXML
    private Circle workoutNameCircle;

    @FXML
    private Circle effectedMuscleCircle;

    @FXML
    private Circle burntCalorieCircle;

    @FXML
    private Text emptyNoti;

    @FXML
    private Circle completeCircWorkoutName;

    @FXML
    private Circle completeCircEffectedMuscle;

    @FXML
    private Circle completeCircBurntCalorie;

    @FXML
    private Text invalidName;

    @FXML
    private Text invalidMuscle;

    @FXML
    private Text invalidCalorie;

    @FXML
    private JFXDrawer adminDrawer;

    @FXML
    private JFXHamburger adminMenu;

    @FXML
    private Rectangle titleTemplate;

    @FXML
    private Text titleRecordTrainerAtt;

    @FXML
    private AnchorPane mediaAnchorPane;

    @FXML
    private JFXButton chooseVideoButton;

    @FXML
    private JFXTextField txtFieldWorkoutName;

    @FXML
    private AnchorPane imageAnchorPane;

    @FXML
    private JFXButton chooseImageButton;

    @FXML
    private JFXTextField txtFieldEffectedMuscle;

    @FXML
    private JFXTextField txtFieldBurntCalorie;

    @FXML
    private JFXButton uploadButton;

    @FXML
    private JFXComboBox<String> exerciseChoice;

    private byte[] img;

    private File Vfile;
    
    
    private File Ifile;

    public void rawData(db.workout w) {
        txtFieldWorkoutName.setText(w.getName());
        exerciseChoice.setValue(w.getWorkout_type());
        txtFieldEffectedMuscle.setText(w.getEffectedMuscle());
        txtFieldBurntCalorie.setText(String.valueOf(w.getBurnt_calorie()));
    }

    @FXML
    void chooseImagePressedAction(MouseEvent event) {
        imageAnchorPane.setStyle("-fx-background-color : #c4ffc4;");
        imageAnchorPane.getChildren().clear();
    }

    @FXML
    void chooseImageReleasedAction(MouseEvent event) {
        try {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().addAll(new ExtensionFilter("PNG", "*.png"),
                    new ExtensionFilter("JPG", "*.jpg"),
                    new ExtensionFilter("JPEG", "*.jpeg"));
             Ifile = fc.showOpenDialog(null);
//            fc.setInitialDirectory(new File(Ifile.getParent()));

            if (Ifile != null) {
                Image image = new Image(Ifile.toURI().toString());
                img = Files.readAllBytes(Ifile.toPath());
                ImageView imgView = new ImageView(image);
                imgView.setFitHeight(304);
                imgView.setFitWidth(329);

                imageAnchorPane.setStyle("-fx-background-color : transparent;");
                imageAnchorPane.getChildren().add(imgView);
            }
        } catch (Exception e) {

        }

    }

    @FXML
    void chooseVideoPressedAction(MouseEvent event) {
        mediaAnchorPane.setStyle("-fx-background-color : #c4ffc4;");
        mediaAnchorPane.getChildren().clear();
    }

    @FXML
    void chooseVideoReleasedAction(MouseEvent event) throws InvocationTargetException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new ExtensionFilter("VIDEO", "*.mp4"));
        Vfile = fc.showOpenDialog(null);
        try {
//            fc.setInitialDirectory(new File(Vfile.getParent()));

            long fileSizeInBytes = Vfile.length();
            long fileSizeInKB = fileSizeInBytes / 1024;
            long fileSizeInMB = fileSizeInKB / 1024;

            if (Vfile != null && fileSizeInMB < 26) {
                Media media = new Media(Vfile.toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setAutoPlay(true);
                mediaPlayer.setMute(true);
                mediaPlayer.setCycleCount(Timeline.INDEFINITE);
                MediaView mediaView = new MediaView(mediaPlayer);
                mediaView.setFitHeight(409);
                mediaView.setFitWidth(530);

                mediaAnchorPane.setStyle("-fx-background-color : transparent;");
                mediaAnchorPane.getChildren().add(mediaView);

            } else {
                new Alert(AlertType.ERROR, "The file you have selected is too large. The maximum size is 25MB.", ButtonType.OK).showAndWait();
            }
        } catch (Exception e) {

        }
    }

    public void redCircleAnimation() {
        fadeAnimationItems(fadeAnimationWorkoutName);
        fadeAnimationItems(fadeAnimationEffectedMuscle);
        fadeAnimationItems(fadeAnimationBurntCalorie);
        fadeAnimationItems(fadeAnimationEmptyNoti);
    }

    public void invalidAnimation() {
        fadeAnimationItems(fadeAnimationInvalidWorkoutName);
        fadeAnimationItems(fadeAnimationInvalidEffectedMuscle);
        fadeAnimationItems(fadeAnimationInvalidBurntCalorie);
    }

    public void fadeAnimationItems(FadeTransition fadeAnimation) {
        fadeAnimation.setDuration(Duration.millis(4000));
        fadeAnimation.setCycleCount(1);
        fadeAnimation.setAutoReverse(false);
        fadeAnimation.setFromValue(1);
        fadeAnimation.setToValue(0);
    }

    public void invalidWorkoutNameCondition() {
        if (!Pattern.matches("[a-zA-Z\\s]+", txtFieldWorkoutName.getText())) {
            completeCircWorkoutName.setFill(Color.RED);
            fadeAnimationInvalidWorkoutName.setNode(invalidName);
            fadeAnimationInvalidWorkoutName.play();
        } else {
            completeCircWorkoutName.setFill(Color.GREEN);
        }
    }

    public void invalidEffectedMuscleCondition() {
        if (!Pattern.matches("[a-zA-Z\\s]+", txtFieldEffectedMuscle.getText())) {
            completeCircEffectedMuscle.setFill(Color.RED);
            fadeAnimationInvalidEffectedMuscle.setNode(invalidMuscle);
            fadeAnimationInvalidEffectedMuscle.play();
        } else {
            completeCircEffectedMuscle.setFill(Color.GREEN);
        }
    }

    public void invalidBurntCalorieCondition() {
        try {
            Float.parseFloat(txtFieldBurntCalorie.getText());
            completeCircBurntCalorie.setFill(Color.GREEN);
        } catch (NumberFormatException e) {
            completeCircBurntCalorie.setFill(Color.RED);
            fadeAnimationInvalidBurntCalorie.setNode(invalidCalorie);
            fadeAnimationInvalidBurntCalorie.play();
        }
//        if (!Pattern.matches("[a-zA-Z\\s]+", txtFieldBurntCalorie.getText())) {
//            completeCircBurntCalorie.setFill(Color.RED);
//            fadeAnimationInvalidBurntCalorie.setNode(invalidCalorie);
//            fadeAnimationInvalidBurntCalorie.play();
//        } else {
//            completeCircBurntCalorie.setFill(Color.GREEN);
//        }
    }

    public void workoutNameCondition() {
        if (txtFieldWorkoutName.getText().isEmpty()) {
            fadeAnimationWorkoutName.setNode(workoutNameCircle);
            fadeAnimationWorkoutName.play();
        }
    }

    public void effectedMuscleCondition() {
        if (txtFieldEffectedMuscle.getText().isEmpty()) {
            fadeAnimationEffectedMuscle.setNode(effectedMuscleCircle);
            fadeAnimationEffectedMuscle.play();
        }
    }

    public void burntCalorieCondition() {
        if (txtFieldBurntCalorie.getText().isEmpty()) {
            fadeAnimationBurntCalorie.setNode(burntCalorieCircle);
            fadeAnimationBurntCalorie.play();
        }
    }

    public void emptyNotiMethod() {
        fadeAnimationEmptyNoti.setNode(emptyNoti);
        fadeAnimationEmptyNoti.play();
    }

    public void emptyControllThreads() {
        Thread tWorkout = new Thread() {
            @Override
            public void run() {
                workoutNameCondition();
            }
        };
        Thread tEffected = new Thread() {
            @Override
            public void run() {
                effectedMuscleCondition();
            }
        };
        Thread tCalorie = new Thread() {
            @Override
            public void run() {
                burntCalorieCondition();
            }
        };
        tWorkout.start();
        try {
            tWorkout.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tEffected.start();
        try {
            tEffected.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tCalorie.start();
    }

    public void invalidControllThreads() {
        Thread tInvalidWorkoutName = new Thread() {
            @Override
            public void run() {
                invalidWorkoutNameCondition();
            }
        };
        Thread tInvalidEffected = new Thread() {
            @Override
            public void run() {
                invalidEffectedMuscleCondition();
            }
        };
        Thread tInvalidCalorie = new Thread() {
            @Override
            public void run() {
                invalidBurntCalorieCondition();
            }
        };
        tInvalidWorkoutName.start();
        try {
            tInvalidWorkoutName.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tInvalidEffected.start();
        try {
            tInvalidEffected.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tInvalidCalorie.start();
    }

    @FXML
    void uploadPressedAction(MouseEvent event) {
        if (!txtFieldWorkoutName.getText().isEmpty() && !txtFieldEffectedMuscle.getText().isEmpty() && !txtFieldBurntCalorie.getText().isEmpty()) {
            if (completeCircWorkoutName.getFill() == Color.RED || completeCircEffectedMuscle.getFill() == Color.RED
                    || completeCircBurntCalorie.getFill() == Color.RED) {
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
    void uploadReleasedAction(MouseEvent event) {
        if (!txtFieldWorkoutName.getText().isEmpty() && !txtFieldEffectedMuscle.getText().isEmpty() && !txtFieldBurntCalorie.getText().isEmpty()) {
            if (completeCircWorkoutName.getFill() == Color.GREEN && completeCircEffectedMuscle.getFill() == Color.GREEN
                    && completeCircBurntCalorie.getFill() == Color.GREEN) {

                Alert alertUpload = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to upload the data?", ButtonType.YES, ButtonType.NO);
                alertUpload.showAndWait();
                if (alertUpload.getResult() == ButtonType.YES) {
                    try {
                        //  System.err.println(new workout(txtFieldWorkoutName.getText(), file.toString(), exerciseChoice.getSelectionModel().getSelectedItem(), Float.valueOf(txtFieldBurntCalorie.getText()), txtFieldEffectedMuscle.getText(),getTitle(), img));
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        db.workout_table.insert_video(new workout(txtFieldWorkoutName.getText(), Vfile.toString(),
                                exerciseChoice.getSelectionModel().getSelectedItem(), Float.valueOf(txtFieldBurntCalorie.getText()),
                                txtFieldEffectedMuscle.getText(), window.getTitle(), img));

                        new Alert(AlertType.INFORMATION, "Data are uploaded", ButtonType.OK).showAndWait();
                        Parent trainerSignUpScene = FXMLLoader.load(getClass().getResource("SetWorkoutScene.fxml"));
                        Scene scene = new Scene(trainerSignUpScene);

                        window.setResizable(false);
                        window.setScene(scene);
                        window.show();
                    } catch (IOException ex) {
                        Logger.getLogger(MemberSignUpController.class.getName()).log(Level.SEVERE, null, ex);
                    }
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        exerciseChoice.getItems().addAll("Loose Weight", "Gain Weight", "Get Fitter");
        exerciseChoice.getSelectionModel().selectFirst();

        TrainerMyAccountController trainerMyAccControl = new TrainerMyAccountController();
        HamburgerBasicCloseTransition burgerTask = new HamburgerBasicCloseTransition(adminMenu);
        burgerTask.setRate(-1);
        adminMenu.setOnMousePressed((event) -> {
            try {
                adminDrawer.setSidePane(trainerMyAccControl.adminMenuScene(titleRecordTrainerAtt.getText()));
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
    void workoutNameAction(ActionEvent event) {
        txtFieldEffectedMuscle.requestFocus();
        txtFieldEffectedMuscle.selectAll();
    }

    @FXML
    void effectedMuscleAction(ActionEvent event) {
        txtFieldBurntCalorie.requestFocus();
        txtFieldBurntCalorie.selectAll();
    }

   
}
