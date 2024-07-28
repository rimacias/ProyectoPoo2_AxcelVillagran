package Modelo;

import java.io.Serializable;

/**
 *
 * @author AVGla
 */
public class Jugador implements Serializable {

    private String nombre;
    private Equipo equipo;
    private int nCamiseta;
    private String dt;
    private String iniciales_equipo;

    public Jugador(String nombre, Equipo equipo, int nCamiseta, String dt) {
        this.nombre = nombre;
        this.equipo = equipo;
        this.nCamiseta = nCamiseta;
        this.dt = dt;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Equipo getEquipo() {
        return this.equipo;
    }

    public int getnCamiseta() {
        return this.nCamiseta;
    }

    public String getDt() {
        return this.dt;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public void setnCamiseta(int nCamiseta) {
        this.nCamiseta = nCamiseta;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null) {
            if (o instanceof Jugador) {
                Jugador j = (Jugador) o;
                return (j.getNombre()).equals(this.getNombre());
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}
