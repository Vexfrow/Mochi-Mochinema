package ui;

import java.io.IOException;

import SQL.InsertRecords;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import mochinema.Abonne;
import mochinema.Date;

public class InscriptionController {
    

    public Abonne a;

    @FXML
    private TextField text_pseudo;

    @FXML
    private TextField text_mdp1;

    @FXML
    private TextField text_mdp2;

    @FXML
    private TextField text_nom;

    @FXML
    private TextField text_prenom;

    @FXML
    private TextField text_birthday;

    @FXML
    private TextField text_mail;


    @FXML
    private void Confirmer() throws IOException{
        if(text_birthday.getText()!=null && text_mdp1.getText()!=null && text_mdp2.getText()!=null && text_mail.getText()!=null && text_nom.getText()!=null && text_prenom.getText()!=null && text_pseudo.getText()!=null){
            Date dateNaissance = new Date(text_birthday.getText());
            a = new Abonne(text_pseudo.getText(), text_nom.getText(), text_prenom.getText(), text_mail.getText(), text_mdp1.getText(), dateNaissance);
            
            InsertRecords ir = new InsertRecords();
            ir.insertAbonne(a.getPseudo(), a.getNom(), a.getPrenom(), a.getMail(), text_birthday.getText(), a.getMDP());
            App.setRoot("home");
        }   

    }


    @FXML
    private void Exit() throws IOException{
        App.setRoot("home");
    }



}
