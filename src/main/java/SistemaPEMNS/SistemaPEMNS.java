package SistemaPEMNS;

import Gestor.GestorGenerico;

import Productos.Producto;
import Orden.*;
import Productos.Producto;
import enumeradores.DestinoEcommerce;
import enumeradores.Empresa;
import enumeradores.Prioridad;
import enumeradores.VolumenDisponible;
import estanteria.Estanteria;
import estanteria.Posicion;
import estanteria.ProductoAlmacenado;

import java.util.ArrayList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class SistemaPEMNS {

    private final GestorGenerico<Producto, TreeSet<Producto>,Integer> gestorProductos;
    private final GestorGenerico<OrdenAlmacenamiento, PriorityQueue<OrdenAlmacenamiento>,Integer> gestorOrdenesAlmacenamiento;
    private final GestorGenerico<OrdenPicking, PriorityQueue<OrdenPicking>,Integer> gestorOrdenesPicking;
    private final GestorGenerico<Estanteria, ArrayList<Estanteria>, Integer> gestorEstanteria;

    public SistemaPEMNS() {
        this.gestorProductos = new GestorGenerico<>(new TreeSet<Producto>());
        this.gestorOrdenesAlmacenamiento = new GestorGenerico<>(new PriorityQueue<OrdenAlmacenamiento>());
        this.gestorOrdenesPicking = new GestorGenerico<>(new PriorityQueue<OrdenPicking>());
        this.gestorEstanteria = new GestorGenerico<>(new ArrayList<Estanteria>());
    }

    public boolean generarOrdenPicking(Integer hashProducto, int cantidad, String idPedido, DestinoEcommerce ecommerce) {
        //buscar el producto
        Producto producto = gestorProductos.buscar(hashProducto);
            Estanteria estanteria = gestorEstanteria.buscar(producto.getPrioridad().getValor());
            for (Map.Entry<Posicion, ProductoAlmacenado> entry : estanteria.getEstanteria().entrySet()) {
                if (entry.getValue().getProductoAlmacenado().equals(producto) && entry.getValue().getProductoAlmacenado().getStock() >= cantidad) {
                    producto.setStock(producto.getStock()-cantidad);//se reserva del stock disponible
                    return gestorOrdenesPicking.agregar(new OrdenPicking(producto, cantidad, ecommerce, idPedido,estanteria.getIdEstanteria(),entry.getKey().getHashPosicion()));
                }
            }
        return false;
    }

    public boolean generarOrdenAlmacenamiento(Integer hashProducto, int cantidad, String nroRemito, Empresa empresa){
        //buscar el producto
        Producto producto = gestorProductos.buscar(hashProducto);

        return gestorOrdenesAlmacenamiento.agregar(new OrdenAlmacenamiento(producto,cantidad,nroRemito,empresa));
    }

    public void finalizarOrdenPicking(Integer idOrden, Integer legajoFinalizador){
        OrdenPicking ordenPicking = gestorOrdenesPicking.buscar(idOrden);
        //sacar que sea una linea de codigo
        gestorEstanteria.buscar(ordenPicking.getEstanteria()).getEstanteria().get(ordenPicking.getPosicion()).setVolumenAlmacenado(ordenPicking.getProducto().getVolumen()*(ordenPicking.getCantidadProducto())*-1);
        ordenPicking.finalizarOrden(legajoFinalizador,ordenPicking.getPosicion());
    }


}
