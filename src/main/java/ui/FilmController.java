package ui;

import java.io.IOException;

import SQL.SelectRecords;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import mochinema.Abonne;
import mochinema.Film;

public class FilmController {

    @FXML
    private Button button_exit;

    @FXML
    private Text text_titre;

    private Parent root;

    public Abonne abonne;

    private Film film;


    @FXML
    private void exit() throws IOException{
        FXMLLoader newRoot = new FXMLLoader(App.class.getResource("main.fxml"));
        root = newRoot.load();

        MainController mainC = newRoot.getController();
        mainC.setAbonne(abonne);

        App.newSceneAbonne(root);
    }


    @FXML
    private void initialize(){
        
    }



    @FXML
    public void setParametre(Abonne a, Film f){
        abonne = a;
        film = f;

        text_titre.setText("Film " + film.getTitre());

    }

    
}
