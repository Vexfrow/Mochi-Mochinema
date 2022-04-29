package ui;

import java.io.IOException;

import SQL.SelectRecords;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import mochinema.Abonne;
import mochinema.Film;

public class MainController {

    @FXML
    private Button button_profil;

    @FXML
    private Text text_test;

    @FXML
    private GridPane gp_main;

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
        remplirListeFilm(tabFilm);
        
    }


    @FXML
    private void remplirListeFilm(Film[] tab){
        int i = 0;

        while(i < 6 && i < tab.length){
            Text text_film = new Text(tab[i].getTitre());

            
            text_film.setOnMouseClicked(event -> {
                SelectRecords sr = new SelectRecords();
                
                try {
                    FXMLLoader newRoot = new FXMLLoader(App.class.getResource("film.fxml"));
                    root = newRoot.load();
                    FilmController filmC = newRoot.getController();
                    filmC.setParametre(abonne, sr.selectFilm(text_film.getText()));
                    App.newSceneAbonne(root);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            gp_main.add(text_film, i/3, i%3);
            i++;
        }

    }


    @FXML
    public void setAbonne(Abonne a){
        abonne = a;
        button_profil.setDisable(a.getPseudo().equals("Invite"));
        text_test.setText("Hello " + abonne.getPseudo());
    }

    
}
