package estanteria;

import Interfaces.Buscable;
import Interfaces.Filtrable;
import enumeradores.Disponible;
import enumeradores.Prioridad;
import enumeradores.VolumenDisponible;

import java.util.Objects;

public class Posicion implements Buscable<Integer>, Filtrable<Double>,  Comparable<Posicion> {
    private Integer modulo;
    private Integer nivel;
    private Integer x;
    private Integer idEstanteria;
    private final Integer hashPosicion;
    private Double volumenDisponible;
    private Prioridad prioridad;
    private Disponible disponibilidad;

    public Posicion(Integer modulo, Integer nivel, Integer x, Integer idEstanteria, Prioridad prioridad) {
        this.modulo = modulo;
        this.nivel = nivel;
        this.x = x;
        this.idEstanteria = idEstanteria;
        this.hashPosicion= Objects.hash(modulo,nivel, x, idEstanteria, prioridad);
        this.volumenDisponible = VolumenDisponible.ALTO.getCapacidad(); //SE CREA POR DEFECTO AL 80% DE LA CAPACIDAD
        this.prioridad = prioridad;
        this.disponibilidad=Disponible.DISPONIBLE;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Integer getModulo() {
        return modulo;
    }

    public void setModulo(Integer modulo) {
        this.modulo = modulo;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getIdEstanteria() {
        return idEstanteria;
    }

    public void setIdEstanteria(Integer idEstanteria) {
        this.idEstanteria = idEstanteria;
    }

    public Integer getHashPosicion() {
        return hashPosicion;
    }


    public Double getVolumenDisponible() {
        return volumenDisponible;
    }

    public void setVolumenDisponible(Double volumenDisponible) {
        this.volumenDisponible = volumenDisponible;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public Disponible getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Disponible disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public boolean filtrarPorPrioridad (Prioridad prioridad){
        return this.getPrioridad().equals(prioridad);
    }

    @Override
    public int compareTo(Posicion posicionAComparar) {
        return getHashPosicion().compareTo(posicionAComparar.getHashPosicion());
    }


    @Override
    public boolean buscar(Integer parametroABuscar) {
        return this.hashPosicion.equals(parametroABuscar);
    }

    @Override
    public boolean filter(Double parametroFiltrado){ //SI EL VOLUMEN DISPONIBLE ES MAYOR O IGUAL AL VOLUMEN DEL PARAMETRO, RETORNA TRUE
        return this.getVolumenDisponible() >= parametroFiltrado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Posicion posicion = (Posicion) o;
        return Objects.equals(getModulo(), posicion.getModulo()) && Objects.equals(getNivel(), posicion.getNivel()) && Objects.equals(getX(), posicion.getX()) && Objects.equals(getIdEstanteria(), posicion.getIdEstanteria()) && Objects.equals(getHashPosicion(), posicion.getHashPosicion()) && Objects.equals(getVolumenDisponible(), posicion.getVolumenDisponible()) && getPrioridad() == posicion.getPrioridad() && getDisponibilidad() == posicion.getDisponibilidad();
    }

    @Override
    public int hashCode() {
        return this.getHashPosicion();
    }

    @Override
    public String toString() {
        return "Posicion{" +
                "modulo=" + modulo +
                ", nivel=" + nivel +
                ", x=" + x +
                ", idEstanteria=" + idEstanteria +
                ", hashPosicion=" + hashPosicion +
                ", volumenDisponible=" + volumenDisponible +
                ", prioridad=" + prioridad +
                ", disponibilidad=" + disponibilidad +
                '}';
    }
}

