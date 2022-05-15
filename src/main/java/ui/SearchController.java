package ui;

import java.io.IOException;

import SQL.SelectRecords;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import mochinema.Abonne;
import mochinema.Film;
import mochinema.Professionel;

public class SearchController {
    
    @FXML 
    private Button button_exit;

    @FXML 
    private Button button_recherche;

    @FXML 
    private ComboBox<Professionel> choice_acteur;

    @FXML 
    private ComboBox<Integer> choice_annee;

    @FXML 
    private GridPane grid_film;

    private Parent root;

    private Abonne abonne;




    public void setParameters(String pseudo){
        SelectRecords sr = new SelectRecords();
        abonne = sr.selectAbonneSpecifique(pseudo);
    }
    

    public void initialize(){
        SelectRecords sr = new SelectRecords();
        Professionel[] listeActeur = sr.selectAllActeurs();
        int i = 0;
        while(i < listeActeur.length){
            choice_acteur.getItems().add(listeActeur[i]);
            i++;
        }

        int[] listeAnnee = sr.selectAllAnneeFilm();
        i = 0; 
        while(i < listeAnnee.length){
            choice_annee.getItems().add(listeAnnee[i]);
            i++;
        }

        Film[] listeFilm = sr.selectAllFilm();
        afficherFilm(listeFilm);

    }


    @FXML
    private void exit() throws IOException{
        //Blabla pour retourner à l'écran principale
        FXMLLoader newRoot = new FXMLLoader(App.class.getResource("main.fxml"));
        Parent root = newRoot.load();
        MainController mainC = newRoot.getController();
        mainC.setAbonne(abonne.getPseudo());
        App.newSceneAbonne(root);
    }



    @FXML
    private void recherche(){

        SelectRecords sr = new SelectRecords();
        Film[] listeFilm;
        grid_film.getChildren().clear();

        if(choice_acteur.getSelectionModel().isEmpty() && choice_annee.getSelectionModel().isEmpty()){
            //Aucune recherche
            listeFilm = sr.selectAllFilm();

        }else if(choice_annee.getSelectionModel().isEmpty()){
            //Recherche par acteur
            listeFilm = sr.selectFilmParticipant(choice_acteur.getValue().getNom(), choice_acteur.getValue().getPrenom(), choice_acteur.getValue().getDateNaissance());

        }else if(choice_acteur.getSelectionModel().isEmpty()){
            //Recherche par année
            listeFilm = sr.selectFilmAnnee(choice_annee.getValue());
            
        }else{
            //Recherche par acteur et année
            listeFilm = sr.selectFilmAnneeActeur(choice_acteur.getValue().getNom(), choice_acteur.getValue().getPrenom(), choice_acteur.getValue().getDateNaissance(),choice_annee.getValue());
        }
        afficherFilm(listeFilm);
    }



    private void afficherFilm(Film[] listeFilm){

        int i = 0;
        while(i  < listeFilm.length){
            StackPane film = new StackPane();
            StackPane annee = new StackPane();
            Label text_film = new Label(listeFilm[i].getTitre());
            Label text_annee = new Label(""+listeFilm[i].getDateProduction());
            text_film.setId(""+listeFilm[i].getID());
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
            film.getChildren().add(text_film);

            annee.getChildren().add(text_annee);

            film.setStyle("-fx-border-color: black; -fx-border-width: 1 1 1 1 ;");
            annee.setStyle("-fx-border-color: black; -fx-border-width: 1 1 1 0 ;");

            grid_film.add(film, 0, i);

            grid_film.add(annee, 1, i);

            i++;

        }


    }




}
