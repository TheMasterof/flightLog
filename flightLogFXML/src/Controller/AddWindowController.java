package Controller;

import DB.Database;
import Model.LogEntry;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.management.timer.TimerMBean;
import javax.xml.crypto.Data;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class AddWindowController implements Initializable {
    //region Fields
    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private DatePicker flightDatePicker;

    @FXML
    private TextField droneIDTextField;

    @FXML
    private Button addFlightButton;


    //endregion


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    void addFlightButtonHandler(ActionEvent event) {
        if(flightDatePicker.getValue() == null || droneIDTextField.getText().equals("")){
            Alert al = new Alert(Alert.AlertType.CONFIRMATION);
            al.setTitle("Falsche Parameter");
            al.setContentText("Kein Datum/Keine Drohne gegeben");
            al.show();
        }
        else{
            Boolean containsOnlyDigits = true;
            LocalDate l = flightDatePicker.getValue();
            Timestamp t = Timestamp.valueOf(l.atStartOfDay());
            String droneID = droneIDTextField.getText();
            for (int i = 0; i < droneID.length(); i++) {
                if(!Character.isDigit(droneID.charAt(i))){
                    containsOnlyDigits = false;
                    Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                    al.setTitle("Falsche Parameter");
                    al.setContentText("Drone-ID ist keine Zahl");
                    al.show();
                }
            }
            if(containsOnlyDigits){
                int droneIDInt = Integer.parseInt(droneIDTextField.getText());
                if(!Database.getInstance().checkIfDroneExists(droneIDInt)){
                    Alert al1 = new Alert(Alert.AlertType.CONFIRMATION);
                    al1.setTitle("Drone does not exist");
                    al1.setContentText("The given DroneID does not exist");
                    al1.show();
                }
                else{
                    Database.getInstance().addLogEntry(new LogEntry(droneIDInt, Database.getInstance().getCurrentUser(), descriptionTextArea.getText(), t));
                    Platform.runLater(() ->{
                        Stage s = (Stage)droneIDTextField.getScene().getWindow();
                        s.close();
                    });
                }

            }
        }
    }
}
