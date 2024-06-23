package Gestor;

import Interfaces.Buscable;
import Interfaces.Filtrable;

import java.util.*;


public class GestorCollGen<E extends Buscable<B> & Filtrable <F> & Comparable <E>, C extends Collection<E>, B, F> { //falta parametrizar a Buscable

    private final C elementos;

    public GestorCollGen(C collection) {
        this.elementos = collection;
    }

    public boolean agregar(E elemento) {
        return elementos.add(elemento);
    }

    public boolean eliminar(E elemento) {
        return elementos.remove(elemento);
    }

    public int tamanio() {
        return elementos.size();
    }

    public boolean contiene(E elemento) {
        return elementos.contains(elemento);
    }

    public E buscarPrimero(B criterioBusqueda) {
        return elementos.stream().filter(elemento -> elemento.buscar(criterioBusqueda)).findFirst().orElse(null);
    }

    public List<E> buscarTodos(B criterioBusqueda) {
        return elementos.stream().filter(elemento -> elemento.buscar(criterioBusqueda)).toList();
    }

    public List<E> filtrar(F criterioBusqueda) {
        return elementos.stream().filter(elemento -> elemento.filter(criterioBusqueda)).toList();
    }
    public void limpiar() {
        elementos.clear();
    }

    public Iterator<E> iterator() {
        return elementos.iterator();
    }

    @Override
    public String toString() {
        return "GestorColeccionesDatos{" +
                "elementos=" + elementos +
                '}';
    }
}

