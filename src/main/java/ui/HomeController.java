package ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import SQL.SelectRecords;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import mochinema.Abonne;


/*
    Classe pour la vue principale
*/

public class HomeController {

    @FXML
    private TextField text_pseudo;

    @FXML
    private TextField text_mdp;

    @FXML
    private ImageView image_test;

    private Parent root;

    private Abonne a;


    @FXML
    private void Confirmer() throws IOException{
        
        //Si l'utilisateur à rentré son pseudo et son mot de passe 
        if(text_mdp.getText().length() != 0 && text_pseudo.getText().length() != 0){ 

            SelectRecords sr = new SelectRecords();

            //On vérifie si les informations correspondent à un utilisateur
            try{
                //On récupère les autres informations de l'abonné
                a = sr.selectAbonneSpecifique(text_pseudo.getText(), text_mdp.getText()); 

                //Un message informatif apparaît pour confirmer la connection
                Alert alert = new Alert(Alert.AlertType.INFORMATION); 
                alert.setContentText("Vous êtes connecté. \nBonjour " + a.getPrenom() + " " + a.getNom() + " !");
                alert.setHeaderText(null);
                alert.showAndWait();

                //Bla bla pour changer de view
                FXMLLoader newRoot = new FXMLLoader(App.class.getResource("main.fxml"));
                root = newRoot.load();
                MainController mainC = newRoot.getController();
                mainC.setAbonne(a.getPseudo());
                App.newSceneAbonne(root);

            }catch(Exception e){ //Si les informations ne correspondent à aucun abonné (ie, qu'on a aucun abonné qui correspondent aux données dans la base de donnée)

                //Un message informatif apparaît pour dire qu'aucun abonné ne correspond aux données rentrées
                Alert alert = new Alert(Alert.AlertType.ERROR); 
                alert.setContentText("Aucun abonnée ne correponds aux données saisis. \nVeuillez vérifier les informations rentrées.");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        }


    }


    @FXML
    private void Inscription() throws IOException{

        //On change de view
        App.newScene("register"); 
    }
    

    @FXML
    private void Invite() throws IOException{

        //Bla bla pour changer de view
        FXMLLoader newRoot = new FXMLLoader(App.class.getResource("main.fxml")); 
        root = newRoot.load();
        MainController mainC = newRoot.getController();
        SelectRecords sr = new SelectRecords();
        a = sr.selectAbonneSpecifique("Invite");
        mainC.setAbonne(a.getPseudo());
        App.newSceneAbonne(root);
        
    }

    @FXML
    private void initialize() throws FileNotFoundException{
        Image image = new Image(new FileInputStream("./src/main/resources/image/home.png"));
        image_test.setImage(image);
        
    }


    
}