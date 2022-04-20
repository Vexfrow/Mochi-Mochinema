package ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import mochinema.Abonne;

public class MainController {

    @FXML
    private Button button_profil;

    public Abonne abonne;


    @FXML
    private void Profil() throws IOException{
        App.newScene("profil");
    }
    
}
