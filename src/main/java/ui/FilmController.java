package ui;

import java.io.IOException;

import SQL.SelectRecords;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import mochinema.Abonne;
import mochinema.Critique;
import mochinema.Film;

public class FilmController {

    @FXML
    private Button button_exit;
    @FXML
    private Button button_information;
    @FXML
    private Button button_critique;

    @FXML
    private Text text_titre;

    @FXML
    private Pane pane_second;

    private Parent root;

    private Abonne abonne;

    private Film film;

    private Critique[] tabCritique;


    @FXML
    private void exit() throws IOException{
        FXMLLoader newRoot = new FXMLLoader(App.class.getResource("main.fxml"));
        root = newRoot.load();
        MainController mainC = newRoot.getController();
        mainC.setAbonne(abonne.getPseudo());

        App.newSceneAbonne(root);
    }


    @FXML
    private void showCritic(){
        pane_second.getChildren().clear();

        GridPane gp_critique = new GridPane();
        gp_critique.setGridLinesVisible(true);


        SelectRecords sr = new SelectRecords();
        
        tabCritique = sr.selectCritiques(film.getID());
        int i =0;

        while(i < 6 && i < tabCritique.length){
            Text text_critique = new Text(tabCritique[i].getCritique());

            gp_critique.add(text_critique, 0, i);
            i++;
        }

        pane_second.getChildren().add(gp_critique);

        float mean = sr.moyenneNote(film.getID());

        Circle cercle = new Circle();
        cercle = switchColor(cercle, mean);
        cercle.setLayoutX(507);
        cercle.setLayoutY(60);
        cercle.setRadius(52);
        pane_second.getChildren().add(cercle);

        Text text_note = new Text(""+mean);
        text_note.setLayoutX(496);
        text_note.setLayoutY(64);
        text_note.setTextAlignment(TextAlignment.CENTER);
        pane_second.getChildren().add(text_note);
    }

    @FXML 
    public void showInformation(){


    }



    @FXML
    public void setParametre(String pseudo, int idFilm){
        SelectRecords sr = new SelectRecords();
        abonne = sr.selectAbonneSpecifique(pseudo);
        film = sr.selectFilm(idFilm);

        text_titre.setText(film.getTitre());
        showCritic();

    }


    private Circle switchColor(Circle c, float mean){
        if(mean < 4.){
            c.setFill(Color.RED);
        }else if(mean < 7.){
            c.setFill(Color.ORANGE);
        }else{
            c.setFill(Color.GREEN);
        }
        return c;
    }

    
}
