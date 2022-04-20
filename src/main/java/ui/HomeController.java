package ui;

import java.io.IOException;

import SQL.SelectRecords;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import mochinema.Abonne;
import mochinema.Date;


/*
    Classe pour la vue principale
*/

public class HomeController {

    @FXML
    private TextField text_pseudo;

    @FXML
    private TextField text_mdp;

    private Abonne a;

    private final Date d = new Date("00/00/0000");
    private final Abonne invite = new Abonne("Invite", "Invite","Invite","Invite", "Invite", d);


    @FXML
    private void Confirmer() throws IOException{
        if(text_mdp.getText().length() != 0 && text_pseudo.getText().length() != 0){
            SelectRecords sr = new SelectRecords();
            a = sr.selectAbonneSpecifique(text_pseudo.getText(), text_mdp.getText());

            if(a != null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Vous êtes connecté. \nBonjour " + a.getPrenom() + " " + a.getNom() + " !");
                alert.setHeaderText(null);
                alert.showAndWait();
                App.newScene("main");
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Aucun abonnée ne correponds aux données saisis. \nVeuillez vérifier les informations rentrées.");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        }


    }


    @FXML
    private void Inscription() throws IOException{
        App.newScene("register");
    }

    @FXML
    private void Invite() throws IOException{
        a = invite;
        App.newScene("main");
        
    }

    
}