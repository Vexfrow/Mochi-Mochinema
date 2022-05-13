package ui;

import java.io.IOException;

import SQL.InsertRecords;
import SQL.SelectRecords;
import SQL.UpgradeRecords;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import mochinema.Abonne;
import mochinema.Critique;
import mochinema.Film;

public class CommentaireController {

    @FXML
    private Text text_titre;
    
    @FXML
    private Button button_exit; 
    
    @FXML
    private TextArea text_area;

    @FXML
    private TextField text_note;

    @FXML
    private Button button_submit;

    private Abonne abonne;
    private Film film;

    private Parent root;

    private boolean previousCom;

    @FXML
    private void exit() throws IOException{
        FXMLLoader newRoot = new FXMLLoader(App.class.getResource("film.fxml"));
        root = newRoot.load();
        FilmController filmC = newRoot.getController();
        filmC.setParametre(abonne.getPseudo(), film.getID());
        App.newSceneAbonne(root);
    }

    @FXML
    private void submit() throws IOException{

        if(estNombre(text_note.getText())){
            if(!previousCom){
                InsertRecords ir = new InsertRecords();
                ir.insertCritique(abonne.getPseudo(), film.getID(),text_area.getText() , Integer.parseInt(text_note.getText()));
            }
            else{
                UpgradeRecords ur = new UpgradeRecords();
                ur.upgradeCritique(abonne.getPseudo(), film.getID(),text_area.getText() , Integer.parseInt(text_note.getText()));
            }
            exit();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION); 
            alert.setContentText("La note n'est pas valide");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }


    public void setAttributs(String pseudo, int filmID){
        SelectRecords sr = new SelectRecords();
        this.abonne = sr.selectAbonneSpecifique(pseudo);
        this.film = sr.selectFilm(filmID);

        Critique c = sr.selectPrevCrit(pseudo, filmID);
        previousCom = c != null;
        loadPrevCom();
    }

    public void loadPrevCom(){
        SelectRecords sr = new SelectRecords();
        Critique c = sr.selectPrevCrit(this.abonne.getPseudo(), this.film.getID());
        if(previousCom){
            text_area.setText(c.getCritique());
            text_note.setText("" + (int)c.getNote());
        }
    }
 
    public boolean estNombre(String s){
        int i = 0;
        while(i < s.length() && (s.charAt(i) >= '0' && s.charAt(i) <= '9')){
            i++;
        }
        return i == s.length();
    }



}
