package Modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import Ventanasproyecto.App;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author AVGla
 */
public class Equipo implements Comparable<Equipo>, Serializable {

    private ArrayList<Jugador> jugadores = new ArrayList();
    private String pais;
    private String iniciales;
    private int copasMundiales = 0;
    private String imagenEquipo;

    public Equipo(String pais, String iniciales) {
        this.pais = pais;
        this.iniciales = iniciales;
    }

    public Equipo(String pais) {
        this.pais = pais;
    }

    public String getIniciales() {
        return this.iniciales;
    }

    public ArrayList<Jugador> getJugadores() {
        return this.jugadores;
    }

    public String getPais() {
        return this.pais;
    }

    public int getCopasMundiales() {
        return this.copasMundiales;
    }

    public String getImagen() {
        return this.imagenEquipo;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setCopasMundiales(int copasMundiales) {
        this.copasMundiales = copasMundiales;
    }

    public void setIniciales(String iniciales) {
        this.iniciales = iniciales;
    }

    public void setImagen(String imagenEquipo) {
        this.imagenEquipo = imagenEquipo;
    }

    @Override
    public String toString() {
        return this.pais;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (o != null) {
            if (o instanceof Equipo) {
                Equipo e = (Equipo) o;
                return (e.getPais()).equals(this.getPais());
            }
        }
        return false;
    }

    @Override
    public int compareTo(Equipo equiporandom) {
        return this.getPais().compareTo(equiporandom.getPais());
    }

    public void sumarCopas() {
        try ( BufferedReader bfr = new BufferedReader(new FileReader(App.pathFile + "WorldCups.csv"))) {
            String linea;
            while ((linea = bfr.readLine()) != null) {
                String[] datoslinea = linea.trim().split(",");
                if (datoslinea[2].equals(this.getPais())) {
                    this.copasMundiales += 1;
                }
            }

        } catch (FileNotFoundException excep1) {
            System.out.println("No se encontro el archivo");
        } catch (IOException excep2) {
            System.out.println("Ocurrio un problema al leer datos");
        }

    }

    public void cargarJugadores() {
        ArrayList<Jugador> jugadoresrepetidos = new ArrayList();
        try ( BufferedReader br = new BufferedReader(new FileReader(App.pathFile + "/WorldCupPlayersBrasil2014.csv", StandardCharsets.UTF_8))) {
            String linea = br.readLine();
            linea = br.readLine();
            while (linea != null) {
                String[] datos = linea.split(",");
                if (this.getIniciales().equals(datos[2])) {
                    jugadoresrepetidos.add(new Jugador(datos[6], this, Integer.parseInt(datos[5]), datos[3]));
                }
                linea = br.readLine();

            }
            for (Jugador player : jugadoresrepetidos) {
                if (!this.jugadores.contains(player)) {
                    this.jugadores.add(player);
                }

            }

        } catch (FileNotFoundException f) {
            System.out.println("No se encontro el archivo");
        } catch (IOException i) {
            System.out.println("Ocurrio un error de lectura");
        }
        //System.out.println(this.jugadores);
    }
}
