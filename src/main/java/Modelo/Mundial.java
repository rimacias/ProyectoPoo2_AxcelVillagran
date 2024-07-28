package Modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import Ventanasproyecto.App;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author AVGla
 */
public class Mundial {

    private String anio;
    private String golesAnotados;
    private String nEquipos;
    private String nPartidos;
    private String nAsistentes;
    private ArrayList<Equipo> mejoresCuatro = new ArrayList();
    private Equipo equipoGanador;
    private Equipo equipoSegundo;
    private Equipo equipoTercero;
    private Equipo equipoCuarto;

    public Mundial(String anio, String golesAnotados, String nEquipos, String nPartidos, String nAsistentes, ArrayList<Equipo> mejoresCuatro) {
        this.anio = anio;
        this.golesAnotados = golesAnotados;
        this.nEquipos = nEquipos;
        this.nPartidos = nPartidos;
        this.nAsistentes = nAsistentes;
        this.mejoresCuatro = mejoresCuatro;
    }

    public String getAnio() {
        return this.anio;
    }

    public String getGolesAnotados() {
        return this.golesAnotados;
    }

    public String getnEquipos() {
        return this.nEquipos;
    }

    public String getnPartidos() {
        return this.nPartidos;
    }

    public String getnAsistentes() {
        return this.nAsistentes;
    }

    public ArrayList<Equipo> getMejoresCuatro() {
        return this.mejoresCuatro;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public void setgolesAnotados(String golesAnotados) {
        this.golesAnotados = golesAnotados;
    }

    public void setnEquipos(String nEquipos) {
        this.nEquipos = nEquipos;
    }

    public void setnPartidos(String nPartidos) {
        this.nPartidos = nPartidos;
    }

    public void setnAsistentes(String nAsistentes) {
        this.nAsistentes = nAsistentes;
    }

    public void setMejoresCuatro(ArrayList<Equipo> mejoresCuatro) {
        this.mejoresCuatro = mejoresCuatro;
    }

    public static Mundial generarMundial(String anioelegido) {
        if (anioelegido != null) {
            try ( BufferedReader bfr = new BufferedReader(new FileReader(App.pathFile + "WorldCups.csv"))) {
                String linea;
                while ((linea = bfr.readLine()) != null) {
                    String[] datosMundial = linea.trim().split(",");
                    String anio = datosMundial[0];
                    String anotados = datosMundial[6];
                    String equipos = datosMundial[7];
                    String partidos = datosMundial[8];
                    String asistencia = datosMundial[9];
                    Equipo ganador = new Equipo(datosMundial[2]);
                    ganador.sumarCopas();
                    Equipo segundo = new Equipo(datosMundial[3]);
                    segundo.sumarCopas();
                    Equipo tercero = new Equipo(datosMundial[4]);
                    tercero.sumarCopas();
                    Equipo cuarto = new Equipo(datosMundial[5]);
                    cuarto.sumarCopas();
                    ArrayList<Equipo> mejoresCuatro = new ArrayList();
                    mejoresCuatro.add(ganador);
                    mejoresCuatro.add(segundo);
                    mejoresCuatro.add(tercero);
                    mejoresCuatro.add(cuarto);
                    if (anioelegido.equals(anio)) {
//         System.out.println(ganador);
//        System.out.println(segundo);
                        return new Mundial(anio, anotados, equipos, partidos, asistencia, mejoresCuatro);
                    }
                }
            } catch (FileNotFoundException excep1) {
                System.out.println("No se encontro el archivo");
            } catch (IOException excep2) {
                System.out.println("Ocurrio un problema al leer los datos");
            }
            return null;
        }
        return null;
    }

}
