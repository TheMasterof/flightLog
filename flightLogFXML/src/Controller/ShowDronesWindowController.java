package Controller;

import DB.Database;
import Model.Drone;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ShowDronesWindowController implements Initializable {

    //region FIELDS

    @FXML
    private TableView<Drone> dronesTableView;

    @FXML
    private TableColumn<Drone, Integer> idCol;

    @FXML
    private TableColumn<Drone, String> nameCol;

    @FXML
    private TableColumn<Drone, String> descriptionCol;

    @FXML
    private TableColumn<Drone, Boolean> availableCol;

    List<Drone> drones;

    //endregion

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory("description"));
        availableCol.setCellValueFactory(new PropertyValueFactory("available"));

        drones = Database.getInstance().getDrones();
        dronesTableView.setItems(FXCollections.observableArrayList(drones));
    }


}
