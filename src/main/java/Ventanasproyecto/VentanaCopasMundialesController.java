package Ventanasproyecto;

import Modelo.Equipo;
import Modelo.Mundial;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

/**
 * FXML Controller class
 *
 * @author AVGla
 */
public class VentanaCopasMundialesController implements Initializable {

    @FXML
    TextField textfieldAnio;
    @FXML
    Button botonConsultar;
    @FXML
    VBox vboxPremios;
    @FXML
    VBox vboxDatos;
    @FXML
    HBox hboxDetalles;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void consultarMundiales(ActionEvent event1) {
        vboxPremios.getChildren().clear();
        vboxDatos.getChildren().clear();
        //Parte 1: Podio 
        if (textfieldAnio.getText() != null) {
            String anioelegido = textfieldAnio.getText();
            Mundial mundialelegido = Mundial.generarMundial(anioelegido);
            //System.out.println(mundialelegido.getMejoresCuatro());

            HBox hboxorganizador = new HBox();
            hboxorganizador.setSpacing(20);
            VBox.setMargin(hboxorganizador, new Insets(10, 0, 0, 0));
            Label textopremios = new Label("Premios");
            textopremios.setStyle("-fx-font-size:18;");
            Label Ganador = new Label("Ganador");
            Ganador.setStyle("-fx-font-size:18;");
            Label Segundo = new Label("Segundo");
            Segundo.setStyle("-fx-font-size:18;");
            Label Tercero = new Label("Tercero");
            Tercero.setStyle("-fx-font-size:18;");
            Label Cuarto = new Label("Cuarto");
            Cuarto.setStyle("-fx-font-size:18;");
            VBox vboxpuestos = new VBox();
            vboxpuestos.getChildren().addAll(Ganador, Segundo, Tercero, Cuarto);
            vboxpuestos.setSpacing(10);
            HBox.setMargin(vboxPremios, new Insets(0, 0, 0, 10));
            Line separador = new Line();
            separador.setStartX(-150);
            separador.setEndX(100);
            VBox vboxpaises = new VBox();
            vboxpaises.setSpacing(10);
            if (mundialelegido != null) {
                for (Equipo team : mundialelegido.getMejoresCuatro()) {
                    HBox banderasycopas = new HBox();
                    team.setImagen(team.getPais() + "_flag.png");
                    HBox hboxinfopais = new HBox();
                    HBox hboxtrofeos = new HBox();
                    hboxtrofeos.setSpacing(5);
                    //ImageView imagenBandera=null;
                    ImageView imagenBandera = new ImageView();
                    ImageView imagenTrofeo = null;
                    try ( FileInputStream input = new FileInputStream(App.pathImg + team.getImagen())) {
                        Image bandera = new Image(input, 40, 40, true, false);
                        //imagenBandera=new ImageView(bandera);
                        imagenBandera.setImage(bandera);

                    } catch (FileNotFoundException firstex) {
                        System.out.println("No se encontro el archivo de imagen");
                    } catch (IOException secondex) {
                        System.out.println("Ocurrio un problema al leer datos");
                    }
                    for (int i = 0; i < team.getCopasMundiales(); i++) {
                        try ( FileInputStream input2 = new FileInputStream(App.pathImg + "trofeo2.png")) {
                            Image trofeo = new Image(input2, 25, 25, true, false);
                            imagenTrofeo = new ImageView(trofeo);
                            hboxtrofeos.getChildren().addAll(imagenTrofeo);

                        } catch (FileNotFoundException firstex) {
                            System.out.println("No se encontro el archivo");
                        } catch (IOException secondex) {
                            System.out.println("Ocurrio un problema al leer datos");
                        }

                    }

                    Label labelpais = new Label(team.getPais());
                    labelpais.setStyle("-fx-font-size:16;");
                    hboxinfopais.getChildren().addAll(imagenBandera, labelpais);
                    hboxinfopais.setPrefSize(150, 20);
                    hboxinfopais.setSpacing(5);
                    banderasycopas.setAlignment(Pos.CENTER_LEFT);
                    hboxtrofeos.setAlignment(Pos.CENTER_LEFT);
                    banderasycopas.getChildren().addAll(hboxinfopais, hboxtrofeos);
                    vboxpaises.getChildren().addAll(banderasycopas);

                }
                hboxorganizador.getChildren().addAll(vboxpuestos, vboxpaises);
                vboxPremios.getChildren().addAll(textopremios, separador, hboxorganizador);

                //Parte 2: Datos generales
                Label textodatos = new Label("Datos generales");
                textodatos.setStyle("-fx-font-size:18;");
                Line separador2 = new Line();
                separador2.setStartX(-150);
                separador2.setEndX(100);
                Label labelgoles = new Label("Goles anotados: " + mundialelegido.getGolesAnotados());
                labelgoles.setStyle("-fx-font-size:18;");
                Label equiposclasificados = new Label("Equipos: " + mundialelegido.getnEquipos());
                equiposclasificados.setStyle("-fx-font-size:18;");
                Label partidosjugados = new Label("Partidos jugados: " + mundialelegido.getnPartidos());
                partidosjugados.setStyle("-fx-font-size:18;");
                Label asistentes = new Label("Asistencia: " + mundialelegido.getnAsistentes());
                textopremios.setStyle("-fx-font-size:18;");
                asistentes.setStyle("-fx-font-size:18;");
                VBox vboxDatosorganizador = new VBox();
                //vboxDatos.getChildren().addAll(textodatos,separador2,labelgoles,equiposclasificados,partidosjugados,asistentes);
                vboxDatosorganizador.getChildren().addAll(textodatos, separador2, labelgoles, equiposclasificados, partidosjugados, asistentes);
                vboxDatosorganizador.setSpacing(10);
                VBox.setMargin(vboxDatosorganizador, new Insets(10, 0, 0, 0));
                vboxDatos.getChildren().addAll(textodatos, separador2, vboxDatosorganizador);
            }
        }

    }

}
