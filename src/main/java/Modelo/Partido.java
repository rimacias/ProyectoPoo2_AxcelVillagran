package Modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import Ventanasproyecto.App;

/**
 *
 * @author AVGla
 */
public class Partido {

    private Equipo equipo1;
    private Equipo equipo2;
    private int goles1;
    private int goles2;
    private String fecha;
    private String fase;
    private String estadio;
    private String ciudad;

    public Partido(Equipo equipo1, Equipo equipo2, int goles1, int goles2, String fecha, String fase, String estadio, String ciudad) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.goles1 = goles1;
        this.goles2 = goles2;
        this.fecha = fecha;
        this.fase = fase;
        this.estadio = estadio;

    }

    public Equipo getEquipo1() {
        return this.equipo1;
    }

    public Equipo getEquipo2() {
        return this.equipo2;
    }

    public int getGoles1() {
        return this.goles1;
    }

    public int getGoles2() {
        return this.goles2;
    }

    public String getFecha() {
        return this.fecha;
    }

    public String getFase() {
        return this.fase;
    }

    public String getEstadio() {
        return this.estadio;
    }

    public String getCiudad() {
        return this.ciudad;
    }

    public void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }

    public void setEquipo2(Equipo equipo2) {
        this.equipo2 = equipo2;
    }

    public void setGoles1(int goles1) {
        this.goles1 = goles1;
    }

    public void setGoles2(int goles2) {
        this.goles2 = goles2;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public static ArrayList<Partido> cargarPartido() {
        ArrayList<Partido> partidos = new ArrayList();
        try ( FileReader fr = new FileReader(App.pathFile + "/WorldCupMatchesBrasil2014.csv");  BufferedReader br = new BufferedReader(fr)) {
            String linea = br.readLine();
            linea = br.readLine();
            while (linea != null) {

                String[] datos_partidos = linea.split("\\|");

                partidos.add(new Partido(new Equipo(datos_partidos[5], datos_partidos[18]), new Equipo(datos_partidos[8], datos_partidos[19]), Integer.parseInt(datos_partidos[6]), Integer.parseInt(datos_partidos[7]), datos_partidos[1], datos_partidos[2], datos_partidos[3], datos_partidos[4]));

                linea = br.readLine();

            }
        } catch (IOException e) {

        }

        return partidos;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null) {
            if (o instanceof Partido) {
                Partido p = (Partido) o;
                return ((p.getEquipo1()).equals(p.getEquipo1()) && (p.getEquipo2()).equals(this.getEquipo2()));
            }
        }
        return false;
    }
}
