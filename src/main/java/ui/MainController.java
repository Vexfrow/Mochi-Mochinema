package ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {

    @FXML
    private Button button_profil;


    @FXML
    private void Profil() throws IOException{
        App.setRoot("profil");
    }
    
}
