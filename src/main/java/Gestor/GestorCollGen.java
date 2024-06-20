package Gestor;

import Interfaces.Buscable;

import java.util.*;


public class GestorCollGen<T extends Buscable<C>, U extends Collection<T>,C> { //falta parametrizar a Buscable

    private final U elementos;

    public GestorCollGen(U collection) {
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

    public T buscar(C elemento) {
        return elementos.stream().filter(el -> el.equals(elemento)).findFirst().orElse(null);
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
