package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class AddWindowController implements Initializable {
    //region Fields
    @FXML
    private Label timeLabel;

    @FXML
    private Label logIDLabel;

    @FXML
    private Label droneIDLabel;

    @FXML
    private TextArea descriptionTextArea;

    //endregion


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
