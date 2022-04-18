package ui;

import java.io.IOException;
import javafx.fxml.FXML;


/*
    Classe pour la vue principale
*/

public class HomeController {

    @FXML
    private void Connecter() throws IOException{
        App.setRoot("connection");

    }

    @FXML
    private void Inscription() throws IOException{
        App.setRoot("register");
    }

    @FXML
    private void Invite(){
        
    }
}