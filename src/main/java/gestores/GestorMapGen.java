package gestores;

import Interfaces.Buscable;
import Interfaces.Filtrable;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class GestorMapGen<K extends Buscable<B> & Filtrable<F> & Comparable <K>, B, F,  V extends Buscable<C> & Filtrable<G> & Comparable <V>, C, G, M extends Map<K, V>> {

    private final M mapa;

    public GestorMapGen(M mapa) {
        this.mapa = mapa;
    }

    public V agregar(K clave, V valor) {
        return mapa.put(clave, valor);
    }

    public V eliminar(K clave) {
        return mapa.remove(clave);
    }

    public int tamanio() {
        return mapa.size();
    }

    public boolean contieneClave(K clave) {
        return mapa.containsKey(clave);
    }

    public boolean contieneValor(V valor) {
        return mapa.containsValue(valor);
    }

    public V buscarPorClavePrimero(B criterioBusqueda) {
        return mapa.keySet().stream()
                .filter(clave -> clave.buscar(criterioBusqueda))
                .findFirst()
                .map(mapa::get)
                .orElse(null);
    }

    public K buscarPorValorPrimero(C criterioBusqueda) {
        return mapa.entrySet().stream()
                .filter(entry -> entry.getValue().buscar(criterioBusqueda))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    // Método para buscar valores cuyas claves cumplen con el criterio de búsqueda
    public List<V> buscarPorClaveTodos(B criterioBusqueda) {
        return mapa.keySet().stream()
                .filter(clave -> clave.buscar(criterioBusqueda))
                .map(mapa::get)
                .collect(Collectors.toList());
    }

    public List<K> buscarPorValorTodos(C criterioBusqueda) {
        return mapa.entrySet().stream()
                .filter(entry -> entry.getValue().buscar(criterioBusqueda))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public Set<K> filtrarClaves(F criterioFiltrado) {
        return mapa.keySet().stream()
                .filter(clave -> clave.filter(criterioFiltrado))
                .collect(Collectors.toSet());
    }

    public Collection<V> filtrarValores(G criterioFiltrado) {
        return mapa.values().stream()
                .filter(valor -> valor.filter(criterioFiltrado))
                .collect(Collectors.toList());
    }

    public void limpiar() {
        mapa.clear();
    }

    public Set<K> claves() {
        return mapa.keySet();
    }

    public Collection<V> valores() {
        return mapa.values();
    }

    public Set<Map.Entry<K, V>> entradas() {
        return mapa.entrySet();
    }

    @Override
    public String toString() {
        return "GestorMapGen{" +
                "mapa=" + mapa +
                '}';
    }
}