package Controller;

import DB.Database;
import Model.LogEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;

public class ShowLogWindowController implements Initializable {

    //region FIELDS
    @FXML
    private TableView<LogEntry> logTableView;

    @FXML
    private TableColumn<LogEntry, Integer> logIDCol;

    @FXML
    private TableColumn<LogEntry, Integer> droneIDCol;

    @FXML
    private TableColumn<LogEntry, String> descriptionCol;

    @FXML
    private TableColumn<LogEntry, Timestamp> timeOfFlightCol;

    private List<LogEntry> entries;

    //endregion

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logIDCol.setCellValueFactory(new PropertyValueFactory("id"));
        droneIDCol.setCellValueFactory(new PropertyValueFactory("droneID"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory("description"));
        timeOfFlightCol.setCellValueFactory(new PropertyValueFactory("timeOfFlight"));
        entries = Database.getInstance().getLogEntries();
        ObservableList<LogEntry> obs = FXCollections.observableArrayList(entries);
        logTableView.setItems(obs);
    }
}