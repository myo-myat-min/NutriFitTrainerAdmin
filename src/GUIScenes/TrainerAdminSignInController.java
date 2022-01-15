package GUIScenes;

//import database.Member;
//import database.Membertable;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TrainerAdminSignInController extends Stage implements Initializable {

    @FXML
    private Rectangle welcomeTemplate;

    @FXML
    private Text txtWelcome;

    @FXML
    private Text txtNutriFit;

    @FXML
    private Rectangle titleTemplate;

    @FXML
    private Text titleTrainerSignUp;

    @FXML
    private JFXButton trainerSignIn;

    @FXML
    private JFXButton adminSignIn;
    
    AdminSignInController adminSignInControl = new AdminSignInController();

    @FXML
    void adminSignInButtonAction(ActionEvent event) {
        try {
            Parent memberSignUpScene = FXMLLoader.load(getClass().getResource("AdminSignInScene.fxml"));
            Scene scene = new Scene(memberSignUpScene, 1360, 700);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setResizable(false);
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(TrainerAdminSignInController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void trainerSignInButtonAction(ActionEvent event) {
        try {
            Parent memberSignUpScene = FXMLLoader.load(getClass().getResource("TrainerSignInScene.fxml"));
            Scene scene = new Scene(memberSignUpScene, 1360, 700);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setResizable(false);
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(TrainerAdminSignInController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}
