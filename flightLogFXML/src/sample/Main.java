package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Stage stage = new Stage();
        Parent root = null;
        URL url = new File("src/view/masterWindow.fxml").toURI().toURL();
        root = FXMLLoader.load(url);
        stage.setTitle("flightLog");
        stage.getIcons().add(new Image(Paths.get("src/view/drone.png").toUri().toURL().toString()));
        stage.setResizable(false);
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
