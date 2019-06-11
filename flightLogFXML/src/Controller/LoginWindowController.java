package Controller;

import Model.LoginThread;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class LoginWindowController implements Initializable, Observer {

    //region FIELDS
    Thread thread;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;


    //endregion

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    void loginButtonHandler(ActionEvent event) {
        LoginThread lt = LoginThread.getInstance();
        lt.addObserver(this);
        thread = new Thread(lt);
        lt.setLoginData(usernameTextField.getText(), passwordField.getText());
        thread.start();
    }

    /*
    @FXML
    void onKeyPressed(KeyEvent event) {
        System.out.println("Key pressed");
        System.out.println(event.getText());
        if(event.getCode() == KeyCode.ENTER){
            LoginThread lt = LoginThread.getInstance();
            lt.addObserver(this);
            thread = new Thread(lt);
            lt.setLoginData(usernameTextField.getText(), passwordField.getText());
            thread.start();
            event.consume();
        }

    }
    */
    @Override
    public void update(Observable o, Object arg) {
        if(!arg.equals("")){
            thread.interrupt();
            Platform.runLater(() ->{
                Stage s = (Stage)usernameTextField.getScene().getWindow();
                s.close();
            });

        }
        else{
            Platform.runLater(() ->{
                Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                al.setTitle("Login Error");
                al.setContentText("Falscher Username/Passwort");
                al.show();
            });

        }
    }
}
