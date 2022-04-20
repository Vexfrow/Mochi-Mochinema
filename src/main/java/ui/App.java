package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class App extends Application {

    private static Scene scene;
    static Stage stage;

    // @Override
    // public void start(Stage stage) throws IOException {
    //     scene = new Scene(loadFXML("home"), 640, 480);
    //     stage.setScene(scene);
    //     stage.show();
    // } 


    @Override
    public void start(Stage stage) throws IOException 
    {
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
        launch();
    }


    static void newScene(String newFileFXML) throws IOException {
        FXMLLoader newRoot = new FXMLLoader(App.class.getResource(newFileFXML + ".fxml"));
        scene.setRoot(newRoot.load());
        stage.setResizable(false);
        stage.setScene(scene);
        stage.centerOnScreen();
    }

}

