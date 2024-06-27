package Orden;

import enumeradores.DestinoEcommerce;
import enumeradores.EstadoOrden;

import java.time.LocalDateTime;
import java.util.Objects;

public class OrdenPicking extends Orden{ //Se puede filtrar por pedido
    private DestinoEcommerce destinoEcommerce;
    private String pedido;
    private Integer idProductoAlmacenado;

    public OrdenPicking(Integer hashProducto, Integer cantidadProducto, DestinoEcommerce destinoEcommerce, String pedido, Integer hashPosicionCreacion, Integer idProductoAlmacenado) {
        super(hashProducto, cantidadProducto, hashPosicionCreacion);
        this.destinoEcommerce = destinoEcommerce;
        this.pedido = pedido;
        this.idProductoAlmacenado = idProductoAlmacenado;
    }


    public DestinoEcommerce getDestinoEcommerce() {
        return destinoEcommerce;
    }

    public void setDestinoEcommerce(DestinoEcommerce destinoEcommerce) {
        this.destinoEcommerce = destinoEcommerce;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public Integer getIdProductoAlmacenado() {
        return idProductoAlmacenado;
    }

    public void setIdProductoAlmacenado(Integer idProductoAlmacenado) {
        this.idProductoAlmacenado = idProductoAlmacenado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrdenPicking that = (OrdenPicking) o;
        return getDestinoEcommerce() == that.getDestinoEcommerce() && Objects.equals(getPedido(), that.getPedido()) && Objects.equals(getIdProductoAlmacenado(), that.getIdProductoAlmacenado());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDestinoEcommerce(), getPedido(), getIdProductoAlmacenado());
    }

    @Override
    public String toString() {
        return "OrdenPicking{" +
                "destinoEcommerce=" + destinoEcommerce +
                ", pedido='" + pedido + '\'' +
                ", idProductoAlmacenado=" + idProductoAlmacenado +
                '}';
    }

    @Override
    public boolean buscar(Integer parametroABuscar) {
        return super.getIdOrden().equals(parametroABuscar);
    }

    @Override
    public boolean filter(String parametroFiltrado) {
        return this.getPedido().equalsIgnoreCase(parametroFiltrado);
    }


    @Override
    public void finalizarOrden(Integer legajoRealizador){
        this.setLegajo(legajoRealizador);
        this.setEstado(EstadoOrden.FINALIZADA);
        this.setFechaRealizacion(LocalDateTime.now());
    }

}
