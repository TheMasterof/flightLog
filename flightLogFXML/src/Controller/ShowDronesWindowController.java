package Controller;

import DB.Database;
import Model.Drone;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import javax.xml.crypto.Data;
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
    private TableColumn<Drone, String> availableCol;

    List<Drone> drones;

    //endregion

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(Database.getInstance().getCurrentUser() == null){
            dronesTableView.setEditable(false);
        }
        else{
            dronesTableView.setEditable(true);
        }

        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionCol.setCellValueFactory(new PropertyValueFactory("description"));
        descriptionCol.setCellFactory(TextFieldTableCell.forTableColumn());
        availableCol.setCellValueFactory(new PropertyValueFactory("available"));
        availableCol.setCellFactory(TextFieldTableCell.forTableColumn());
        availableCol.setOnEditCommit((TableColumn.CellEditEvent<Drone, String> t) -> {

            if(t.getNewValue().equals("yes")){
                t.getRowValue().setAvailable(true);
                Database.getInstance().updateDrone(t.getRowValue());
            }
            else if(t.getNewValue().equals("no")){
                t.getRowValue().setAvailable(false);
                Database.getInstance().updateDrone(t.getRowValue());
            }
            else{
                Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                al.setTitle("Wrong input");
                al.setContentText("Type in \"yes\" or \"no\"");
                al.show();
                t.getTableColumn().setText(t.getOldValue());
            }
        });

        drones = Database.getInstance().getDrones();
        dronesTableView.setItems(FXCollections.observableArrayList(drones));
    }


}
