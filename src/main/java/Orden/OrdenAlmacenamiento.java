package Orden;

import Interfaces.Buscable;
import Interfaces.Filtrable;
import enumeradores.Empresa;
import enumeradores.EstadoOrden;

import java.time.LocalDateTime;
import java.util.Objects;

public class OrdenAlmacenamiento extends Orden implements Comparable<OrdenAlmacenamiento>, Buscable<Integer>, Filtrable<String> { //se puede filtrar por nroRemito
    private static Integer autoID = 1;
    private final Integer idOrden;
    private String nroRemito;
    private Empresa empresa;

    public OrdenAlmacenamiento(Integer hashProducto, Integer cantidadProducto, String nroRemito, Empresa empresa, Integer hashPosicion) {
        super(hashProducto, cantidadProducto, hashPosicion);
        this.idOrden = autoID++;
        this.nroRemito = nroRemito;
        this.empresa = empresa;
    }


    public Integer getIdOrden() {
        return idOrden;
    }

    public String getNroRemito() {
        return nroRemito;
    }

    public void setNroRemito(String nroRemito) {
        this.nroRemito = nroRemito;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrdenAlmacenamiento that = (OrdenAlmacenamiento) o;
        return Objects.equals(getIdOrden(), that.getIdOrden()) && Objects.equals(getNroRemito(), that.getNroRemito()) && getEmpresa() == that.getEmpresa();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getIdOrden(), getNroRemito(), getEmpresa());
    }

    @Override
    public String toString() {
        return "OrdenAlmacenamiento{" +
                "idOrden=" + idOrden +
                ", nroRemito='" + nroRemito + '\'' +
                ", empresa=" + empresa +
                '}';
    }

    @Override
    public boolean buscar(Integer parametroABuscar) {
        return this.getIdOrden().equals(parametroABuscar);
    }

    @Override
    public boolean filter(String parametroFiltrado) {
        return this.getNroRemito().toUpperCase().contains(parametroFiltrado.toUpperCase());
    }

    @Override
    public int compareTo(OrdenAlmacenamiento orden) {
        return this.getIdOrden().compareTo(orden.getIdOrden());
    }


    @Override
    public void finalizarOrden(Integer legajoRealizador, Integer posicion){
        this.setHashPosicion(posicion);
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
