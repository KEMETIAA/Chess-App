
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {


    public void start(Stage primaryStage) {
        Label titleLabel = new Label("Welcome to JavaFX!");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, (double)24.0F));
        Label messageLabel = new Label("Click the button below to see magic.");
        messageLabel.setFont(Font.font("Arial", (double)14.0F));
        Button actionButton = new Button("Click Me!");
        actionButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 8px 16px;");
        actionButton.setOnAction((event) -> {
            messageLabel.setText("Hello! You've successfully triggered JavaFX!");
            titleLabel.setText("It Works! \ud83c\udf89");
            actionButton.setDisable(true);
        });
        VBox rootLayout = new VBox((double)20.0F);
        rootLayout.setAlignment(Pos.CENTER);
        rootLayout.setStyle("-fx-background-color: #f4f6f7;");
        rootLayout.getChildren().addAll(new Node[]{titleLabel, messageLabel, actionButton});
        Scene scene = new Scene(rootLayout, (double)400.0F, (double)300.0F);
        primaryStage.setTitle("My First JavaFX Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
