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
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import mochinema.Abonne;
import mochinema.Critique;
import mochinema.Film;
import mochinema.Professionel;

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
        gp_critique.setPrefWidth(420.);
        gp_critique.setPrefHeight(275.);

        RowConstraints rc1 = new RowConstraints();
        rc1.setPercentHeight(275./6.);
        RowConstraints rc2 = new RowConstraints();
        rc2.setPercentHeight(275./6.);
        RowConstraints rc3 = new RowConstraints();
        rc3.setPercentHeight(275./6.);
        RowConstraints rc4 = new RowConstraints();
        rc4.setPercentHeight(275./6.);
        RowConstraints rc5 = new RowConstraints();
        rc5.setPercentHeight(275./6.);
        RowConstraints rc6 = new RowConstraints();
        rc6.setPercentHeight(275./6.);

        ColumnConstraints cc1 = new ColumnConstraints();
        cc1.setPercentWidth(65.);
        ColumnConstraints cc2 = new ColumnConstraints();
        cc2.setPercentWidth(25.);
        ColumnConstraints cc3 = new ColumnConstraints();
        cc3.setPercentWidth(10.);

        gp_critique.getColumnConstraints().addAll(cc1,cc2,cc3);
        gp_critique.getRowConstraints().addAll(rc1,rc2,rc3,rc4,rc5,rc6);


        SelectRecords sr = new SelectRecords();
        
        tabCritique = sr.selectCritiques(film.getID());
        int i =0;

        while(i < 6 && i < tabCritique.length){
            Label text_critique = new Label(tabCritique[i].getCritique());
            Label text_pseudo = new Label(tabCritique[i].getPseudo());
            Label text_note = new Label(""+tabCritique[i].getNote());

            gp_critique.add(text_critique, 0, i);
            text_critique.setWrapText(true);
            GridPane.setValignment(text_critique, VPos.CENTER);
            GridPane.setHalignment(text_critique, HPos.CENTER);

            gp_critique.add(text_pseudo, 1, i);
            text_pseudo.setWrapText(true);
            GridPane.setHalignment(text_pseudo, HPos.CENTER);
            GridPane.setValignment(text_pseudo, VPos.CENTER);

            gp_critique.add(text_note, 2, i);
            text_note.setWrapText(true);
            GridPane.setHalignment(text_note, HPos.CENTER);
            GridPane.setValignment(text_note, VPos.CENTER);
            i++;
        }

        pane_second.getChildren().add(gp_critique);
        gp_critique.setLayoutY(14.);
        gp_critique.setLayoutX(14.);

        float mean = sr.moyenneNote(film.getID());

        Circle cercle = new Circle();
        switchColor(cercle, mean);
        cercle.setLayoutX(507);
        cercle.setLayoutY(60);
        cercle.setRadius(52);
        pane_second.getChildren().add(cercle);

        Text text_note = new Text(""+mean);
        text_note.setLayoutX(496);
        text_note.setLayoutY(64);
        text_note.setTextAlignment(TextAlignment.CENTER);
        pane_second.getChildren().add(text_note);


        Button button_critique = new Button("Ajouter/Modifier critique");
        button_critique.setLayoutX(450.);
        button_critique.setLayoutY(157.);
        button_critique.setPrefWidth(105.);
        button_critique.setPrefHeight(130.);
        pane_second.getChildren().add(button_critique);

    }

    @FXML 
    public void showInformation(){
        pane_second.getChildren().clear();

        SelectRecords sr = new SelectRecords();

        TextFlow textF_info = new TextFlow();
        textF_info.setPrefHeight(275.);
        textF_info.setPrefWidth(290.);

        Professionel real = sr.selectRealisateur(film.getID());
        Label texte_realisateur = new Label("Le film a été réalisé par " + real.getPrenom() + " " + real.getNom() );
        textF_info.getChildren().add(texte_realisateur);

        Label texte_acteur = new Label("\n \nLes acteurs suivants ont participés au film : \n");
        Professionel[] acteurs = sr.selectActeurs(film.getID());
        int i = 0;

        while(i < acteurs.length){
            texte_acteur.setText(texte_acteur.getText() + "-" +acteurs[i].getPrenom()+" "+ acteurs[i].getNom()+"," + '\n');
           
            i++;
        }
        textF_info.getChildren().add(texte_acteur);
        
        pane_second.getChildren().add(textF_info);
        textF_info.setLayoutX(14.);
        textF_info.setLayoutY(14.);

    }



    @FXML
    public void setParametre(String pseudo, int idFilm){
        SelectRecords sr = new SelectRecords();
        abonne = sr.selectAbonneSpecifique(pseudo);
        film = sr.selectFilm(idFilm);

        text_titre.setText(film.getTitre());
        showInformation();

    }


    private void switchColor(Circle c, float mean){
        if(mean < 4.){
            c.setFill(Color.RED);
        }else if(mean < 7.){
            c.setFill(Color.ORANGE);
        }else{
            c.setFill(Color.GREEN);
        }
    }

    
}
