package Controller;

import DB.Database;
import Model.Drone;
import Model.LogEntry;
import javafx.application.Platform;
import javafx.collections.FXCollections;
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
import java.util.stream.Collectors;

public class AddWindowController implements Initializable {
    //region Fields
    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private ChoiceBox<Integer> droneIDChoiceBox;

    @FXML
    private DatePicker flightDatePicker;


    @FXML
    private Button addFlightButton;


    //endregion


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        droneIDChoiceBox.setItems(FXCollections.observableList(Database.getInstance().getDrones().stream().map(Drone::getId).collect(Collectors.toList())));
    }


    @FXML
    void addFlightButtonHandler(ActionEvent event) {
        if(flightDatePicker.getValue() == null || droneIDChoiceBox.getValue() == null){
            Alert al = new Alert(Alert.AlertType.CONFIRMATION);
            al.setTitle("Falsche Parameter");
            al.setContentText("Kein Datum/Keine Drohne gegeben");
            al.show();
        }
        else{
            Boolean containsOnlyDigits = true;
            LocalDate l = flightDatePicker.getValue();
            Timestamp t = Timestamp.valueOf(l.atStartOfDay());
            int droneID = droneIDChoiceBox.getValue();
            Database.getInstance().addLogEntry(new LogEntry(droneID, Database.getInstance().getCurrentUser(), descriptionTextArea.getText(), t));
            Platform.runLater(() ->{
                Stage s = (Stage)droneIDChoiceBox.getScene().getWindow();
                s.close();
            });
        }
    }
}
