/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Ventanasproyecto;

import Modelo.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import java.util.ArrayList;
import javafx.scene.layout.VBox;
import java.util.Collections;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.shape.Line;
import javafx.scene.control.Alert;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Random;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class VentanaConsultaPartidoController implements Initializable {

    @FXML
    ComboBox comboFase;

    @FXML
    HBox hbox1_partidos;

    @FXML
    HBox hbox2_partidos;

    @FXML
    HBox hbox3_partidos;

    @FXML
    VBox root1;

    @FXML
    VBox vbox_datos;

    @FXML
    HBox hbox_marcador;
    @FXML
    VBox vbox_buttons;
    @FXML
    VBox vbox_heading;
    @FXML
    HBox hboxgrupos;
    @FXML
    Label labelfase;
    Partido partido = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboFase.getItems().addAll("Groups", "Round of 16", "Quarter-finals", "Semi-finals", "Final");
    }

    @FXML
    public void seleccionarFase(ActionEvent e) {
        hboxgrupos.getChildren().clear();
        hbox2_partidos.getChildren().clear();
        hbox3_partidos.getChildren().clear();

        Label labelGrupos = new Label();
        ComboBox comboGrupos = new ComboBox();

        if (((String) comboFase.getValue()).equals("Groups")) {

            labelGrupos.setText("Grupos: ");
            labelGrupos.setFont(new Font("Arial", 14));
            comboGrupos.setPrefWidth(150);
            comboGrupos.getItems().addAll("A", "B", "C", "D", "E", "F", "G", "H");
            HBox.setMargin(labelGrupos, new Insets(0, 0, 0, 115));
            hboxgrupos.getChildren().addAll(labelGrupos, comboGrupos);
            hbox1_partidos.setAlignment(Pos.CENTER);
            comboGrupos.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    hbox2_partidos.getChildren().clear();
                    hbox3_partidos.getChildren().clear();

                    mostrarEquipos(seleccionar_partidos_fase("Group " + (String) comboGrupos.getValue()));
                }
            });
        } else {
            mostrarEquipos(seleccionar_partidos_fase((String) comboFase.getValue()));
        }

    }

    public ArrayList<Partido> seleccionar_partidos_fase(String fase) {
        ArrayList<Partido> partidos = Partido.cargarPartido();
        ArrayList<Partido> partidos_fase = new ArrayList();
        for (Partido p : partidos) {
            if ((p.getFase()).equals(fase)) {
                partidos_fase.add(p);
            }

        }
        ArrayList<Partido> partidos_fase_unicos = new ArrayList();
        for (Partido p : partidos_fase) {
            if (!partidos_fase_unicos.contains(p)) {
                partidos_fase_unicos.add(p);
            }
        }
        return partidos_fase_unicos;
    }

    public void mostrarEquipos(ArrayList partidos_fase_unicos) {

        Label equipo1 = new Label("Equipo 1:");
        Label equipo2 = new Label("Equipo 2:");
        Label lb_vs = new Label("vs");
        ComboBox<Equipo> comboEquipo1 = new ComboBox();
        comboEquipo1.setPrefWidth(150);
        ComboBox<Equipo> comboEquipo2 = new ComboBox();
        comboEquipo2.setPrefWidth(150);
        Button btnConsultar = new Button("CONSULTAR");
        btnConsultar.setStyle("-fx-background-color: #17a0c6;-fx-text-fill:WHITE");
        btnConsultar.setFont(new Font("Arial", 16));
        btnConsultar.setPrefHeight(50);
        equipo1.setFont(new Font("Arial", 14));
        equipo2.setFont(new Font("Arial", 14));
        lb_vs.setFont(new Font("Arial", 12));
        hbox2_partidos.getChildren().addAll(equipo1, comboEquipo1, lb_vs);
        HBox.setMargin(equipo1, new Insets(0, 0, 0, 25));
        HBox.setMargin(lb_vs, new Insets(0, 0, 0, 150));

        ArrayList<Equipo> equipos1 = new ArrayList();
        for (Object p : partidos_fase_unicos) {
            equipos1.add(((Partido) p).getEquipo1());

        }

        Collections.sort(equipos1);

        ArrayList<Equipo> equipos1_unicos = new ArrayList();

        for (Equipo p : equipos1) {
            if (!equipos1_unicos.contains(p)) {
                equipos1_unicos.add(p);
            }
        }
        //System.out.println(equipos1_unicos);
        comboEquipo1.getItems().addAll(equipos1_unicos);

        comboEquipo1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent a) {
                ArrayList<Equipo> equipos2 = new ArrayList();
                for (Object p : partidos_fase_unicos) {
                    if ((comboEquipo1.getValue()).equals(((Partido) p).getEquipo1())) {
                        equipos2.add(((Partido) p).getEquipo2());

                    }
                }

                Collections.sort(equipos2);
                ObservableList<Equipo> equipos2_ordenado = FXCollections.observableArrayList(equipos2);
                comboEquipo2.setItems(equipos2_ordenado);

            }
        });
        hbox2_partidos.getChildren().addAll(equipo2, comboEquipo2);
        HBox.setMargin(equipo2, new Insets(0, 0, 0, 113));

        hbox1_partidos.setSpacing(20);
        hbox2_partidos.setSpacing(20);
        hbox3_partidos.setSpacing(20);
        hbox3_partidos.getChildren().add(btnConsultar);

        hbox2_partidos.setAlignment(Pos.CENTER);
        hbox3_partidos.setAlignment(Pos.CENTER);

        btnConsultar.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                vbox_buttons.getChildren().clear();
                vbox_heading.getChildren().clear();
                vbox_datos.getChildren().clear();
                hbox_marcador.getChildren().clear();
                Label label_resultados = new Label("Resultados del partido");
                label_resultados.setFont(new Font("Arial", 16));

                Line linea = new Line();
                linea.setStartX(0.0);
                linea.setEndX(1500);
                //Partido partido = null;
                for (Object p : partidos_fase_unicos) {
                    if (comboEquipo1.getValue().equals(((Partido) p).getEquipo1()) && (comboEquipo2.getValue().equals(((Partido) p).getEquipo2()))) {
                        partido = (Partido) p;
                    }
                }
                Label labelFecha1 = new Label(partido.getFecha().substring(0, 6));
                labelFecha1.setFont(new Font("Arial", 16));
                Label fecha_datos = new Label(partido.getFecha() + " Hora Local");

                Label grupo = new Label(partido.getFase());
                Label estadio = new Label(partido.getEstadio());
                Label ciudad = new Label(partido.getCiudad());
                Label equipo1 = new Label(partido.getEquipo1().getPais());
                Label equipo2 = new Label(partido.getEquipo2().getPais());
                Label marcador = new Label(Integer.toString(partido.getGoles1()) + "-" + Integer.toString(partido.getGoles2()));

                ImageView bandera_equipo1 = new ImageView();
                ImageView bandera_equipo2 = new ImageView();

                vbox_datos.getChildren().addAll(fecha_datos, grupo, estadio, ciudad);
                try ( FileInputStream input1 = new FileInputStream(App.pathImg + "/" + partido.getEquipo1().getPais() + "_flag.png");  FileInputStream input2 = new FileInputStream(App.pathImg + "/" + partido.getEquipo2().getPais() + "_flag.png")) {
                    Image bandera1 = new Image(input1, 90, 110, true, false);
                    Image bandera2 = new Image(input2, 90, 110, true, false);
                    bandera_equipo1.setImage(bandera1);
                    bandera_equipo2.setImage(bandera2);

                } catch (IOException e) {
                }
                hbox_marcador.getChildren().addAll(vbox_datos, bandera_equipo1, equipo1, marcador, equipo2, bandera_equipo2);
                Button btn_exportar = new Button("Exportar resultados de Grupo");
                Button btn_detalle = new Button("Ver detalle de equipo");
                btn_exportar.setStyle("-fx-background-color: #17a0c6;-fx-text-fill:WHITE");
                btn_exportar.setFont(new Font("Arial", 12));
                btn_exportar.setPrefHeight(25);
                btn_detalle.setStyle("-fx-background-color: #17a0c6;-fx-text-fill:WHITE");
                btn_detalle.setFont(new Font("Arial", 12));
                btn_detalle.setPrefHeight(25);
                HBox.setMargin(bandera_equipo1, new Insets(0, 0, 0, 100));
                equipo1.setFont(new Font("Arial", 16));
                equipo2.setFont(new Font("Arial", 16));
                marcador.setFont(new Font("Arial", 16));
                marcador.setStyle("-fx-fill:#249ae4");
                HBox.setMargin(equipo1, new Insets(20, 0, 0, 0));
                HBox.setMargin(equipo2, new Insets(20, 0, 0, 0));
                HBox.setMargin(marcador, new Insets(20, 0, 0, 0));
                hbox_marcador.setSpacing(10);
                vbox_heading.getChildren().addAll(label_resultados, labelFecha1, linea);
                vbox_buttons.getChildren().addAll(btn_exportar, btn_detalle);
                VBox.setMargin(label_resultados, new Insets(0, 0, 0, 375));
                VBox.setMargin(btn_exportar, new Insets(0, 0, 0, 370));
                VBox.setMargin(btn_detalle, new Insets(0, 0, 10, 400));
                vbox_buttons.setSpacing(20);

                btn_exportar.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent a) {
                        serializar(equipos1_unicos, partidos_fase_unicos);

                    }

                });
                btn_detalle.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent evento) {
                        verDetalle(partido.getEquipo1(), partido.getEquipo2());

                    }

                });

            }

        });

    }

    public void serializar(ArrayList<Equipo> equipos1, ArrayList<Partido> partidos) {
        Alert conf_exportar = new Alert(Alert.AlertType.CONFIRMATION);
        conf_exportar.setHeaderText(null);
        conf_exportar.setContentText("¿Está seguro de que desea exportar el grupo de jugadores selccionado?");
        Optional<ButtonType> confirmacion = conf_exportar.showAndWait();
        ArrayList<Equipo> equipos_completos = equipos1;
        if (confirmacion.get() == ButtonType.OK) {
            for (Partido p : partidos) {
                if (!equipos_completos.contains(p.getEquipo2())) {
                    equipos_completos.add(p.getEquipo2());
                }

            }
            //System.out.println(equipos_completos);
            //System.out.println(equipos_completos.get(1).getIniciales());
            try {
                FileOutputStream fout = new FileOutputStream("Lista de Jugadores.bin");
                ObjectOutputStream out = new ObjectOutputStream(fout);
                //System.out.println(equipos_completos.size());
                ArrayList<Jugador> jugadorespartido = new ArrayList();
                for (Equipo e : equipos_completos) {
                    e.cargarJugadores();
                    //System.out.println(e);
                    //out.writeObject(e.getJugadores());
                    //System.out.println(e.getJugadores());
                    for (Jugador j : e.getJugadores()) {
                        jugadorespartido.add(j);
                    }
                }
                //System.out.println(jugadoresdos);
                out.writeObject(jugadorespartido);
            } catch (FileNotFoundException s) {
                System.out.println("No se encontro el archivo");
            } catch (IOException i) {
                System.out.println("Ocurrio un error al leer datos");
            }
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setHeaderText(null);
            info.setContentText("Archivo generado");
            info.showAndWait();
        }
    }

    public void verDetalle(Equipo equipo1, Equipo equipo2) {
        ((Stage) hbox1_partidos.getScene().getWindow()).close();
        equipo1.cargarJugadores();
        equipo2.cargarJugadores();
        Stage s = new Stage();
        VBox vboxroot = new VBox();
        vboxroot.prefWidth(1100);
        vboxroot.prefHeight(550);
        HBox hboxequipo1 = new HBox();
        hboxequipo1.setSpacing(5);
        HBox hboxequipo2 = new HBox();
        hboxequipo2.setSpacing(5);
        ArrayList<ImageView> imagenes = new ArrayList();
        for (int i = 0; i < 12; i++) {
            ImageView imagev = new ImageView();
            imagev.setFitWidth(150);
            imagev.setFitHeight(170);
            imagenes.add(imagev);
        }
        setDefaultImages(imagenes);
        //ArrayList<VBox>vboxjugadores=new ArrayList();
        HBox hboxdetalle = new HBox();
        hboxdetalle.setAlignment(Pos.CENTER);
        Label detalles = new Label("Detalle de equipos");
        detalles.setStyle("-fx-font-size:14;-fx-font-weight:bold");
        hboxdetalle.getChildren().addAll(detalles);
        Label lblequipo1 = new Label("");
        lblequipo1.setText(equipo1.getPais());
        lblequipo1.setStyle("-fx-font-size:14;-fx-font-weight:bold");
        Label lblequipo2 = new Label("");
        lblequipo2.setText(equipo2.getPais());
        lblequipo2.setStyle("-fx-font-size:14;-fx-font-weight:bold");
        llenarHBoxs(hboxequipo1, hboxequipo2, imagenes);
        vboxroot.getChildren().addAll(hboxdetalle, lblequipo1, hboxequipo1, lblequipo2, hboxequipo2);
        vboxroot.setSpacing(10);
        Random rd = new Random();
        Thread hiloimagenes = new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<Integer> numerosunicos = new ArrayList();
                for (int i = 0; i < 12; i++) {
                    try {
                        int tiempodeespera = rd.nextInt(10) + 6;
                        //System.out.println(tiempodeespera);
                        int posicion = rd.nextInt(12);
                        while (numerosunicos.contains(posicion)) {
                            posicion = rd.nextInt(12);
                            //System.out.println(posicion);
                        }
                        numerosunicos.add(posicion);
                        //System.out.println(posicion);
                        //System.out.println(tiempodeespera);
                        setImagenesyLabelsJugadores(posicion, hboxequipo1, hboxequipo2, equipo1, equipo2);
                        Thread.sleep(tiempodeespera * 1000);
                        //Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        System.out.println("Se interrumpio el hilo");
                    }

                }

            }
        });
        try {
            hiloimagenes.join();
        } catch (InterruptedException excep1) {
            System.out.println("Se interrumpio el hilo");
        }
        hiloimagenes.start();
        //setImagenesJugadores(2,hboxequipo1,hboxequipo2,equipo1,equipo2);

        for (int f = 0; f < 6; f++) {
            crearVentanaJugador(f, equipo1, hboxequipo1);
            crearVentanaJugador(f, equipo2, hboxequipo2);
        }

        //crearVentanaJugador()
        Scene scene = new Scene(vboxroot);
        s.setTitle("Detalle de equipos");
        s.setScene(scene);
        s.show();
    }

    public void setDefaultImages(ArrayList<ImageView> imagenes) {
        for (ImageView imgv : imagenes) {
            try ( FileInputStream input = new FileInputStream(App.pathImg + "noimage.png")) {
                Image img = new Image(input, 150, 170, true, false);
                imgv.setImage(img);
            } catch (FileNotFoundException ex1) {
                System.out.println("No se encontro el archivo");

            } catch (IOException ex2) {
                System.out.println("Ocurrio un problema al leer los datos de imagen");
            }
        }
    }

    public void llenarHBoxs(HBox hboxequipo1, HBox hboxequipo2, ArrayList<ImageView> imagenes) {
        for (int i = 0; i < 12; i++) {
            VBox vboxjugador = new VBox();
            Label lblnombre = new Label("Nombre jugador");
            imagenes.get(i).setFitHeight(170);
            imagenes.get(i).setFitWidth(150);
            vboxjugador.getChildren().addAll(imagenes.get(i), lblnombre);
            vboxjugador.setAlignment(Pos.CENTER);
            vboxjugador.setStyle("-fx-background-color:#d2d6d4;-fx-border-color:#d2d6d4;-fx-border-radius:10;-fx-border-width:5");
            if (i < 6) {
                hboxequipo1.getChildren().addAll(vboxjugador);
            } else {
                hboxequipo2.getChildren().addAll(vboxjugador);
            }
        }

    }

    public void setImagenesyLabelsJugadores(int i, HBox hboxequipo1, HBox hboxequipo2, Equipo equipo1, Equipo equipo2) {
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                if (i <= 5) {
                    try ( FileInputStream fis = new FileInputStream(App.pathImg + equipo1.getJugadores().get(i).getNombre() + ".png")) {
                        Image img = new Image(fis);
                        ((ImageView) ((VBox) hboxequipo1.getChildren().get(i)).getChildren().get(0)).setImage(img);
                        ((Label) ((VBox) hboxequipo1.getChildren().get(i)).getChildren().get(1)).setText(equipo1.getJugadores().get(i).getNombre());
                    } catch (FileNotFoundException ex1) {
                        System.out.println("No se encontro el archivo");
                    } catch (IOException ex2) {
                        System.out.println("Ocurrio un problema al leer datos");
                    }

                } else {
                    //System.out.println(i);
                    //System.out.println(equipo2);
                    //System.out.println(equipo2.getJugadores().get(i-6).getNombre());
                    try ( FileInputStream fis = new FileInputStream(App.pathImg + equipo2.getJugadores().get(i - 6).getNombre() + ".png")) {
                        Image img = new Image(fis);
                        ((ImageView) ((VBox) hboxequipo2.getChildren().get(i - 6)).getChildren().get(0)).setImage(img);
                        ((Label) ((VBox) hboxequipo2.getChildren().get(i - 6)).getChildren().get(1)).setText(equipo2.getJugadores().get(i - 6).getNombre());
                    } catch (FileNotFoundException ex1) {
                        System.out.println("No se encontro el archivo");
                    } catch (IOException ex2) {
                        System.out.println("Ocurrio un problema al leer datos");
                    }
                }
            }

        });

    }

    public void crearVentanaJugador(int i, Equipo equipo1, HBox hboxequipo1) {
        VBox vboxjugador = ((VBox) hboxequipo1.getChildren().get(i));
        vboxjugador.setFocusTraversable(true);
        vboxjugador.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                Stage s = new Stage();
                Scene scene;
                VBox vboxdetallejugador = new VBox();
                vboxdetallejugador.setAlignment(Pos.CENTER);
                VBox vboxinfo = new VBox();
                Label lbldt = new Label("DT. " + equipo1.getJugadores().get(i).getDt());
                Label lblpais = new Label(equipo1.getIniciales());
                Label lblcamiseta = new Label("CAMISETA NRO " + equipo1.getJugadores().get(i).getnCamiseta());
                vboxinfo.setSpacing(10);
                vboxinfo.getChildren().addAll(lblpais, lblcamiseta, lbldt);
                vboxinfo.setStyle("-fx-background-color:#41e884");
                Label lblnombre = new Label();
                lblnombre.setText(equipo1.getJugadores().get(i).getNombre());
                lblnombre.setStyle("-fx-font-size:18;-fx-font-weight:bold");
                Label lblcontador = new Label();
                ImageView imgv = new ImageView();
                imgv.setFitWidth(150);
                imgv.setFitHeight(170);
                try ( FileInputStream input = new FileInputStream(App.pathImg + equipo1.getJugadores().get(i).getNombre() + ".png")) {
                    Image img = new Image(input);
                    imgv.setImage(img);

                } catch (FileNotFoundException ex1) {
                    System.out.println("No se encontro el archivo");
                } catch (IOException ex2) {
                    System.out.println("Ocurrio un problema al leer los datos");
                }
                vboxdetallejugador.setSpacing(15);
                vboxinfo.setAlignment(Pos.CENTER);
                vboxdetallejugador.getChildren().addAll(lblnombre, imgv, vboxinfo, lblcontador);

                //cambiarContador()
                scene = new Scene(vboxdetallejugador, 200, 400);
                s.setScene(scene);
                s.show();
                mostrarContador(lblcontador);

            }

        });

    }

    public void mostrarContador(Label lblcontador) {
        Thread hilocontador = new Thread(new Runnable() {
            public void run() {
                int contador = 10;
                for (int d = 0; d < 11; d++) {
                    int contador1 = contador + d;
                    //System.out.println(contador1);
                    cambiarContador(contador1, lblcontador);
                    contador = contador -= 2;
                    //System.out.println(contador);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException excep3) {
                        System.out.println("Se interumpio el hilo");
                    }
                }

            }

        });
        hilocontador.start();

    }

    public void cambiarContador(int i, Label lblcontador) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //System.out.println(i);
                if (i != 0) {
                    lblcontador.setText("Mostrando por " + i + " segundos.");
                } else {
                    ((Stage) (lblcontador.getScene().getWindow())).close();
                }
            }

        });

    }

}
