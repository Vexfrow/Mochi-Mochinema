package ui;

import java.io.IOException;

import SQL.InsertRecords;
import SQL.SelectRecords;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
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
        if(text_birthday.getText().length()!=0 && text_mdp1.getText().length()!=0 && text_mdp2.getText().length()!=0 && text_mail.getText().length()!=0 && text_nom.getText().length()!=0 && text_prenom.getText().length()!=0 && text_pseudo.getText().length()!=0){

            //Si les deux pseudos correspondent
            if(text_mdp1.getText().equals(text_mdp2.getText())){
                try{
                    //On crée un nouvel abonnée
                    Date dateNaissance = new Date(text_birthday.getText());
                    a = new Abonne(text_pseudo.getText(), text_nom.getText(), text_prenom.getText(), text_mail.getText(), text_mdp1.getText(), dateNaissance);

                    //On regarde si on peut insérer le nouvel abonnée dans la base de donnée
                    InsertRecords ir = new InsertRecords();
                    ir.insertAbonne(a.getPseudo(), a.getNom(), a.getPrenom(), a.getMail(), text_birthday.getText(), a.getMDP());

                    //Un message informatif apparaît pour confirmer l'inscription
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Votre compte a bien été crée. \nBienvenue " + a.getPrenom() + " " + a.getNom() + " !");
                    alert.setHeaderText(null);
                    alert.showAndWait();

                    //Bla bla pour changer de view
                    FXMLLoader newRoot = new FXMLLoader(App.class.getResource("main.fxml"));
                    Parent root = newRoot.load();
                    MainController mainC = newRoot.getController();
                    mainC.setAbonne(a);
                    App.newSceneAbonne(root);


                }catch(Exception e){ //Si on a message d'erreur lorsqu'on essaye d'inserer le nouvel abonné dans la base de donnée

                    //Un message informatif apparaît pour signaler que le pseudo est déjà pris
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Le pseudo est déjà pris par un autre utilisateur. Veuillez choisir un autre pseudo !");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }

            }else{
                //Un message informatif apparaît pour signaler que les mots de passes ne correspondent pas
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Les mots de passe ne corresponds pas, veuillez verifier les informations rentrées !");
                alert.setHeaderText(null);
                alert.showAndWait();

            }

        }
    }


    @FXML
    private void Exit() throws IOException{
        App.newScene("home");
    }



}
