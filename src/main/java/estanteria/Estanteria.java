package estanteria;


import enumeradores.ValoresMaximos;
import gestores.GestorCollGen;
import Interfaces.Buscable;
import Interfaces.Filtrable;
import enumeradores.Empresa;
import enumeradores.Prioridad;

import java.util.*;

public class Estanteria implements Buscable<Integer>, Filtrable<Prioridad>, Comparable<Estanteria> {
    private static Integer autoId=1;
    private final Integer idEstanteria;
    private final GestorCollGen<Posicion,List<Posicion>,Integer, Double> listaPosiciones;
    private final Prioridad prioridad;
    private final Empresa empresa;

    public Estanteria(Prioridad prioridad, Empresa empresa) {
        this.idEstanteria = autoId++;
        this.listaPosiciones = new GestorCollGen<>(new LinkedList<>());
        this.prioridad = prioridad;
        this.empresa=empresa;
    }

    public Integer getIdEstanteria() {
        return idEstanteria;
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
        return Objects.equals(getIdEstanteria(), that.getIdEstanteria()) && Objects.equals(getListaPosiciones(), that.getListaPosiciones()) && getPrioridad() == that.getPrioridad() && getEmpresa() == that.getEmpresa();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdEstanteria(), getListaPosiciones(), getPrioridad(), getEmpresa());
    }

    @Override
    public String toString() {
        return "Estanteria{" +
                "idEstanteria=" + idEstanteria +
                ", listaPosiciones=" + listaPosiciones +
                ", prioridad=" + prioridad +
                ", empresa=" + empresa +
                '}';
    }

    @Override
    public boolean filter(Prioridad parametroFiltrado) { //RETORNA TRUE SI LA PRIORIDAD DE LA ESTANTERIA ES LA MISMA QUE EL PARAMETRO DE FILTRADO
        return getPrioridad().equals(parametroFiltrado);
    }

    @Override
    public boolean buscar(Integer parametroABuscar) {
        return this.getIdEstanteria().equals(parametroABuscar);
    }

    @Override
    public int compareTo(Estanteria estanteria) {
        return this.getPrioridad().compareTo(estanteria.getPrioridad());
    }

    public void crearPosicionesEstanteria() {
        for (int modulo = 1; modulo <= ValoresMaximos.MAXIMO_MODULOS.getValor(); modulo++) {
            for (int nivel = 1; nivel <= ValoresMaximos.MAXIMO_NIVELES.getValor(); nivel++) {
                for (int x = 1; x <= ValoresMaximos.MAXIMO_X_CALZADO.getValor(); x++) { //CREO TODAS LAS ESTANTERIAS COMO SI FUERA PARA CALZADO, EN PROXIMA ACTUALIZACION SE VIENE HERENCIA DE ESTANTERIAS. UNA CLASE HIJA ESTANTERIAINDACC Y OTRA ESTANTERIACALZADO
                    Posicion posicion = new Posicion(modulo, nivel, x,this.getIdEstanteria(), this.getPrioridad());
                    this.getListaPosiciones().agregar(posicion);
                }
            }
        }
    }
}