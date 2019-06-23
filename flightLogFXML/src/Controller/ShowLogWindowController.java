package Controller;

import DB.Database;
import Model.LogEntry;
import Model.LoginThread;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sun.rmi.runtime.Log;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class ShowLogWindowController implements Initializable, Observer {

    //region FIELDS
    @FXML
    private TableView<LogEntry> logTableView;

    @FXML
    private TableColumn<LogEntry, Integer> logIDCol;

    @FXML
    private TableColumn<LogEntry, Integer> droneIDCol;

    @FXML
    private TableColumn<LogEntry, String> userIDCol;

    @FXML
    private TableColumn<LogEntry, String> descriptionCol;

    @FXML
    private TableColumn<LogEntry, Timestamp> timeOfFlightCol;

    private List<LogEntry> entries;

    @FXML
    private Button addFlightButton;

    @FXML
    private Button showLogButton;

    @FXML
    private Button addDroneButton;

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
    private int showLogWindowWidth = 1000;
    private int showLogWindowHeight = 600;
    private int showDronesWindowWidth = 1000;
    private int showDronesWindowHeight = 600;
    private int addDroneWindowWidth = 300;
    private int addDroneWindowHeight = 400;

    //endregion

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        changeButtonsDisable();
        logIDCol.setCellValueFactory(new PropertyValueFactory("id"));
        droneIDCol.setCellValueFactory(new PropertyValueFactory("droneID"));
        userIDCol.setCellValueFactory(new PropertyValueFactory("userID"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory("description"));
        timeOfFlightCol.setCellValueFactory(new PropertyValueFactory("timeOfFlight"));
        if(Database.getInstance().getCurrentDroneID() == -1){
            entries = Database.getInstance().getLogEntries();
        }
        else{
            entries = Database.getInstance().getLogEntriesOfDrone(Database.getInstance().getCurrentDroneID());
        }
        logTableView.setItems(FXCollections.observableArrayList(entries));
    }

    public void changeButtonsDisable(){
        addFlightButton.setDisable(!addFlightButton.isDisabled());
        addDroneButton.setDisable(!addDroneButton.isDisabled());
    }

    @FXML
    void addDroneButtonHandler(ActionEvent event) {
        try {
            Stage stage = new Stage();
            Parent root;
            // für .jar file:    URL url = new File("flightLogFXML/src/view/AddDroneWindow.fxml").toURI().toURL();
            URL url = new File("src/view/AddDroneWindow.fxml").toURI().toURL();
            root = FXMLLoader.load(url);
            stage.setTitle("Add Drone");
            stage.getIcons().add(new Image(Paths.get("src/view/drone.png").toUri().toURL().toString()));
            stage.setScene(new Scene(root, addDroneWindowWidth, addDroneWindowHeight));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void addFlightButtonHandler(ActionEvent event) {

        try {
            Stage stage = new Stage();
            Parent root;
            // für .jar file:    URL url = new File("flightLogFXML/src/view/AddWindow.fxml").toURI().toURL();
            URL url = new File("src/view/AddWindow.fxml").toURI().toURL();
            root = FXMLLoader.load(url);
            stage.setTitle("Add Flight");
            stage.getIcons().add(new Image(Paths.get("src/view/drone.png").toUri().toURL().toString()));
            stage.setScene(new Scene(root, addWindowWidth, addWindowHeight));
            stage.showAndWait();
            logTableView.setItems(FXCollections.observableArrayList(Database.getInstance().getLogEntries()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void parameterTextFieldHandler(ActionEvent event) {

    }



    public void loginButtonHandler(ActionEvent actionEvent) {
        if(loginButton.getText().equals("Logout")){
            changeButtonsDisable();
            Database.getInstance().setCurrentUser(null);
            messageLabel.setText("");
            loginButton.setText("Login");
        }
        else{
            try {
                LoginThread lt = LoginThread.getInstance();
                lt.addObserver(this);
                Thread t = new Thread(lt);

                Stage stage = new Stage();
                // für .jar file:   URL url = new File("flightLogFXML/src/view/loginWindow.fxml").toURI().toURL();
                URL url = new File("src/view/loginWindow.fxml").toURI().toURL();

                Parent root = FXMLLoader.load(url);
                stage.setTitle("Login");
                stage.getIcons().add(new Image(Paths.get("src/view/drone.png").toUri().toURL().toString()));
                stage.setScene(new Scene(root, loginWindowWidth, loginWindowHeight));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void showDronesHandler(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            Parent root = null;
            // für .jar file:    URL url = new File("flightLogFXML/src/view/showDronesWindow.fxml").toURI().toURL();
            URL url = new File("src/view/showDronesWindow.fxml").toURI().toURL();

            root = FXMLLoader.load(url);
            stage.setTitle("Drones");
            stage.getIcons().add(new Image(Paths.get("src/view/drone.png").toUri().toURL().toString()));
            stage.setScene(new Scene(root, showDronesWindowWidth, showDronesWindowHeight));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if(!arg.equals("")){
            Database.getInstance().setCurrentUser((String)arg);

            Platform.runLater(() -> {
                changeButtonsDisable();
                loginButton.setText("Logout");
                messageLabel.setText("Hello " + Database.getInstance().getUsersName((String)arg));
            });
        }

    }
}