package productoAlmacenado;

import Interfaces.Buscable;
import Interfaces.Filtrable;
import Productos.Producto;
import estanteria.Posicion;

import java.util.Objects;

public class ProductoAlmacenado implements Buscable<Integer>, Filtrable<Integer>, Comparable<ProductoAlmacenado> { //SE FILTRA Y SE COMPARA POR EL HASH Producto productoAlmacenado YA QUE EN SI ES EL MISMO PERO DISTRIBUIDO EN DIFERENTES LUGARES
    private static Integer autoId=0;
    private final Integer idProductoAlmacenado;
    private Producto productoAlmacenado;
    private Posicion posicionDeAlmacenado;
    private Integer stockDePosicion;//STOCK QUE ALMACENO EN UNA POSICION
    private Double volumenAlmacenado;

    public ProductoAlmacenado(Producto productoAlmacenado,Posicion posicionDeAlmacenado, Integer stockDePosicion) {
        this.idProductoAlmacenado = autoId++;
        this.productoAlmacenado = productoAlmacenado;
        this.posicionDeAlmacenado = posicionDeAlmacenado;
        this.stockDePosicion = stockDePosicion;
        this.volumenAlmacenado = productoAlmacenado.getVolumen()*stockDePosicion;
    }


    public Integer getIdProductoAlmacenado() {
        return idProductoAlmacenado;
    }

    public Producto getProductoAlmacenado() {
        return productoAlmacenado;
    }

    public void setProductoAlmacenado(Producto productoAlmacenado) {
        this.productoAlmacenado = productoAlmacenado;
    }

    public Posicion getPosicionDeAlmacenado() {
        return posicionDeAlmacenado;
    }

    public void setPosicionDeAlmacenado(Posicion posicionDeAlmacenado) {
        this.posicionDeAlmacenado = posicionDeAlmacenado;
    }

    public Integer getStockDePosicion() {
        return stockDePosicion;
    }

    public void setStockDePosicion(Integer stockDePosicion) {
        this.stockDePosicion = stockDePosicion;
    }

    public Double getVolumenAlmacenado() {
        return volumenAlmacenado;
    }

    public void setVolumenAlmacenado(Double volumenAlmacenado) {
        this.volumenAlmacenado = volumenAlmacenado;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductoAlmacenado that = (ProductoAlmacenado) o;
        return Objects.equals(getIdProductoAlmacenado(), that.getIdProductoAlmacenado()) && Objects.equals(getProductoAlmacenado(), that.getProductoAlmacenado()) && Objects.equals(getPosicionDeAlmacenado(), that.getPosicionDeAlmacenado()) && Objects.equals(getStockDePosicion(), that.getStockDePosicion()) && Objects.equals(getVolumenAlmacenado(), that.getVolumenAlmacenado());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdProductoAlmacenado(), getProductoAlmacenado(), getPosicionDeAlmacenado(), getStockDePosicion(), getVolumenAlmacenado());
    }

    @Override
    public String toString() {
        return "ProductoAlmacenado{" +
                "idProductoAlmacenado=" + idProductoAlmacenado +
                ", productoAlmacenado=" + productoAlmacenado +
                ", posicionDeAlmacenado=" + posicionDeAlmacenado +
                ", stockDePosicion=" + stockDePosicion +
                ", volumenAlmacenado=" + volumenAlmacenado +
                '}';
    }

    @Override
    public boolean filter(Integer parametroFiltrado) {
        return this.getProductoAlmacenado().getHashProducto().equals(parametroFiltrado);
    }

    @Override
    public boolean buscar(Integer parametroABuscar) {
        return this.getIdProductoAlmacenado().equals(parametroABuscar);
    }

    @Override
    public int compareTo(ProductoAlmacenado productoAlmacenado) {
        return this.getIdProductoAlmacenado().compareTo(productoAlmacenado.getIdProductoAlmacenado());
    }

}
