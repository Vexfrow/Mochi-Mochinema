package ui;

import java.io.IOException;

import SQL.SelectRecords;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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

    private Abonne abonne;

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
        gp_main.setGridLinesVisible(true);

        while(i < 6 && i < tab.length){
            Label text_film = new Label(tab[i].getTitre());
            text_film.setId(""+tab[i].getID());
            text_film.setTextAlignment(TextAlignment.CENTER);
            
            text_film.setOnMouseClicked(event -> {
                
                try {
                    FXMLLoader newRoot = new FXMLLoader(App.class.getResource("film.fxml"));
                    root = newRoot.load();
                    FilmController filmC = newRoot.getController();
                    filmC.setParametre(abonne.getPseudo(), Integer.parseInt(text_film.getId()));
                    App.newSceneAbonne(root);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            gp_main.add(text_film, i/3, i%3);
            GridPane.setHalignment(text_film, HPos.CENTER);
            GridPane.setValignment(text_film, VPos.CENTER);
            
            i++;
        }

    }


    @FXML
    public void setAbonne(String pseudo){
        SelectRecords sr = new SelectRecords();
        sr.selectAbonneSpecifique(pseudo);
        abonne = sr.selectAbonneSpecifique(pseudo);
        button_profil.setDisable(pseudo.equals("Invite"));
        text_test.setText("Hello " + abonne.getPseudo());
    }

    
}
