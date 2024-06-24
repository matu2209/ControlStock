package SistemaPEMNS;

import gestores.GestorCollGen;

import gestores.GestorMapGen;
import Productos.Producto;
import Orden.*;
import enumeradores.DestinoEcommerce;
import enumeradores.Prioridad;
import estanteria.Estanteria;
import estanteria.Posicion;
import productoAlmacenado.ProductoAlmacenado;

import java.util.*;

public class SistemaPEMNS {
    //GESTORES MANEJADORES DE CLASES ESPECIFICOS DE NUESTRO SISTEMA
    private final GestorCollGen<Producto, TreeSet<Producto>,Integer, Prioridad> gestorProductos;
    private final GestorCollGen<OrdenAlmacenamiento, PriorityQueue<OrdenAlmacenamiento>,Integer, String> gestorOrdenesAlmacenamiento;
    private final GestorCollGen<OrdenPicking, PriorityQueue<OrdenPicking>,Integer, String> gestorOrdenesPicking;
    private final GestorCollGen<Estanteria, ArrayList<Estanteria>, Integer, Prioridad> gestorEstanteria;  //vamos a trabajar por busqueda desde esta collection pero estaria bueno trabajarlo desde mapaRelacionalRastreo para optimizar las busquedas

    //GESTORES RELACIONALES DE BÚSQUEDA DE NUESTRO SISTEMA

    private final GestorMapGen<Producto, Integer, Prioridad, Posicion, Integer, Double, Map<Producto, Posicion>> mapaRelacionalRastreo; //es quien va a llevar la relacion entre un producto y las ubicaciones donde se encuentra almacenado
                                                                                                                                            // totalmente necesario para las operaciones de busqueda tanto para almacenamiento como para pickeo
    private final GestorMapGen<Posicion, Integer, Double, ProductoAlmacenado, Integer, Integer, Map<Posicion, ProductoAlmacenado>>mapaRelacionalAlmacenamiento;


    public SistemaPEMNS() {
        this.gestorProductos = new GestorCollGen<>(new TreeSet<>());
        this.gestorOrdenesAlmacenamiento = new GestorCollGen<>(new PriorityQueue<OrdenAlmacenamiento>());
        this.gestorOrdenesPicking = new GestorCollGen<>(new PriorityQueue<OrdenPicking>());
        this.gestorEstanteria = new GestorCollGen<>(new ArrayList<>());
        this.mapaRelacionalRastreo=new GestorMapGen<>(new TreeMap<>());
        this.mapaRelacionalAlmacenamiento=new GestorMapGen<>(new TreeMap<>());
    }

    public GestorCollGen<Producto, TreeSet<Producto>, Integer, Prioridad> getGestorProductos() {
        return gestorProductos;
    }

    public GestorMapGen<Producto, Integer, Prioridad, Posicion, Integer, Double, Map<Producto, Posicion>> getMapaRelacionalRastreo() {
        return mapaRelacionalRastreo;
    }

    public GestorCollGen<Estanteria, ArrayList<Estanteria>, Integer, Prioridad> getGestorEstanteria() {
        return gestorEstanteria;
    }

    public GestorCollGen<OrdenPicking, PriorityQueue<OrdenPicking>, Integer, String> getGestorOrdenesPicking() {
        return gestorOrdenesPicking;
    }

    public GestorCollGen<OrdenAlmacenamiento, PriorityQueue<OrdenAlmacenamiento>, Integer, String> getGestorOrdenesAlmacenamiento() {
        return gestorOrdenesAlmacenamiento;
    }

    public GestorMapGen<Posicion, Integer, Double, ProductoAlmacenado, Integer, Integer, Map<Posicion, ProductoAlmacenado>> getMapaRelacionalAlmacenamiento() {
        return mapaRelacionalAlmacenamiento;
    }

    //HAY QUE MODIFICAR LO DE ABAJO PARA QUE NO TIRE ERROR, AHI ME PONGO A HACERLO



    public int generarOrdenPickingDesdePedido (Integer hashProducto, Integer cantidad, DestinoEcommerce destinoEcommerce, String idPedido) {
        List<Posicion> posicionesDelProducto = this.getMapaRelacionalRastreo().buscarPorClaveTodos(hashProducto); // Obtengo la lista de posiciones en que se almacena un producto
        Integer cantidadAPickear = cantidad;
        int indexPosicion = 0;

        while (cantidadAPickear > 0 && indexPosicion < posicionesDelProducto.size()) {
            List<ProductoAlmacenado> productosBuscados = this.getMapaRelacionalAlmacenamiento()
                    .buscarPorClaveTodos(posicionesDelProducto.get(indexPosicion).getHashPosicion())
                    .stream()
                    .filter(elemento -> elemento.getProductoAlmacenado().getHashProducto().equals(hashProducto))
                    .toList(); // Obtengo la lista de productos almacenados en una posición y los filtro por el hash del producto que necesito

            for (ProductoAlmacenado productoAlmacenado : productosBuscados) {
                if (productoAlmacenado.getStockDePosicion() >= cantidadAPickear) {
                    getGestorOrdenesPicking().agregar(new OrdenPicking(hashProducto, cantidadAPickear, destinoEcommerce, idPedido, posicionesDelProducto.get(indexPosicion).getHashPosicion()));
                    cantidadAPickear = 0;
                    break;
                } else {
                    getGestorOrdenesPicking().agregar(new OrdenPicking(hashProducto, productoAlmacenado.getStockDePosicion(), destinoEcommerce, idPedido, posicionesDelProducto.get(indexPosicion).getHashPosicion()));
                    cantidadAPickear -= productoAlmacenado.getStockDePosicion();
                }
            }

            indexPosicion++;
        }

        if (cantidadAPickear > 0) {
            // No se pudo completar el picking de todos los productos solicitados
            // Hay que hacer una excepcion personalizada
        }

        return cantidad - cantidadAPickear; // Retorna la cantidad que se logró pickear
    }

    //public boolean generarOrdenPicking(Integer hashProducto, int cantidad, String idPedido, DestinoEcommerce ecommerce) {
    //        //buscar el producto
    //        Producto producto = gestorProductos.buscar(hashProducto);
    //        Posicion p;
    //        ProductoAlmacenado productoAlmacenado;
    //        if (producto.getStock()<cantidad) return false;
    //        else {
    //            producto.setStock(producto.getStock()-cantidad);
    //            do {
    //                p = gestorEstanteria.buscar(1).getListaPosiciones().getElementos().stream().filter(posicion -> posicion.filtrarProductoAlmacenado(producto)).findFirst().orElse(null);
    //                productoAlmacenado = p.buscarProductoAlmacenado(producto);
    //
    //                p.setVolumenDisponible(p.getVolumenDisponible() + producto.getVolumen() * cantidad);
    //                if (cantidad >= productoAlmacenado.getStockDePosicion()) {
    //                    cantidad = cantidad - productoAlmacenado.getStockDePosicion();
    //                    p.getProductosAlmacenados().remove(productoAlmacenado);
    //                } else {
    //                    productoAlmacenado.setStockDePosicion(productoAlmacenado.getStockDePosicion() - cantidad);
    //                    cantidad = cantidad - productoAlmacenado.getStockDePosicion();
    //
    //                }
    //
    //            } while (cantidad > 0);
    //        }
    //        return true;
    //    }


    //public void almacenarProducto(Producto producto, int cantidad) {
    //
    //        Posicion p = gestorEstanteria.buscar(1).getListaPosiciones().getElementos().stream().filter(posicion -> posicion.getVolumenDisponible()>producto.getVolumen()*cantidad).findFirst().orElse(null);
    //        if (p!=null) {
    //            //que pasa si existe?
    //           p.setVolumenDisponible(p.getVolumenDisponible()-producto.getVolumen()*cantidad);
    //           p.setProductosAlmacenados(new ProductoAlmacenado(producto,cantidad,p.getHashPosicion()));
    //        }
    //    }




//    public boolean generarOrdenAlmacenamiento(Integer hashProducto, int cantidad, String nroRemito, Empresa empresa){
//        //buscar el producto
//        Producto producto = gestorProductos.buscar(hashProducto);
//        return gestorOrdenesAlmacenamiento.agregar(new OrdenAlmacenamiento(producto,cantidad,nroRemito,empresa));
//    }
//
//    public void finalizarOrdenPicking(Integer idOrden, Integer legajoFinalizador){
//        OrdenPicking ordenPicking = gestorOrdenesPicking.buscar(idOrden);
//        //sacar que sea una linea de codigo
//        gestorEstanteria.buscar(ordenPicking.getEstanteria()).getEstanteria().get(ordenPicking.getPosicion()).setVolumenAlmacenado(ordenPicking.getProducto().getVolumen()*(ordenPicking.getCantidadProducto())*-1);
//        ordenPicking.finalizarOrden(legajoFinalizador,ordenPicking.getPosicion());
//    }
//


}
