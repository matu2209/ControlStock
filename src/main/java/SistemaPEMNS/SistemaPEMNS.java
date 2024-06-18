package SistemaPEMNS;

import Gestor.GestorGenerico;
import Orden.OrdenAlmacenamiento;
import Orden.OrdenPicking;
import Productos.Producto;
import estanteria.Estanteria;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class SistemaPEMNS {
    private final GestorGenerico<Producto, TreeSet<Producto>, Integer> gestorProducto;
    private final GestorGenerico<OrdenAlmacenamiento, PriorityQueue<OrdenAlmacenamiento>,Integer> gestorOrdenAlmacenamiento;
    private final GestorGenerico<OrdenPicking, PriorityQueue<OrdenPicking>, Integer>gestorOrdenPicking;
    private final GestorGenerico<Estanteria, ArrayList<Estanteria>,Integer> gestorEstanteria;

    SistemaPEMNS() {
        this.gestorProducto=new GestorGenerico<>(new TreeSet<Producto>());
        this.gestorEstanteria = new GestorGenerico<>(new PriorityQueue<OrdenAlmacenamiento>());
        this.gestorOrdenAlmacenamiento=new GestorGenerico<>(new PriorityQueue<OrdenPicking>());
        this.gestorOrdenPicking = new GestorGenerico<>(new ArrayList<Estanteria>());
    }



}
