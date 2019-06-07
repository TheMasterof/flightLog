package Controller;

import DB.Database;
import Model.LoginThread;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private String currentUserID;

    //endregion

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        changeButtonsDisable();

    }

    public void changeButtonsDisable(){
        addFlightButton.setDisable(!addFlightButton.isDisabled());
        showDrones.setDisable(!showDrones.isDisabled());

    }

    @FXML
    void addFlightButtonHandler(ActionEvent event) {

        try {
            Stage stage = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../View/AddWindow.fxml"));
            stage.setTitle("Add Flight");
            stage.setScene(new Scene(root, loginWindowWidth, loginWindowHeight));
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

    }

    public void loginButtonHandler(ActionEvent actionEvent) {
        try {
            LoginThread lt = LoginThread.getInstance();
            System.out.println(lt);
            System.out.println(this);
            lt.addObserver(this);
            Thread t = new Thread(lt);

            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../View/loginWindow.fxml"));
            stage.setTitle("Login");
            stage.setScene(new Scene(root, loginWindowWidth, loginWindowHeight));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showDronesHandler(ActionEvent actionEvent) {
    }

    @Override
    public void update(Observable o, Object arg) {
        if(!arg.equals("")){
            currentUserID = (String)arg;
            changeButtonsDisable();
            Platform.runLater(() -> {
                messageLabel.setText("Hello " + Database.getInstance().getUsersName((String)arg));
            });
        }

    }
}
