package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import  javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

public class PlayVsFriendController extends MainMenuController {
    @FXML
    TextField P1Name;
    @FXML
    TextField P2Name;
    @FXML
    ChoiceBox Timer;
    @FXML
    RadioButton W1;
    @FXML
    RadioButton B1;
    @FXML
    RadioButton W2;
    @FXML
    RadioButton B2;
    @FXML
    Label ERROR;
    @FXML
    Label NameERROR;
    @FXML
    Label NameERROR2;

    @FXML
    @Override
    void initialize() {
        ERROR.visibleProperty().bind(
                W1.selectedProperty().and(W2.selectedProperty()).or(B1.selectedProperty().and(B2.selectedProperty())
                ));
        ERROR.managedProperty().bind(ERROR.visibleProperty());


        NameERROR.visibleProperty().bind(
                P1Name.textProperty().isEmpty()
        );
        NameERROR.managedProperty().bind(NameERROR.visibleProperty());


        NameERROR2.visibleProperty().bind(
               P2Name.textProperty().isEmpty()
        );
        NameERROR2.managedProperty().bind(NameERROR.visibleProperty());
        if(Timer!=null)
        {
            Timer.getItems().addAll("3 Mins", "5 Mins", "10 Mins");
            Timer.setValue("3 Mins");
        }

    }
    @FXML
    public void ToMainMenu(ActionEvent e) {
        loadscreen(e, "/Screens/MainMenu.fxml");
    }
    public void Play(ActionEvent e) {
        if(P1Name!=null)
        {
            System.out.println(P1Name.getText());
        }
        if(P2Name!=null)
        {
            System.out.println(P2Name.getText());
        }
        if(Timer!=null){
            System.out.println(Timer.getValue());
        }
        loadscreen(e, "/Screens/Board.fxml");
    }
}
