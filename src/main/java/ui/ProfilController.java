package ui;

import java.io.IOException;

import SQL.DeleteRecords;
import SQL.SelectRecords;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import mochinema.Abonne;

public class ProfilController {

    @FXML
    private Text text_info;

    @FXML
    private Button button_changeMDP;

    @FXML
    private Button button_delete;

    @FXML
    private Button button_exit;

    @FXML
    private Button button_modifier;

    @FXML
    private Button button_critics;

    @FXML
    private TextField text_actuelMDP;

    @FXML
    private TextField text_nouveauMDP1;

    @FXML
    private TextField text_nouveauMDP2;

    @FXML
    private Pane pane_second;

    private Abonne abonne;

    @FXML
    private void deleteAccount() throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez vous vraiment supprimer votre compte Mochi-Mochinema ?", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText(null);

        ButtonType result = alert.showAndWait().orElse(ButtonType.YES);

        SelectRecords sr = new SelectRecords();

        if(ButtonType.YES.equals(result)){
            DeleteRecords dr = new DeleteRecords();
            dr.deleteAbonne(abonne.getPseudo());

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Votre compte a bien été supprimé. \nOn espere vous revoir bientôt !");
            alert.setHeaderText(null);
            alert.showAndWait();

            App.newScene("home");
        }

    }


    @FXML
    private void Exit() throws IOException{
        FXMLLoader newRoot = new FXMLLoader(App.class.getResource("main.fxml"));
        Parent root = newRoot.load();

        MainController mainC = newRoot.getController();
        mainC.setAbonne(abonne);

        App.newSceneAbonne(root);
        
    }


    @FXML
    private void modifierAccount(){
        pane_second.getChildren().clear();

        Button button_confirmationMDP = new Button("Confirmer changement mot de passe");
        pane_second.getChildren().add(button_confirmationMDP);
        button_confirmationMDP.setLayoutX(224.);
        button_confirmationMDP.setLayoutY(230.);
        button_confirmationMDP.setPrefHeight(25.);
        button_confirmationMDP.setPrefWidth(119.);

        Button button_confirmationInfo = new Button("Confirmer changement d'information");
        pane_second.getChildren().add(button_confirmationInfo);
        button_confirmationInfo.setLayoutX(407.);
        button_confirmationInfo.setLayoutY(24.);
        button_confirmationInfo.setPrefHeight(86.);
        button_confirmationInfo.setPrefWidth(107.);

        TextField text_nom = new TextField(abonne.getNom());
        pane_second.getChildren().add(text_nom);
        text_nom.setPromptText("Nom");
        text_nom.setLayoutX(36.);
        text_nom.setLayoutY(29.);

        TextField text_prenom = new TextField(abonne.getPrenom());
        pane_second.getChildren().add(text_prenom);
        text_prenom.setPromptText("Prenom");
        text_prenom.setLayoutX(209.);
        text_prenom.setLayoutY(29.);

        TextField text_mail = new TextField(abonne.getMail());
        pane_second.getChildren().add(text_mail);
        text_mail.setPromptText("Adresse e-mail");
        text_mail.setLayoutX(36.);
        text_mail.setLayoutY(85.);
        
    }


    @FXML
    private void showCritique(){
        pane_second.getChildren().clear();

        Button button_truc = new Button("Button");
        pane_second.getChildren().add(button_truc);
        
    }


    
    @FXML
    public void setAbonne(Abonne a){
        abonne = a;
        text_info.setText("Profil de " + abonne.getPseudo());
        showCritique();   
    }





    
}
