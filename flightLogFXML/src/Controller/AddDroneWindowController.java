package Controller;

import DB.Database;
import Model.Drone;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddDroneWindowController implements Initializable {
    //region FIELDS
    @FXML
    private TextField droneIDTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextArea descriptionTextField;

    @FXML
    private Button addButton;

    //endregion


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void addButtonHandler(ActionEvent event) {
        Boolean containsOnlyDigits = true;
        String droneid = droneIDTextField.getText();
        for (int i = 0; i < droneid.length(); i++) {
            if(!Character.isDigit(droneid.charAt(i))){
                containsOnlyDigits = false;
            }
        }
        if (!containsOnlyDigits || droneid.equals("") || nameTextField.getText().equals("")){
            Alert al = new Alert(Alert.AlertType.CONFIRMATION);
            al.setTitle("Wrong Parameter");
            al.setContentText("DroneID or Name are wrong/empty");
            al.show();
        }
        else{
            if(Database.getInstance().checkIfDroneExists(Integer.parseInt(droneid))){
                Alert al1 = new Alert(Alert.AlertType.CONFIRMATION);
                al1.setTitle("Drone already exists");
                al1.setContentText("Drone cannot be added because it already exists");
                al1.show();
            }
            else{
                Database.getInstance().addDrone(new Drone(Integer.parseInt(droneid), nameTextField.getText(), descriptionTextField.getText()));
                Platform.runLater(() ->{
                    Stage s = (Stage)droneIDTextField.getScene().getWindow();
                    s.close();
                });
            }
        }
    }
}