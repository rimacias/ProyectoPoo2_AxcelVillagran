/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Ventanasproyecto;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.*;
import java.io.FileInputStream;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class VentanaInicioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    ImageView logoCopaMundial;
    @FXML
    ImageView imagenInicio;
    @FXML
    Button btnConsultaPartidos;
    @FXML
    Button btnConsultaCopas;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try(FileInputStream input = new FileInputStream(App.pathImg+"/logo.jpg");FileInputStream input2 = new FileInputStream(App.pathImg+"/imagenInicio.jpg")){
            Image img = new Image(input,90,90,true,false);
            Image img2 = new Image(input2, 694,382,true,false);
            imagenInicio.setImage(img2);
            logoCopaMundial.setImage(img);
        }catch(IOException e){
            System.out.println("No se pudo cargar");
        }
        
        
        
    }
    
    @FXML
    public void consultar_partidos(ActionEvent event) throws IOException{
        Scene scene;
        Stage s = new Stage();
        FXMLLoader fxmlloader = new FXMLLoader (App.class.getResource("VentanaConsultaPartido.fxml"));
        Parent root1 = fxmlloader.load();
        scene = new Scene(root1,900,600);
        s.setTitle("Consultar Partido");
        s.setScene(scene);
        s.show();
        
    }
    @FXML
    public void consultar_copas()throws IOException{
        Scene scene;
        
        Stage s = (Stage)btnConsultaCopas.getScene().getWindow();
        FXMLLoader fxmlloader = new FXMLLoader (App.class.getResource("VentanaCopasMundiales.fxml"));
        Parent root1 = fxmlloader.load();
        scene = new Scene(root1,700,400);
        s.setTitle("Consultar Mundiales");
        s.setScene(scene);
        s.show();
        
    }
    
}
