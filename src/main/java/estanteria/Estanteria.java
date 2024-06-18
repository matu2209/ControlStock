package estanteria;


import Interfaces.Buscable;
import enumeradores.Prioridad;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class Estanteria implements Buscable<Integer> {
    private static Integer autoId=1;
    private final Integer idEstanteria;
    private final Map<Posicion, ProductoAlmacenado> estanteria;
    private Prioridad prioridad;

    public Estanteria(Prioridad prioridad) {
        idEstanteria=autoId++;
        estanteria = new HashMap<>();
        this.prioridad = prioridad;
    }

    public abstract void crearPosicionesEnMaximos();

    public Map<Posicion, ProductoAlmacenado> getEstanteria() {
        return estanteria;
    }

    public Integer getIdEstanteria() {
        return idEstanteria;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estanteria that = (Estanteria) o;
        return Objects.equals(getEstanteria(), that.getEstanteria()) && getPrioridad() == that.getPrioridad();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEstanteria(), getPrioridad());
    }

    @Override
    public String toString() {
        return
                "Estanteria{" + "idEstanteria=" + idEstanteria +
                "estanteria=" + estanteria +
                ", prioridad=" + prioridad +
                '}';
    }

    /*public void agregarProducto(int posicion, Producto producto) {
        if (posicion >= 0 && posicion < 300) {
            Posicion p = estanteria.get(posicion);
            if (producto.getVolumen() < p.getVolumen()) {
                p.agregarObjeto(producto);
            } else {
                System.out.println("La posición " + posicion + " está llena.");
            }
        } else {
            System.out.println("Posición inválida.");
        }
    }

    public List<Producto> obtenerProductosEnPosicion(int posicion) {
        if (posicion >= 0 && posicion < 300) {
            estanteria.get(posicion).mostrarContenido();
            return estanteria.get(posicion).getProductos();
        } else {
            System.out.println("Posición inválida.");
            return null;
        }
    }

    */
}