package ui;

import java.io.IOException;

import SQL.SelectRecords;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import mochinema.Abonne;
import mochinema.Film;

public class MainController {

    @FXML
    private Button button_profil;

    @FXML
    private Text text_test;

    private Parent root;

    public Abonne abonne;

    private Film[] tabFilm;


    @FXML
    private void Profil() throws IOException{
        FXMLLoader newRoot = new FXMLLoader(App.class.getResource("profil.fxml"));
        root = newRoot.load();

        ProfilController profilC = newRoot.getController();
        profilC.setAbonne(abonne);

        App.newSceneAbonne(root);
    }

    @FXML
    private void initialize(){
        SelectRecords sr = new SelectRecords();
        tabFilm = sr.selectAllFilm();
        
    }



    @FXML
    public void setAbonne(Abonne a){
        abonne = a;
        button_profil.setDisable(a.getPseudo().equals("Invite"));
        text_test.setText("Hello " + abonne.getPseudo());
    }

    
}
