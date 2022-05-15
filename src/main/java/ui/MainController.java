package ui;

import java.io.IOException;
import java.util.Optional;

import SQL.SelectRecords;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

    @FXML
    private  Button button_deconnexion;

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
    private void recherche() throws IOException{
        //Blabla pour retourner à l'écran principale
        FXMLLoader newRoot = new FXMLLoader(App.class.getResource("search.fxml"));
        Parent root = newRoot.load();
        SearchController searchC = newRoot.getController();
        searchC.setParameters(abonne.getPseudo());
        App.newSceneAbonne(root);
    }




    @FXML
    private void Deconnexion() throws IOException{
        if(abonne.getPseudo().equals("Invite")){
            FXMLLoader newRoot = new FXMLLoader(App.class.getResource("home.fxml"));
            root = newRoot.load();
            App.newSceneAbonne(root);
        }
        else{
            try{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Déconnexion");
                alert.setContentText("Voulez-vous vraiment vous deconnecter");
                Optional<ButtonType> option = alert.showAndWait();
                if(option.get() == ButtonType.OK)
                {
                    FXMLLoader newRoot = new FXMLLoader(App.class.getResource("home.fxml"));
                    root = newRoot.load();
                    App.newSceneAbonne(root);
                }


            } catch (Exception e){
                System.out.println(e);
            }
            
        }

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
        text_test.setTextAlignment(TextAlignment.CENTER);
        if(abonne.getPseudo().equals("Invite")){
            button_deconnexion.setText("Connexion");
        }
    }

    
}
