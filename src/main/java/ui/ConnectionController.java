package ui;

import java.io.IOException;

import SQL.SelectRecords;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import mochinema.Abonne;

public class ConnectionController {

    @FXML
    private TextField text_pseudo;

    @FXML
    private TextField text_mdp;

    private Abonne a;


    @FXML
    private void Confirmer() throws IOException{
        if(text_mdp.getText()!=null && text_pseudo.getText() != null){
            SelectRecords sr = new SelectRecords();
            a = sr.selectAbonneSpecifique(text_pseudo.getText(), text_mdp.getText());

            if(a != null){
                App.setRoot("home");
            }
        }


    }



    @FXML
    private void Exit() throws IOException{
        App.setRoot("home");
    }
    
}
