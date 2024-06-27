package Orden;

import enumeradores.Empresa;
import enumeradores.EstadoOrden;

import java.time.LocalDateTime;
import java.util.Objects;

public class OrdenAlmacenamiento extends Orden { //se puede filtrar por nroRemito ;
    private String nroRemito;
    private Empresa empresa;
    private Integer hashPosicionAlmacenamiento;


    public OrdenAlmacenamiento(Integer hashProducto, Integer cantidadProducto,Integer hashPosicionCreacion, String nroRemito, Empresa empresa) {
        super(hashProducto, cantidadProducto, hashPosicionCreacion);
        this.nroRemito = nroRemito;
        this.empresa = empresa;
        this.hashPosicionAlmacenamiento = null;
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

    public Integer getHashPosicionAlmacenamiento() {
        return hashPosicionAlmacenamiento;
    }

    public void setHashPosicionAlmacenamiento(Integer hashPosicionAlmacenamiento) {
        this.hashPosicionAlmacenamiento = hashPosicionAlmacenamiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrdenAlmacenamiento that = (OrdenAlmacenamiento) o;
        return Objects.equals(getNroRemito(), that.getNroRemito()) && getEmpresa() == that.getEmpresa() && Objects.equals(getHashPosicionAlmacenamiento(), that.getHashPosicionAlmacenamiento());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNroRemito(), getEmpresa(), getHashPosicionAlmacenamiento());
    }

    @Override
    public String toString() {
        return "OrdenAlmacenamiento{" +
                ", nroRemito='" + nroRemito + '\'' +
                ", empresa=" + empresa +
                "hashPosicionAlmacenamiento=" + hashPosicionAlmacenamiento +
                '}';
    }

    @Override
    public boolean buscar(Integer parametroABuscar) {
        return super.getIdOrden().equals(parametroABuscar);
    }

    @Override
    public boolean filter(String parametroFiltrado) {
        return this.getNroRemito().toUpperCase().contains(parametroFiltrado.toUpperCase());
    }



    @Override
    public void finalizarOrden(Integer legajoRealizador){
        this.setLegajo(legajoRealizador);
        this.setEstado(EstadoOrden.FINALIZADA);
        this.setFechaRealizacion(LocalDateTime.now());
    }

    public void finalizarOrden(Integer legajoRealizador, Integer hashPosicionAlmacenamiento){
        this.setHashPosicionCreacion(hashPosicionAlmacenamiento);
        this.setLegajo(legajoRealizador);
        this.setEstado(EstadoOrden.FINALIZADA);
        this.setFechaRealizacion(LocalDateTime.now());
    }

}
