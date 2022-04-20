package ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private Button button_profil;


    @FXML
    private void Profil() throws IOException{
        App.newScene("profil");
    }
    
}
