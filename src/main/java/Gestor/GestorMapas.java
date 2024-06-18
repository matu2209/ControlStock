package Gestor;

import java.util.Collection;
import java.util.Map;

public class GestorMapas<T extends Map<U,V>, W extends Collection<T>,U,V> {
    private final W collection;

    public GestorMapas(W collection) {
        this.collection = collection;
    }

    public W getCollection() {
        return collection;
    }

    public void agregar(U key, V value) {

    }
}
