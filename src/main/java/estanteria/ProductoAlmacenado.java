package estanteria;

import Productos.Producto;

import java.util.Objects;

public class ProductoAlmacenado {
    private Producto productoAlmacenado;
    private Integer stockDePosicion;//STOCK QUE ALMACENO EN UNA POSICION
    private Double volumenAlmacenado;

    public ProductoAlmacenado(Producto productoAlmacenado, Integer stockDePosicion) {
        this.productoAlmacenado = productoAlmacenado;
        this.stockDePosicion = stockDePosicion;
        this.volumenAlmacenado = productoAlmacenado.getVolumen()*stockDePosicion;
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
        return Objects.equals(getProductoAlmacenado(), that.getProductoAlmacenado()) && Objects.equals(getStockDePosicion(), that.getStockDePosicion()) && Objects.equals(getVolumenAlmacenado(), that.getVolumenAlmacenado());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductoAlmacenado(), getStockDePosicion(), getVolumenAlmacenado());
    }

    @Override
    public String toString() {
        return "ProductoAlmacenado{" +
                "productoAlmacenado=" + productoAlmacenado +
                ", stockDePosicion=" + stockDePosicion +
                ", volumenAlmacenado=" + volumenAlmacenado +
                '}';
    }
}
