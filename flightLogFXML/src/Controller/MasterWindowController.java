package Controller;

import Model.LoginThread;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    private int loginScreenWidth = 300;
    private int loginScreenHeight = 300;

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
            stage.setScene(new Scene(root, loginScreenWidth, loginScreenHeight));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showDronesHandler(ActionEvent actionEvent) {
    }

    @Override
    public void update(Observable o, Object arg) {
        if((Boolean)arg){
            changeButtonsDisable();
        }

    }
}
