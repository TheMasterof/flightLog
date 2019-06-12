package Controller;

import DB.Database;
import Model.LoginThread;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class MasterWindowController implements Initializable, Observer {

    //region FIELDS
    @FXML
    private Button addFlightButton;

    @FXML
    private Button showLogButton;

    @FXML
    private Button addDroneButton;

    @FXML
    private Button loginButton;

    @FXML
    private TextField parameterTextField;

    @FXML
    private Button showDrones;

    @FXML
    private Label messageLabel;

    private int loginWindowWidth = 300;
    private int loginWindowHeight = 300;
    private int addWindowWidth = 600;
    private int addWindowHeight = 400;
    private int showLogWindowWidth = 1000;
    private int showLogWindowHeight = 600;
    private int showDronesWindowWidth = 1000;
    private int showDronesWindowHeight = 600;
    private int addDroneWindowWidth = 300;
    private int addDroneWindowHeight = 400;

    //endregion

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        changeButtonsDisable();

    }

    public void changeButtonsDisable(){
        addFlightButton.setDisable(!addFlightButton.isDisabled());
        addDroneButton.setDisable(!addDroneButton.isDisabled());
    }

    @FXML
    void addDroneButtonHandler(ActionEvent event) {
        try {
            Stage stage = new Stage();
            Parent root;
            // für .jar file:    URL url = new File("flightLogFXML/src/view/AddDroneWindow.fxml").toURI().toURL();
            URL url = new File("src/view/AddDroneWindow.fxml").toURI().toURL();
            root = FXMLLoader.load(url);
            stage.setTitle("Add Drone");
            stage.setScene(new Scene(root, addDroneWindowWidth, addDroneWindowHeight));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void addFlightButtonHandler(ActionEvent event) {

        try {
            Stage stage = new Stage();
            Parent root;
            // für .jar file:    URL url = new File("flightLogFXML/src/view/AddWindow.fxml").toURI().toURL();
            URL url = new File("src/view/AddWindow.fxml").toURI().toURL();
            root = FXMLLoader.load(url);
            stage.setTitle("Add Flight");
            stage.setScene(new Scene(root, addWindowWidth, addWindowHeight));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void parameterTextFieldHandler(ActionEvent event) {

    }

    @FXML
    void showLogButtonHandler(ActionEvent event) {
        String param = parameterTextField.getText();
        Boolean containsOnlyDigits = true;
        if(!param.equals("")){
            for (int i = 0; i < param.length(); i++) {
                if(!Character.isDigit(param.charAt(i))){
                    containsOnlyDigits = false;
                }
            }
            if(containsOnlyDigits){
                if(Database.getInstance().checkIfDroneExists(Integer.parseInt(param))){
                    Database.getInstance().setCurrentDroneID(Integer.parseInt(param));
                    openShowLogWindow();
                }
                else{
                    Alert al1 = new Alert(Alert.AlertType.CONFIRMATION);
                    al1.setTitle("Drone not Found");
                    al1.setContentText("The given droneID does not exist");
                    al1.show();
                }
            }
            else{
                Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                al.setTitle("Wrong Parameter");
                al.setContentText("The given Drone-ID is of wrong type");
                al.show();
            }
        }
        else{
            Database.getInstance().setCurrentDroneID(-1);
            openShowLogWindow();
        }

    }

    private void openShowLogWindow(){
        try {
            Stage stage = new Stage();
            Parent root = null;
            // für .jar file:   URL url = new File("flightLogFXML/src/view/showLogWindow.fxml").toURI().toURL();
            URL url = new File("src/view/showLogWindow.fxml").toURI().toURL();

            root = FXMLLoader.load(url);
            stage.setTitle("Logs");
            stage.setScene(new Scene(root, showLogWindowWidth, showLogWindowHeight));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loginButtonHandler(ActionEvent actionEvent) {
        if(loginButton.getText().equals("Logout")){
            changeButtonsDisable();
            Database.getInstance().setCurrentUser(null);
            messageLabel.setText("");
            loginButton.setText("Login");
        }
        else{
            try {
                LoginThread lt = LoginThread.getInstance();
                lt.addObserver(this);
                Thread t = new Thread(lt);

                Stage stage = new Stage();
                // für .jar file:   URL url = new File("flightLogFXML/src/view/loginWindow.fxml").toURI().toURL();
                URL url = new File("src/view/loginWindow.fxml").toURI().toURL();

                Parent root = FXMLLoader.load(url);
                stage.setTitle("Login");
                stage.setScene(new Scene(root, loginWindowWidth, loginWindowHeight));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void showDronesHandler(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            Parent root = null;
            // für .jar file:    URL url = new File("flightLogFXML/src/view/showDronesWindow.fxml").toURI().toURL();
            URL url = new File("src/view/showDronesWindow.fxml").toURI().toURL();

            root = FXMLLoader.load(url);
            stage.setTitle("Drones");
            stage.setScene(new Scene(root, showDronesWindowWidth, showDronesWindowHeight));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if(!arg.equals("")){
            Database.getInstance().setCurrentUser((String)arg);

            Platform.runLater(() -> {
                changeButtonsDisable();
                loginButton.setText("Logout");
                messageLabel.setText("Hello " + Database.getInstance().getUsersName((String)arg));
            });
        }

    }
}
