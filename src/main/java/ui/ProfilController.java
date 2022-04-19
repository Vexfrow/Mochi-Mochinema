package ui;

import java.io.IOException;

import SQL.DeleteRecords;
import SQL.SelectRecords;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import mochinema.Abonne;
import mochinema.Date;

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
    private TextField text_actuelMDP;

    @FXML
    private TextField text_nouveauMDP1;

    @FXML
    private TextField text_nouveauMDP2;



    private Date d = new Date("11/06/2004");
    private Abonne a = new Abonne("Vexfrow", "ef", "ef", "ef", "ef", d);

    @FXML
    private void Delete() throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez vous vraiment supprimer votre compte Mochi-Mochinema ?", ButtonType.YES, ButtonType.NO);

        ButtonType result = alert.showAndWait().orElse(ButtonType.YES);

        if(ButtonType.YES.equals(result)){
            DeleteRecords dr = new DeleteRecords();
            dr.deleteAbonne(a.getPseudo());
            App.setRoot("home");

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Votre compte a bien été supprimé. \nOn espere vous revoir bientôt !");
            alert.setHeaderText(null);
            alert.showAndWait();
        }

    }


    @FXML
    private void Exit() throws IOException{
        App.setRoot("main");
        
    }


    @FXML
    private void ChangeMDP(){
        SelectRecords sr = new SelectRecords();
        
        if(sr.getMDPAbonne(a.getPseudo()).equals(text_actuelMDP.getText()) && text_nouveauMDP1.getText().equals(text_nouveauMDP2.getText()) && text_nouveauMDP1.getText().equals("")){
            

        }

        
    }


    @FXML
    private void initialize(){
        text_info.setText("Profil de " + a.getPseudo());
    }
    
}
