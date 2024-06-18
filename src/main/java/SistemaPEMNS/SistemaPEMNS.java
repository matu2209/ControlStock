package SistemaPEMNS;

import Gestor.GestorGenerico;

import Productos.Producto;
import Orden.*;
import Productos.Producto;
import estanteria.Estanteria;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class SistemaPEMNS {

    private final GestorGenerico<Producto, TreeSet<Producto>,Integer> gestorProductos;
    private final GestorGenerico<OrdenAlmacenamiento, PriorityQueue<OrdenAlmacenamiento>,Integer> gestorOrdenesAlmacenamiento;
    private final GestorGenerico<OrdenPicking, PriorityQueue<OrdenPicking>,Integer> gestorOrdenesPicking;
    private final GestorGenerico<Estanteria, ArrayList<Estanteria>,Integer> gestorEstanteria;

    public SistemaPEMNS() {
        this.gestorProductos = new GestorGenerico<>(new TreeSet<Producto>());
        this.gestorOrdenesAlmacenamiento = new GestorGenerico<>(new PriorityQueue<OrdenAlmacenamiento>());
        this.gestorOrdenesPicking = new GestorGenerico<>(new PriorityQueue<OrdenPicking>());
        this.gestorEstanteria = new GestorGenerico<>(new ArrayList<Estanteria>());
    }

    //puede lanzar excepcion
    public Producto buscarProducto(Integer hashProducto) {
        return gestorProductos.buscar(hashProducto);
    }

}
