package gestores;

import Interfaces.Buscable;
import Interfaces.Filtrable;

import java.util.*;
import java.util.stream.Collectors;

public class GestorMapGen<K extends Buscable<B> & Filtrable<F> & Comparable<K>, B, F, V extends Buscable<C> & Filtrable<G> & Comparable<V>, C, G, M extends Map<K, LinkedList<V>>> {

    private final M mapa;

    public GestorMapGen(M mapa) {
        this.mapa = mapa;
    }

    public M getMapa() {
        return mapa;
    }

    public void agregar(K clave, V valor) {
        mapa.computeIfAbsent(clave, k -> new LinkedList<>()).add(valor);
    }

    public LinkedList<V> eliminar(K clave) {
        return mapa.remove(clave);
    }

    public int tamanio() {
        return mapa.size();
    }

    public boolean contieneClave(K clave) {
        return mapa.containsKey(clave);
    }

    public boolean contieneValor(V valor) {
        return mapa.values().stream()
                .anyMatch(lista -> lista.contains(valor));
    }

    public V buscarPorClavePrimerValor(B criterioBusqueda) {  //devuelve el primer valor que matchee con el criterio de búsqueda de la clave
        return mapa.keySet().stream()
                .filter(clave -> clave.buscar(criterioBusqueda))
                .findFirst()
                .map(mapa::get)
                .map(lista -> lista.isEmpty() ? null : lista.getFirst())
                .orElse(null);
    }

    public List<V> devolverListaValoresDeKeyBuscada(B criterioBusqueda) {
        return mapa.keySet().stream()
                .filter(clave -> clave.buscar(criterioBusqueda))
                .findFirst()
                .map(mapa::get)
                .orElseGet(LinkedList::new);
    }


    public List<V> devolverListaValoresDeKeyBuscadaFiltrada(B criterioBusquedaClave, G parametroFiltradoValor) {
        return mapa.keySet().stream()
                .filter(clave -> clave.buscar(criterioBusquedaClave))
                .findFirst()
                .map(clave -> mapa.get(clave)
                        .stream()
                        .filter(valor -> valor.filter(parametroFiltradoValor))
                        .collect(Collectors.toList())) // Recolecta los valores filtrados en una lista
                .orElse(Collections.emptyList()); // En caso de que no encuentre la clave, retorna una lista vacía
    }

    public K buscarPorValorPrimerClave(C criterioBusqueda) {  //devuelve la primero que matchee con el criterio de busqueda
        return mapa.entrySet().stream()
                .filter(entry -> entry.getValue().stream().anyMatch(valor -> valor.buscar(criterioBusqueda)))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }


    public List<V> buscarPorClaveTodosValores(B criterioBusqueda) { //DEVUELVE UNA LISTA DE TODAS LAS LISTAS DE VALORES DE LAS CLAVES QUE CUMPLEN CON EL CRITERIO DE BUSQUEDA DE LA CLAVE
        return mapa.keySet().stream()
                .filter(clave -> clave.buscar(criterioBusqueda))
                .flatMap(clave -> mapa.get(clave).stream())
                .collect(Collectors.toList());
    }


    public List<K> filtrarClaves(F criterioFiltrado) {
        return mapa.keySet().stream()
                .filter(clave -> clave.filter(criterioFiltrado))
                .collect(Collectors.toList());
    }

    public Collection<V> filtrarValores(G criterioFiltrado) {
        return mapa.values().stream()
                .flatMap(Collection::stream)
                .filter(valor -> valor.filter(criterioFiltrado))
                .collect(Collectors.toList());
    }

    public void limpiar() {
        mapa.clear();
    }

    public Set<K> claves() {
        return mapa.keySet();
    }

    public Collection<LinkedList<V>> valores() {
        return mapa.values();
    }

    public Set<Map.Entry<K, LinkedList<V>>> entradas() {
        return mapa.entrySet();
    }

    @Override
    public String toString() {
        return "GestorMapGen{" +
                "mapa=" + mapa +
                '}';
    }
}