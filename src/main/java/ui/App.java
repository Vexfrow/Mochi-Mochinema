package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import SQL.Create;
import SQL.CreateTable;
import SQL.InsertRecords;


public class App extends Application {

    private static Scene scene;
    static Stage stage;


    @Override
    public void start(Stage stage) throws IOException {
        try{
            this.stage = stage;
            Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
            scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
     
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        //On regarde si la base de donnée existe déjà ou pas
        //Si elle n'existe pas, on la crée ainsi que les tables, et on insère les données de base
        try{
            Create.main(null);
            // CreateTable.main(null);
            // InsertRecords.main(null);

        }catch (Exception e){
            System.out.println("La base de donnée existe déjà");
        }

        launch();
    }


    static void newScene(String newFileFXML) throws IOException {
        FXMLLoader newRoot = new FXMLLoader(App.class.getResource(newFileFXML + ".fxml"));
        scene.setRoot(newRoot.load());
        stage.setResizable(false);
        stage.setScene(scene);
        stage.centerOnScreen();
    }
    

    static void newSceneAbonne(Parent root) throws IOException {
        scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

}

