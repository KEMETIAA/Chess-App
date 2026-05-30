package Backend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {


    public void start(Stage primaryStage) {
        Parent  root;
        try {
            root = FXMLLoader.load(getClass().getResource("/Screens/MainMenu.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene MainMenu = new Scene(root);
        primaryStage.setTitle("Chess");
        primaryStage.setScene(MainMenu);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
