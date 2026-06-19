package Controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {
    @FXML
    AnchorPane MainPane;

    @FXML
    void initialize() {}


    void loadscreen (ActionEvent e, String s)
    {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource(s));
        } catch (IOException ex) {

            throw new RuntimeException(ex);
        }

        Scene NewScene = new Scene(root);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(NewScene);
        window.show();

    }

    public void SwitchToPlayVsFriend(ActionEvent e){

        loadscreen(e,"/Screens/PlayVsFriend.fxml");
    }

}
