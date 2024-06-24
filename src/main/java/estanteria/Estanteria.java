package estanteria;


import Gestor.GestorCollGen;
import Interfaces.Buscable;
import Interfaces.Filtrable;
import enumeradores.Empresa;
import enumeradores.Prioridad;

import java.util.*;

public abstract class Estanteria implements Buscable<Integer>, Filtrable<Prioridad>, Comparable<Estanteria> {
    private final GestorCollGen<Posicion,List<Posicion>,Integer, Double> listaPosiciones;
    private final Prioridad prioridad;
    private final Empresa empresa;

    public Estanteria(Prioridad prioridad, Empresa empresa) {
        this.listaPosiciones = new GestorCollGen<>(new LinkedList<Posicion>());
        this.prioridad = prioridad;
        this.empresa=empresa;
    }


    public GestorCollGen<Posicion, List<Posicion>, Integer, Double> getListaPosiciones() {
        return listaPosiciones;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estanteria that = (Estanteria) o;
        return Objects.equals(getListaPosiciones(), that.getListaPosiciones()) && getPrioridad() == that.getPrioridad() && getEmpresa() == that.getEmpresa();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getListaPosiciones(), getPrioridad(), getEmpresa());
    }

    @Override
    public String toString() {
        return "Estanteria{" +
                "listaPosiciones=" + listaPosiciones +
                ", prioridad=" + prioridad +
                ", empresa=" + empresa +
                '}';
    }

    public abstract void crearPosicionesEstanteria();

}