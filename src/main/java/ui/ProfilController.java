package ui;

import java.io.IOException;

import SQL.DeleteRecords;
import SQL.UpgradeRecords;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
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
        //Un message informatif apparaît pour confirmer la suppression du compte
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez vous vraiment supprimer votre compte Mochi-Mochinema ?", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText(null);

        ButtonType result = alert.showAndWait().orElse(ButtonType.YES);

        //Si la réponse est oui
        if(ButtonType.YES.equals(result)){
            //On supprime l'abonné de la base de donnée
            DeleteRecords dr = new DeleteRecords();
            dr.deleteAbonne(abonne.getPseudo());

            //On affiche un message afin de confirmer le suppression du compte
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Votre compte a bien été supprimé. \nOn espere vous revoir bientôt !");
            alert.setHeaderText(null);
            alert.showAndWait();

            //On retourne à l'écran d'accueil
            App.newScene("home");
        }

    }


    @FXML
    private void Exit() throws IOException{
        //Blabla pour retourner à l'écran principale
        FXMLLoader newRoot = new FXMLLoader(App.class.getResource("main.fxml"));
        Parent root = newRoot.load();
        MainController mainC = newRoot.getController();
        mainC.setAbonne(abonne);
        App.newSceneAbonne(root);
        
    }


    @FXML
    private void modifierAccount(){

        //On modifie l'affichage de l'écran
        pane_second.getChildren().clear();

        TextField text_nom = new TextField(abonne.getNom());
        pane_second.getChildren().add(text_nom);
        text_nom.setPromptText("Nom");
        text_nom.setText(abonne.getNom());
        text_nom.setLayoutX(36.);
        text_nom.setLayoutY(29.);

        TextField text_prenom = new TextField(abonne.getPrenom());
        pane_second.getChildren().add(text_prenom);
        text_prenom.setPromptText("Prenom");
        text_nom.setText(abonne.getPrenom());
        text_prenom.setLayoutX(209.);
        text_prenom.setLayoutY(29.);

        TextField text_mail = new TextField(abonne.getMail());
        pane_second.getChildren().add(text_mail);
        text_mail.setPromptText("Adresse e-mail");
        text_nom.setText(abonne.getMail());
        text_mail.setLayoutX(36.);
        text_mail.setLayoutY(85.);

        Button button_confirmationInfo = new Button("Confirmer changement d'information");
        pane_second.getChildren().add(button_confirmationInfo);
        button_confirmationInfo.setLayoutX(407.);
        button_confirmationInfo.setLayoutY(24.);
        button_confirmationInfo.setPrefHeight(86.);
        button_confirmationInfo.setPrefWidth(107.);

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
                if(text_mail.getText()!= null && text_nom.getText()!=null && text_prenom.getText()!=null){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez vous modifier les informations de votre compte Mochi-Mochinema ?", ButtonType.YES, ButtonType.NO);
                    alert.setHeaderText(null);
            
                    ButtonType result = alert.showAndWait().orElse(ButtonType.YES);
            
                    //Si la réponse est oui
                    if(ButtonType.YES.equals(result)){
                        try{
                            UpgradeRecords ur = new UpgradeRecords();
                            ur.upgradeAbonne(abonne.getPseudo(), text_prenom.getText(), text_mail.getText(), text_nom.getText());

                            Alert alert1 = new Alert(Alert.AlertType.INFORMATION); 
                            alert1.setContentText("Vos informations ont bien été modifiés !");
                            alert1.setHeaderText(null);
                            alert1.showAndWait();
                        }catch(Exception error){
                            System.out.println("Errrororororororororororroroor");
                        }
                    }
                }
            }
        };


        PasswordField mdpField_actual = new PasswordField();
        pane_second.getChildren().add(mdpField_actual);
        mdpField_actual.setPromptText("Mot de passe actuel");
        mdpField_actual.setLayoutX(24.);
        mdpField_actual.setLayoutY(193.);

        PasswordField mdpField_new1 = new PasswordField();
        pane_second.getChildren().add(mdpField_new1);
        mdpField_new1.setPromptText("Nouveau mot de passe ");
        mdpField_new1.setLayoutX(209.);
        mdpField_new1.setLayoutY(193.);

        PasswordField mdpField_new2 = new PasswordField();
        pane_second.getChildren().add(mdpField_new2);
        mdpField_new2.setPromptText("Nouveau mot de passe confirmation");
        mdpField_new2.setLayoutX(390.);
        mdpField_new2.setLayoutY(193.);

        Button button_confirmationMDP = new Button("Confirmer changement mot de passe");
        pane_second.getChildren().add(button_confirmationMDP);
        button_confirmationMDP.setLayoutX(257.);
        button_confirmationMDP.setLayoutY(236.);
        button_confirmationMDP.setPrefHeight(25.);
        button_confirmationMDP.setPrefWidth(119.);

        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
                if(mdpField_actual.getText().equals(abonne.getMDP()) && mdpField_new1.getText().equals(mdpField_new2.getText())){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez vous modifier le mot de passe de votre compte Mochi-Mochinema ?", ButtonType.YES, ButtonType.NO);
                    alert.setHeaderText(null);
            
                    ButtonType result = alert.showAndWait().orElse(ButtonType.YES);
            
                    //Si la réponse est oui
                    if(ButtonType.YES.equals(result)){
                        try{
                            UpgradeRecords ur = new UpgradeRecords();
                            ur.upgradeAbonneMdp(abonne.getPseudo(), mdpField_new1.getText());
                            
                            Alert alert1 = new Alert(Alert.AlertType.INFORMATION); 
                            alert1.setContentText("Votre mot de passe à bien été modifiés !");
                            alert1.setHeaderText(null);
                            alert1.showAndWait();
                        }catch(Exception error){
                            System.out.println("Errrororororororororororroroor");
                        }
                    }
                }else{
                    Alert alert1 = new Alert(Alert.AlertType.ERROR); 
                    alert1.setContentText("Le mot de passe actuel ne correspond pas, ou les deux nouveaux mots de passe ne fonctionnent pas.");
                    alert1.setHeaderText(null);
                    alert1.showAndWait();

                }
            }
        };

        button_confirmationMDP.setOnAction(event2);
        button_confirmationInfo.setOnAction(event);



        
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
