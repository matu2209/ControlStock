package Gestor;

import Interfaces.Buscable;

import java.util.*;

public class GestorColecciones<T extends Buscable> {
    private Collection<T> elementos;

    public GestorColecciones(Collection<T> collection) {
        this.elementos = collection;
    }

    public boolean agregar(T elemento) {
        return elementos.add(elemento);
    }

    public boolean eliminar(T elemento) {
        return elementos.remove(elemento);
    }

    public int tamanio() {
        return elementos.size();
    }

    public boolean contiene(T elemento) {
        return elementos.contains(elemento);
    }

    public void limpiar() {
        elementos.clear();
    }

    public Iterator<T> iterator() {
        return elementos.iterator();
    }

    @Override
    public String toString() {
        return "GestorColeccionesDatos{" +
                "elementos=" + elementos +
                '}';
    }
}

