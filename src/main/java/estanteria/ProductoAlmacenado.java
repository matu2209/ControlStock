package estanteria;

import Interfaces.Buscable;
import Interfaces.Filtrable;
import Productos.Producto;

import java.util.Objects;

public class ProductoAlmacenado implements Buscable<Integer>, Filtrable<Producto>, Comparable<ProductoAlmacenado> {
    private static Integer autoId=0;
    private final Integer idProducto;
    private Producto productoAlmacenado;
    private Integer stockDePosicion;//STOCK QUE ALMACENO EN UNA POSICION
    private Double volumenAlmacenado;

    public ProductoAlmacenado(Producto productoAlmacenado, Integer stockDePosicion) {
        this.idProducto = autoId++;
        this.productoAlmacenado = productoAlmacenado;
        this.stockDePosicion = stockDePosicion;
        this.volumenAlmacenado = productoAlmacenado.getVolumen()*stockDePosicion;
    }


    public Integer getIdProducto() {
        return idProducto;
    }

    public Producto getProductoAlmacenado() {
        return productoAlmacenado;
    }

    public void setProductoAlmacenado(Producto productoAlmacenado) {
        this.productoAlmacenado = productoAlmacenado;
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
        return Objects.equals(getIdProducto(), that.getIdProducto()) && Objects.equals(getProductoAlmacenado(), that.getProductoAlmacenado()) && Objects.equals(getStockDePosicion(), that.getStockDePosicion()) && Objects.equals(getVolumenAlmacenado(), that.getVolumenAlmacenado());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdProducto(), getProductoAlmacenado(), getStockDePosicion(), getVolumenAlmacenado());
    }

    @Override
    public String toString() {
        return "ProductoAlmacenado{" +
                "idProducto=" + idProducto +
                ", productoAlmacenado=" + productoAlmacenado +
                ", stockDePosicion=" + stockDePosicion +
                ", volumenAlmacenado=" + volumenAlmacenado +
                '}';
    }



    @Override
    public boolean filter(Producto parametroFiltrado) {
        return this.getProductoAlmacenado().equals(parametroFiltrado);
    }

    @Override
    public boolean buscar(Integer parametroABuscar) {
        return this.getIdProducto().equals(parametroABuscar);
    }

    @Override
    public int compareTo(ProductoAlmacenado productoAlmacenado) {
        return this.getIdProducto().compareTo(productoAlmacenado.getIdProducto());
    }

}
