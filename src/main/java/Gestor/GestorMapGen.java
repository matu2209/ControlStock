package Gestor;

import Interfaces.Buscable;

import java.util.Iterator;
import java.util.Map;

public class GestorMapGen<  K extends Buscable<T>, V extends Buscable <T>, G extends Map<K,V>, T> {

    private final G elementos;

    public GestorMapGen(G map) {
        this.elementos = map;
    }

    public V agregar(K clave, V valor) {
        return elementos.put(clave, valor);
    }

    public V eliminar(K clave) {
        return elementos.remove(clave);
    }

    public int tamanio() {
        return elementos.size();
    }

    public boolean contieneClave(K clave) {
        return elementos.containsKey(clave);
    }

    public V buscar(K clave) {
        return elementos.get(clave);
    }

    public void limpiar() {
        elementos.clear();
    }

    public Iterator<K> iterator() {
        return elementos.keySet().iterator();
    }

    @Override
    public String toString() {
        return "GestorMapGen{" +
                "elementos=" + elementos +
                '}';
    }
}