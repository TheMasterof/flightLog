package Controller;

import DB.Database;
import Model.LogEntry;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.management.timer.TimerMBean;
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
                Database.getInstance().addLogEntry(new LogEntry(t, Integer.parseInt(droneIDTextField.getText()), descriptionTextArea.getText()));
            }
        }
    }
}
