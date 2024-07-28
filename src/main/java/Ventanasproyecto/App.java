package Ventanasproyecto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    public static String pathImg="images/";
    public static String pathFile="files/";
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmloader=new FXMLLoader(App.class.getResource("VentanaInicio.fxml"));
        Parent root= fxmloader.load();
        scene= new Scene(root,900,600);
        stage.setScene(scene);
        stage.setTitle("Copa Mundial de la FIFA Brasil 2014");
        stage.show();
    }

    
    

    public static void main(String[] args) {
        launch();
    }

}