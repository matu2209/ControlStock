package Orden;

import Interfaces.Buscable;
import Interfaces.Filtrable;
import Productos.Producto;
import enumeradores.DestinoEcommerce;
import enumeradores.EstadoOrden;
import estanteria.Posicion;

import java.time.LocalDateTime;
import java.util.Objects;

public class OrdenPicking extends Orden implements Comparable<OrdenPicking>, Buscable<Integer>, Filtrable<String>{ //Se puede filtrar por pedido
    private DestinoEcommerce destinoEcommerce;
    private static Integer autoID = 1;
    private final Integer idOrden;
    private String pedido;
    private final Integer estanteria;

    public OrdenPicking(Producto producto, Integer cantidadProducto, DestinoEcommerce destinoEcommerce, String pedido, Integer estanteria, Integer posicion) {
        super(producto, cantidadProducto);
        this.destinoEcommerce = destinoEcommerce;
        this.idOrden = autoID++;
        this.pedido = pedido;
        this.estanteria = estanteria;
        super.setPosicion(posicion);
    }

    public Integer getEstanteria() {
        return estanteria;
    }

    @Override
    public Integer getPosicion() {
        return super.getPosicion();
    }

    public DestinoEcommerce getDestinoEcommerce() {
        return destinoEcommerce;
    }

    public void setDestinoEcommerce(DestinoEcommerce destinoEcommerce) {
        this.destinoEcommerce = destinoEcommerce;
    }

    public Integer getIdOrden() {
        return idOrden;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrdenPicking that = (OrdenPicking) o;
        return getDestinoEcommerce() == that.getDestinoEcommerce() && Objects.equals(getIdOrden(), that.getIdOrden()) && Objects.equals(getPedido(), that.getPedido());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDestinoEcommerce(), getIdOrden(), getPedido());
    }

    @Override
    public String toString() {
        return "OrdenPicking{" +
                "destinoEcommerce=" + destinoEcommerce +
                ", idOrden=" + idOrden +
                ", pedido='" + pedido + '\'' +
                '}';
    }

    @Override
    public boolean buscar(Integer parametroABuscar) {
        return this.getIdOrden().equals(parametroABuscar);
    }

    @Override
    public boolean filter(String parametroFiltrado) {
        return this.getPedido().equalsIgnoreCase(parametroFiltrado);
    }

    @Override
    public int compareTo(OrdenPicking orden) {
        return this.getIdOrden().compareTo(orden.getIdOrden());
    }

    @Override
    public void finalizarOrden(Integer legajoRealizador, Integer posicion){
        this.setPosicion(posicion);
        this.setLegajo(legajoRealizador);
        this.setEstado(EstadoOrden.FINALIZADA);
        this.setFechaRealizacion(LocalDateTime.now());
    }
    @Override
    public void cancelarOrden(){
        this.setEstado(EstadoOrden.CANCELADA);
        this.setFechaRealizacion(LocalDateTime.now());
    }
}
