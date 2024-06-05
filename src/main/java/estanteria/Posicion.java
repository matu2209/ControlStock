package estanteria;

import Interfaces.Buscable;
import enumeradores.Prioridad;
import enumeradores.VolumenDisponible;

import java.util.Objects;

public class Posicion implements Buscable, Comparable<Posicion> {

    private Integer modulo;
    private Integer nivel;
    private Integer x;
    private Integer hashPosicion;
    private VolumenDisponible volumenDisponible;
    private Prioridad prioridad;

    public Posicion(Integer modulo, Integer nivel, Integer x, VolumenDisponible volumenDisponible, Prioridad prioridad) {
        this.modulo = modulo;
        this.nivel = nivel;
        this.x = x;
        this.volumenDisponible = volumenDisponible;
        this.prioridad = prioridad;
        this.hashPosicion= Objects.hash(modulo,nivel, x, prioridad);

    }

    public Integer getModulo() {
        return modulo;
    }

    public void setModulo(Integer modulo) {
        this.modulo = modulo;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getHashPosicion() {
        return hashPosicion;
    }

    public void setHashPosicion(Integer hashPosicion) {
        this.hashPosicion = hashPosicion;
    }

    public VolumenDisponible getVolumenDisponible() {
        return volumenDisponible;
    }

    public void setVolumenDisponible(VolumenDisponible volumenDisponible) {
        this.volumenDisponible = volumenDisponible;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public int hashCode() {
        return hashPosicion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Posicion posicion = (Posicion) o;
        return Objects.equals(modulo, posicion.modulo) &&
                Objects.equals(nivel, posicion.nivel) &&
                Objects.equals(x, posicion.x) &&
                Objects.equals(volumenDisponible, posicion.volumenDisponible) &&
                Objects.equals(prioridad, posicion.prioridad) &&
                Objects.equals(hashPosicion, posicion.hashPosicion);
    }

    @Override
    public String toString() {
        return "Posicion{" +
                "modulo=" + modulo +
                ", nivel=" + nivel +
                ", x=" + x +
                ", hash=" + hashPosicion +
                ", volumenDisponible=" + volumenDisponible +
                ", prioridad=" + prioridad + '}';
    }

    @Override
    public int compareTo(Posicion posicionAComparar) {
        return getHashPosicion().compareTo(posicionAComparar.getHashPosicion());
    }


}

